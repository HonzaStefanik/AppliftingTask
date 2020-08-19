package applifting.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("applifting.task.infrastructure.model")
public class AppliftingTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppliftingTaskApplication.class, args);
    }

}
