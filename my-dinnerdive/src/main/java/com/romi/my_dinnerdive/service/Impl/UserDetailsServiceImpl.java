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

/**
 * UserDetailsServiceImpl 是 Spring Security 登入時的使用者資料查詢邏輯
 * 會自動被 Spring Security 調用以載入使用者資訊，並建立 UserDetails 物件
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 根據使用者名稱查找帳號，提供給 Spring Security 做登入驗證
     *
     * @param username 從表單提交過來的使用者名稱
     * @return UserDetails 包含帳號、密碼與授權角色資訊
     * @throws UsernameNotFoundException 若找不到帳號則丟出例外
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 從資料庫中查詢使用者，若找不到則拋出錯誤
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("使用者不存在: " + username));

        // 建立 Spring Security 的 UserDetails 實例
        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),                  // 使用者名稱
            user.getUserPassword(),             // 密碼（Spring Security 會自動做比對）
            List.of(new SimpleGrantedAuthority( // 權限清單（須加上 "ROLE_" 前綴）
                "ROLE_" + user.getRoles().name()
            ))
        );
    }
}