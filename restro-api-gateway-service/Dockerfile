FROM openjdk:17-slim

WORKDIR /app

ARG JAR_FILE

COPY target/${JAR_FILE}.jar api-gateway-service.jar

EXPOSE 8085

ENTRYPOINT ["java", "-jar", "api-gateway-service.jar"]
