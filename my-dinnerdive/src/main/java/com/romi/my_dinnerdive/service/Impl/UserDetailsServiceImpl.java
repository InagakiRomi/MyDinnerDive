package com.romi.my_dinnerdive.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.romi.my_dinnerdive.model.User;
import com.romi.my_dinnerdive.repository.UserRepository;

/** Spring Security 登入專用的使用者查詢服務，用於載入帳號資訊並建立 UserDetails */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 依帳號查詢使用者，轉換為 Spring Security 需要的 UserDetails 物件
     *
     * @param username ：使用者帳號
     * @return UserDetails（帳號、密碼、權限）
     * @throws UsernameNotFoundException 查無此帳號時拋出
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 從資料庫中查詢帳號，若找不到則拋出錯誤
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("使用者不存在: " + username));

        // 回傳包含帳號、密碼與角色資訊的 UserDetails 實例
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),                  // 使用者名稱
            user.getUserPassword(),             // 密碼（Spring Security 會自動做比對）
            List.of(new SimpleGrantedAuthority( // 權限清單（須加上 "ROLE_" 前綴）
                "ROLE_" + user.getRoles().name()
            ))
        );
    }
}