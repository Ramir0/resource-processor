FROM openjdk:17.0.1-jdk-slim-buster

WORKDIR /app
COPY bootstrap/target/bootstrap-1.0.0.jar resource-processor.jar
EXPOSE 8083

CMD ["java", "-jar", "-Dspring.profiles.active=prod", "resource-processor.jar"]
