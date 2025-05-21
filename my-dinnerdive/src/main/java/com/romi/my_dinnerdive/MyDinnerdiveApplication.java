package com.romi.my_dinnerdive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 應用程式進入點：MyDinnerdiveApplication
 * 
 * 此類別為 Spring Boot 應用的主啟動類，負責初始化 Spring 應用上下文並啟動內建伺服器。
 */
@SpringBootApplication // 啟用自動組態、元件掃描與 Spring Boot 機制
public class MyDinnerdiveApplication {

    /**
     * 主方法：啟動 Spring Boot 應用。
     *
     * @param args 執行參數
     */
    public static void main(String[] args) {
        SpringApplication.run(MyDinnerdiveApplication.class, args);
    }
}