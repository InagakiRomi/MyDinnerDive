package com.romi.my_dinnerdive.config;

import com.romi.my_dinnerdive.filter.AuthRedirectFilter;
import com.romi.my_dinnerdive.service.Impl.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/** Spring Security 的主要設定類別，設定 API 權限、登入登出、密碼加密與自定過濾器等 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 自定義的使用者驗證服務（查詢使用者帳密與角色）
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    // 自定義登入後導向過濾器
    @Autowired
    private AuthRedirectFilter authRedirectFilter;

    // 是否允許所有 API 無需驗證（從 application-dev.yml 讀取）
    @Value("${security.permit-all:false}")
    private boolean permitAll;

    /** 定義 Spring Security 的安全過濾器鏈 (SecurityFilterChain) */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        
            // 加入自訂的過濾器，放在 UsernamePasswordAuthenticationFilter 前
            .addFilterBefore(authRedirectFilter, UsernamePasswordAuthenticationFilter.class)

            // 使用自定義的使用者驗證服務
            .userDetailsService(userDetailsServiceImpl)
            
            // 關閉 CSRF 保護（通常前後端分離或 REST API 會關）
            .csrf(csrf -> csrf.disable())

            // 設定各種路徑的授權規則
            .authorizeHttpRequests(auth -> {auth

                // 靜態資源與登入頁面、註冊頁面允許所有人訪問
                .requestMatchers("/css/**", "/js/**", "/images/**", "/dinnerHome", "/dinnerHome/memberRegister").permitAll()

                // 註冊 API 允許所有人 POST 存取
                .requestMatchers(HttpMethod.POST, "/users/register").permitAll();

                // 若設定為開放全部 API 權限，則所有請求皆允許
                if (permitAll) {
                    auth
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/**").permitAll();
                }

                // 其餘所有請求皆需驗證登入
                auth.anyRequest().authenticated();
            })

            // 登入相關設定
            .formLogin(login -> login
                // 登入頁面路徑（顯示登入表單）
                .loginPage("/dinnerHome")
                // 表單提交的處理 URL
                .loginProcessingUrl("/login")
                // 登入成功後重新導向的路徑（true = 每次都導向該頁）
                .defaultSuccessUrl("/dinnerHome/randomRestaurant", true)
                // 允許所有人訪問登入頁
                .permitAll()
            )

            // 登出相關設定
            .logout(logout -> logout
                // 登出提交的 URL
                .logoutUrl("/logout")
                // 登出成功後導向的頁面
                .logoutSuccessUrl("/dinnerHome?logout")
                // 允許所有人執行登出
                .permitAll()
            );

        return http.build();
    }

    /** 密碼編碼器設定：使用 BCrypt 加密演算法來存儲密碼（避免明文存入 DB）*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}