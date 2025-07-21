package com.romi.my_dinnerdive.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 自訂過濾器：當使用者已登入且訪問 /dinnerHome 或 /dinnerHome/memberRegister 時，自動導向 /dinnerHome/randomRestaurant
 */
@Component
public class AuthRedirectFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        // 取得目前請求的 URI
        String path = request.getRequestURI();

        // 取得目前使用者的驗證資訊
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // 判斷是否為已登入使用者（不是匿名使用者且驗證成功）
        boolean isLoggedIn = auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal());

        // 若使用者已登入且請求目標為 /dinnerHome 或 /dinnerHome/memberRegister，則進行導向
        if (isLoggedIn && ("/dinnerHome".equals(path) || "/dinnerHome/memberRegister".equals(path))) {
            // 導向至 /dinnerHome/randomRestaurant
            response.sendRedirect("/dinnerHome/randomRestaurant");
            return;
        }

        // 若不符合導向條件，繼續執行接下來的 Filter Chain
        filterChain.doFilter(request, response);
    }
}