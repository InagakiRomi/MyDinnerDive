package com.romi.my_dinnerdive.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.romi.my_dinnerdive.service.Impl.CustomUserDetailsService;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/css/**", "/js/**", "/images/**", "/dinnerHome", "/dinnerHome/memberRegister").permitAll()
                .requestMatchers(HttpMethod.POST, "/users/register", "/users/login").permitAll()
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
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 密碼加密用
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }
}