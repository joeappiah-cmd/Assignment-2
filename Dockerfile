# Stage 1: Build with Java 21
FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

WORKDIR /app

# Copy pom.xml separately for better caching
COPY pom.xml .
RUN mvn dependency:go-offline -DskipTests

# Copy the rest of the source code
COPY src ./src

# Build the application (skip tests for now)
RUN mvn package -DskipTests -Dmaven.repo.local=/root/.m2/repository

# Stage 2: Run with Java 21
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the generated JAR file from the build stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
