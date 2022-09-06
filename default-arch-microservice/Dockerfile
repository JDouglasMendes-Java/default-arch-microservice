FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM adoptopenjdk/openjdk11:alpine
LABEL MAINTAINER default-arch-microservice
COPY --from=build /home/app/target/default-arch-microservice-0.0.1-SNAPSHOT.jar /usr/local/lib/ms-api-1.0.0.jar
# COPY target/default-arch-microservice-0.0.1-SNAPSHOT.jar ms-api-1.0.0.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/ms-api-1.0.0.jar"]