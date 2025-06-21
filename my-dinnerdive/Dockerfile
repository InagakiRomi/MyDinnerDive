# Build jar
FROM maven:3.9.6-openjdk-17-slim AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Run jar
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/*.jar my-dinnerdive.jar
ENTRYPOINT ["java", "-jar", "my-dinnerdive.jar"]