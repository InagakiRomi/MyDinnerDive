package com.romi.my_dinnerdive;

import java.util.logging.*;

import com.romi.my_dinnerdive.logging.LoggingDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 應用程式進入點：MyDinnerdiveApplication
 * 
 * 此類別為 Spring Boot 應用的主啟動類，負責初始化 Spring 應用上下文並啟動內建伺服器。
 */
@SpringBootApplication
@EntityScan(basePackages = "com.romi.my_dinnerdive.model")
@EnableJpaRepositories(basePackages = "com.romi.my_dinnerdive.repository")
public class MyDinnerdiveApplication {

    /**
     * 主方法：啟動 Spring Boot 應用。
     *
     * @param args 執行參數
     */
     public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MyDinnerdiveApplication.class, args);

        LoggingDemo loggingDemo = context.getBean(LoggingDemo.class);
        Logger logger = loggingDemo.printMainLog();
        logger.log(Level.INFO, "可啟動應用程式，請輸入 http://localhost:8080/dinnerHome 來存取應用程式。");
    }
}