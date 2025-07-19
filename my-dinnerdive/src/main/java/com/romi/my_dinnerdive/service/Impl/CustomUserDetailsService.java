package com.romi.my_dinnerdive.service.Impl;

import com.romi.my_dinnerdive.model.User;
import com.romi.my_dinnerdive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                     .orElseThrow(() -> new UsernameNotFoundException("找不到使用者" + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getUserPassword())
                .roles(user.getRoles().name()) // "ADMIN" or "USER"
                .build();
    }
}
