# Stage 1: Build with Maven
FROM maven:3.9.4-eclipse-temurin-17 AS builder
 
WORKDIR /app
 
# Copy pom.xml and source code into the container
COPY pom.xml .
COPY src ./src
 
# Run Maven to build the application (skip tests for now)
RUN mvn clean install -DskipTests
 
# Stage 2: Run with JDK
FROM openjdk:17-jdk-slim
 
WORKDIR /app
 
# Copy the generated JAR file from the build stage
COPY --from=builder /app/target/*.jar app.jar
 
# Expose the port that Spring Boot app is running on
EXPOSE 8080
 
# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]