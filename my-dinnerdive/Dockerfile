FROM maven:3.9.0-eclipse-temurin-17 AS build
COPY . /app
WORKDIR /app/my-dinnerdive
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
COPY --from=build /app/my-dinnerdive/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]