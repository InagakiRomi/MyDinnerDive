# -------- Build Stage --------
FROM maven:3.8.3-openjdk-17-slim AS build

WORKDIR /app

# 只複製必要檔案，避免 .git、.vscode 等多餘內容進入 image
COPY pom.xml .
COPY src ./src

# Maven package，跳過測試
RUN mvn clean package -DskipTests


# -------- Runtime Stage --------
FROM openjdk:17-jdk-slim

WORKDIR /app

# 複製 build 完成的 JAR 檔案到 runtime container
COPY --from=build /app/target/*.jar my-dinnerdive.jar

# 啟動應用程式
ENTRYPOINT ["java", "-jar", "my-dinnerdive.jar"]