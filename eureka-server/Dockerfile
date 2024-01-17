FROM openjdk:21-slim-buster

WORKDIR /usr/app
COPY . /usr/app

RUN chmod +x mvnw
RUN ./mvnw -B dependency:go-offline
RUN ./mvnw -B package

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "target/eureka-server-0.0.1-SNAPSHOT.jar"]