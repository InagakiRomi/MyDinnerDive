package com.romi.my_dinnerdive.config;

import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.romi.my_dinnerdive.model.User;

@Component
public class SecurityHelper {

    public void authenticateUser(User user) {
        // 建立 UserDetails 給 Spring Security 認得
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getUserPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_" + user.getRoles().name()))
        );

        // 建立 Authentication 並放進 SecurityContext
        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
