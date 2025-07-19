package com.romi.my_dinnerdive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/dinnerHome").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(login -> login
                .loginPage("/dinnerHome")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/dinnerHome/randomRestaurant", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/dinnerHome?logout")
                .permitAll()
            );
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // 建立兩個用戶，有不同的權限
        UserDetails admin = User.withUsername("admin")
            .password("{noop}1234") // {noop} 表示不加密密碼
            .roles("ADMIN")
            .build();

        UserDetails user = User.withUsername("user")
            .password("{noop}1234")
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}