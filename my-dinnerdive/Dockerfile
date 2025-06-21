# Build stage
FROM maven:3.9.6-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Runtime stage
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*.jar my-dinnerdive.jar
ENTRYPOINT ["java", "-jar", "my-dinnerdive.jar"]