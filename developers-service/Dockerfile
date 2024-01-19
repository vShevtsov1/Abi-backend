FROM openjdk:21-slim-buster as build

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

RUN ./mvnw -B dependency:go-offline

COPY src src

RUN ./mvnw -B package

FROM openjdk:21-slim-buster

COPY --from=build target/developers-service-0.0.1-SNAPSHOT.jar .

EXPOSE 8086

ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=prod", "developers-service-0.0.1-SNAPSHOT.jar"]