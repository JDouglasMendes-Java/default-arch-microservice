FROM adoptopenjdk/openjdk11:alpine
LABEL MAINTAINER default-arch-microservice
COPY target/default-arch-microservice-0.0.1-SNAPSHOT.jar ms-api-1.0.0.jar
ENTRYPOINT ["java","-jar","/ms-api-1.0.0.jar"]