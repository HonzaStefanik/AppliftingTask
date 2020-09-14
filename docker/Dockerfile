ARG PROJECT_NAME

# Stage: builder
FROM adoptopenjdk/maven-openjdk11:latest AS builder
ARG PROJECT_NAME
LABEL project=$PROJECT_NAME

WORKDIR /tmp
COPY pom.xml .
COPY src src
RUN mvn -B package -DskipTests -Pnangu-nexus || mvn -B package -DskipTests

# Stage: final
FROM adoptopenjdk/openjdk11:latest
ARG PROJECT_NAME
LABEL project=$PROJECT_NAME

ENV XMS=256m
ENV XMX=256m
ENV DEBUG_PORT=8787
ENV JAVA_OPTS=""

COPY docker/docker-entrypoint.sh /
COPY --from=builder /tmp/target/*.jar /var/lib/app/application.jar

RUN ["chmod", "+x", "/docker-entrypoint.sh"]
ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["run"]
