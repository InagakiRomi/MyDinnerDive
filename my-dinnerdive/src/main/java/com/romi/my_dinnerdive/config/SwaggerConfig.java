package com.romi.my_dinnerdive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

/** 負責設定 API 文件的標題、版本資訊，以及設定基本的安全驗證機制（HTTP Basic 認證） */
@Configuration
public class SwaggerConfig {

    /**
     * 設定 OpenAPI 主體，包括基本資訊與安全機制
     * @return OpenAPI 物件，用於產生 Swagger 文件
     */
    @Bean
    public OpenAPI openAPI() {
        // API 的基本資訊，標題和版本號
        Info info = new Info()
                .title("My Spring Boot API Document")
                .version("1.0.0");

        // 設定安全驗證的名稱
        var basicSchemeName = "HTTP Basic Auth";

        // 建立一個安全需求，指定我們的 API 使用 basic 認證
        var securityRequirement = new SecurityRequirement()
                .addList(basicSchemeName);

        // 建立認證的詳細設定
        var components = new Components()
                .addSecuritySchemes(basicSchemeName,            // 名稱要跟上面一致
                        new SecurityScheme()
                                .name(basicSchemeName)          // 顯示在 Swagger UI 上的名稱
                                .type(SecurityScheme.Type.HTTP) // 使用 HTTP 認證類型
                                .scheme("basic"));              // 指定是 basic 認證（帳號密碼）

        // 最終回傳的 OpenAPI 物件，包含資訊、認證需求與元件設定
        return new OpenAPI()
                .info(info)                           // 加入基本 API 資訊
                .addSecurityItem(securityRequirement) // 加入認證需求
                .components(components);              // 加入安全設定元件
    }
}