FROM maven:3.9.0-eclipse-temurin-17 AS build

COPY . /app
WORKDIR /app

# 🐛 Debug 用：看看 /app 裡到底有沒有 pom.xml
RUN ls -al /app

# 🧪 Maven build（這個就是剛剛出錯的地方）
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
