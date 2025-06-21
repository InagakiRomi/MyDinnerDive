# Build stage
FROM maven:3.8.3-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar my-dinnerdive.jar
ENTRYPOINT ["java", "-jar", "my-dinnerdive.jar"]