# 建構階段
FROM eclipse-temurin:17-jdk AS builder
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# 運行階段
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/my-dinnerdive-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]