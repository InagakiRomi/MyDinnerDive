package com.romi.my_dinnerdive.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig 設定 Spring Security 的核心配置。
 * 包含：授權規則、登入機制、密碼編碼器、認證管理器等。
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    /**
     * 設定安全性規則，包括每個 API 的授權條件與登入方式。
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                // 公開 API
                .requestMatchers(HttpMethod.GET, "/restaurants").permitAll()
                .requestMatchers(HttpMethod.GET, "/restaurants/{restaurantId}").permitAll()
                .requestMatchers(HttpMethod.GET, "/random").permitAll()
                .requestMatchers(HttpMethod.POST, "/clearRandom").permitAll()
                .requestMatchers(HttpMethod.POST, "/users/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/users/login").permitAll()

                // 管理員限定操作
                .requestMatchers(HttpMethod.DELETE, "/restaurants/{restaurantId}").hasAuthority("ADMIN")

                // 靜態資源與註冊頁面開放
                .requestMatchers("/dinnerHome/memberRegister", "/css/**", "/js/**").permitAll()

                // 其他請求都需要驗證身分
                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                // 自訂登入頁面：使用者未登入時，導向此頁（必須是 GET）
                .loginPage("/dinnerHome")

                // 登入處理的 POST URL，對應 login 表單的 action
                // Spring Security 會在這裡攔截並驗證帳號密碼
                .loginProcessingUrl("/users/login")

                // 登入成功後的導向路徑
                // 第二個參數為 true 表示「無論原本請求的是哪個頁面，都一律導向此頁」
                .defaultSuccessUrl("/dinnerHome/randomRestaurant", true)

                // 登入失敗時重新導向的頁面，可在頁面根據參數顯示錯誤訊息
                .failureUrl("/dinnerHome?error=true")

                // 允許未登入者訪問上述登入頁面和處理路徑
                .permitAll()
            )
            .logout(logout -> logout
                // 指定觸發登出的 URL（預設是 POST /logout）
                .logoutUrl("/logout")

                // 登出成功後導向的頁面，可加上 query param 讓前端顯示「成功登出」訊息
                .logoutSuccessUrl("/dinnerHome?logout=true")

                // 開放所有人（即使已經登出）都可以觸發 logout URL
                .permitAll()
            )
            .csrf(csrf -> csrf.disable())
            .build();
    }

    /**
     * 建立 AuthenticationManager，供 Spring Security 使用。
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * 密碼編碼器：使用 BCrypt 加密使用者密碼。
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

