FROM openjdk:17-jdk-slim as build

LABEL maintainer="nttdata"

COPY target/orders-0.0.1-SNAPSHOT.jar orders-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","/orders-0.0.1-SNAPSHOT.jar"]