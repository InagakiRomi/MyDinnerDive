# 使用含 Maven 的 Java 開發環境（Java 17），用來建置專案
FROM maven:3.9.0-eclipse-temurin-17 AS build
# 複製專案資料夾中的所有檔案到容器的 /app 目錄
COPY . /app
# 設定工作目錄為 /app/my-dinnerdive（即專案的主目錄）
WORKDIR /app/my-dinnerdive
# 使用 Maven 編譯專案並打包成 .jar 檔（跳過測試）
RUN mvn clean package -DskipTests

# 建構正式執行環境，使用 Java 17 的輕量版執行環境（不含 Maven）
FROM eclipse-temurin:17-jre
# 從前面 build 階段複製打包完成的 .jar 檔到現在這個容器中，並命名為 app.jar
COPY --from=build /app/my-dinnerdive/target/*.jar app.jar
# 開放容器的 8080 port，讓外部可以連進來（Spring Boot 預設使用 8080）
EXPOSE 8080
# 啟動容器時執行的指令：用 Java 執行剛剛打包好的 app.jar
CMD ["java", "-jar", "app.jar"]