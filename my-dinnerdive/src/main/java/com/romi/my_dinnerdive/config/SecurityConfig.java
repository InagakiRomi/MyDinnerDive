package com.romi.my_dinnerdive.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.romi.my_dinnerdive.service.Impl.UserDetailsServiceImpl;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security 的主要設定類別
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 注入自定義的 UserDetailsService 實作，用於驗證使用者資訊
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    /**
     * 定義 Spring Security 的安全過濾器鏈 (Filter Chain)
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 設定使用自定義的 UserDetailsService
            .userDetailsService(userDetailsServiceImpl)
            
            // 關閉 CSRF（跨站請求偽造）保護（通常在 API 或前後端分離架構中關閉）
            .csrf(csrf -> csrf.disable())

            // 設定授權規則
            .authorizeHttpRequests(auth -> auth

                // 允許所有使用者存取靜態資源與首頁及註冊頁面
                .requestMatchers("/css/**", "/js/**", "/images/**", "/dinnerHome", "/dinnerHome/memberRegister").permitAll()

                // 允許所有使用者透過 POST 方法存取註冊 API
                .requestMatchers(HttpMethod.POST, "/users/register").permitAll()

                // 若開放全部 API 權限，可取消註解以下設定
                // .requestMatchers(HttpMethod.GET, "/**").permitAll()
                // .requestMatchers(HttpMethod.POST, "/**").permitAll()
                // .requestMatchers(HttpMethod.DELETE, "/**").permitAll()
                // .requestMatchers(HttpMethod.PUT, "/**").permitAll()
                // .requestMatchers(HttpMethod.PATCH, "/**").permitAll()

                // 其他所有請求都需要先驗證登入
                .anyRequest().authenticated()
            )

            // 設定登入表單的相關路由與行為
            .formLogin(login -> login
                // 自訂登入頁面路徑
                .loginPage("/dinnerHome")
                // 登入表單提交的 URL
                .loginProcessingUrl("/login")
                // 登入成功後導向的 URL
                .defaultSuccessUrl("/dinnerHome/randomRestaurant", true)
                // 允許所有使用者存取登入頁
                .permitAll()
            )

            // 登出相關設定
            .logout(logout -> logout
                // 指定登出的 URL
                .logoutUrl("/logout")
                // 登出成功後導向的頁面
                .logoutSuccessUrl("/dinnerHome?logout")
                // 允許所有使用者執行登出
                .permitAll()
            );

        // 回傳組建完成的 SecurityFilterChain
        return http.build();
    }

    /**
     * 密碼編碼器：使用 BCrypt 演算法加密密碼
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}