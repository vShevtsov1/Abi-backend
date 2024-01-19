FROM openjdk:21-slim-buster as build

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

RUN ./mvnw -B dependency:go-offline

COPY src src

RUN ./mvnw -B package

FROM openjdk:21-slim-buster

COPY --from=build target/photo-service-0.0.1-SNAPSHOT.jar .

EXPOSE 8088

ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=prod", "photo-service-0.0.1-SNAPSHOT.jar"]