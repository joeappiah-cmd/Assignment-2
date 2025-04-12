# Stage 1: Build
FROM maven:3.9.9-eclipse-temurin-17-alpine AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -DskipTests
COPY src ./src
RUN mvn package -DskipTests -Dmaven.repo.local=/root/.m2/repository

# Stage 2: Run
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
