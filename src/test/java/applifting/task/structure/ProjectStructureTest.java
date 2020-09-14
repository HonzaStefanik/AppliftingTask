package applifting.task.structure;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import static com.tngtech.archunit.core.domain.JavaClass.Predicates.simpleNameEndingWith;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "applifting.task", importOptions = {ImportOption.DoNotIncludeArchives.class,
        ImportOption.DoNotIncludeJars.class})
public class ProjectStructureTest {

    private static Architectures.LayeredArchitecture layers() {
        return layeredArchitecture()
                .layer("Handler").definedBy("..handler..")
                .layer("Application").definedBy("..application..")
                .layer("Domain").definedBy("..domain..")
                .layer("Infrastructure").definedBy("..infrastructure..");
    }

   @ArchTest
   public static final ArchRule handlersCannotBeAccessedByAnyLayer =
           layers().whereLayer("Handler").mayNotBeAccessedByAnyLayer();

   @ArchTest
   public static final ArchRule applicationCanBeAccessedByHandlersOnly =
           layers().whereLayer("Application").mayOnlyBeAccessedByLayers("Handler");

   @ArchTest
   public static final ArchRule domainCanBeAccessedByAllLayers = layers()
           .whereLayer("Domain").mayOnlyBeAccessedByLayers("Application", "Infrastructure", "Handler");

   @ArchTest
   public static final ArchRule controllersOnlyInHandlerPackages = classes()
           .that().areAnnotatedWith(Controller.class).should().resideInAPackage("..handler..");

    @ArchTest
    public static final ArchRule repositoriesInDomainPackageMustBeInterfaces = classes()
            .that().resideInAPackage("..domain..")
            .and().haveSimpleNameEndingWith("Repository")
            .should().beInterfaces();

    @ArchTest
    public static final ArchRule repositoryImplementaionsOnlyInInfrastructureRepositoryPackages = classes()
            .that().areAnnotatedWith(Repository.class)
            .should().resideInAPackage("..infrastructure..")
            .andShould().resideInAPackage("..repository..");

    @ArchTest
    public static final ArchRule repositoryImplementationNamesMustEndWithRepository = classes()
            .that().areAnnotatedWith(Repository.class)
            .and().implement(simpleNameEndingWith("Repository"))
            .should().haveSimpleNameEndingWith("Repository");

    @ArchTest
    public static final ArchRule domainPackageShouldContainClientDefinitionsOnly = classes()
            .that().resideInAPackage("..domain..")
            .and().haveSimpleNameEndingWith("Client")
            .should().beInterfaces();

    @ArchTest
    public static final ArchRule clientImplementationNamesMustEndWithClient = classes()
            .that().resideInAPackage("..infrastructure..")
            .and().implement(simpleNameEndingWith("Client"))
            .should().haveSimpleNameEndingWith("Client");
}
