# Build stage
FROM maven:3.8.6-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
ARG JAR_FILE
RUN mvn clean package -DskipTests


# Run stage
FROM openjdk:17-slim

WORKDIR /app

ARG JAR_FILE

COPY target/${JAR_FILE}.jar auth-service.jar

RUN apt-get update && apt-get install -y wait-for-it

EXPOSE 8085

ENTRYPOINT ["wait-for-it", "restro-postgres-db:5432", "--", "java", "-jar", "auth-service.jar"]
