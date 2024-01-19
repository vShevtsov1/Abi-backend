FROM openjdk:21-slim-buster as build

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

RUN ./mvnw -B dependency:go-offline

COPY src src

RUN ./mvnw -B package

FROM openjdk:21-slim-buster

COPY --from=build target/folders-service-0.0.1-SNAPSHOT.jar .

EXPOSE 8087

ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=prod", "folders-service-0.0.1-SNAPSHOT.jar"]