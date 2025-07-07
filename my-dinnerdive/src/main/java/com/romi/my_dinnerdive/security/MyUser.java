package com.romi.my_dinnerdive.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.romi.my_dinnerdive.model.User;

/**
 * MyUser 實作 UserDetails，作為 Spring Security 認證用的使用者包裝類別。
 * 將自定義 User 實體封裝成 Spring Security 所需的格式。
 */
public class MyUser implements UserDetails{

    private final User user;

    /**
     * 建構子：接收 User 實體以供後續封裝使用。
     *
     * @param user 系統內部的 User 實體
     */
    public MyUser(User user) {
        this.user = user;
    }

    /**
     * 回傳使用者的權限列表，這裡以 ROLE_ 為前綴建立授權物件。
     */
    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRoles().name()));
    }

    /**
     * 回傳使用者密碼（Spring Security 用來比對驗證）。
     */
    @Override
    public String getPassword() {
        return user.getMemberPassword();
    }

    /**
     * 回傳使用者帳號（作為登入名稱）。
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * 帳號是否未過期，true 代表有效。
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 帳號是否未被鎖定，true 代表可用。
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 憑證是否未過期，true 代表密碼仍有效。
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 使用者是否啟用，true 代表帳號已啟用。
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
