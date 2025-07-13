package com.romi.my_dinnerdive.dto;

import com.romi.my_dinnerdive.constant.UserCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * 用戶註冊請求資料傳輸物件（DTO）
 * 此類別用來接收前端傳來的註冊資訊
 */
public class UserRegisterRequest {
    
    /**
     * 使用者帳號（只能包含英文字母與數字，不能為 null）
     */
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "帳號只能包含英文與數字")
    private String username;

    /**
     * 使用者密碼（不能為 null）
     */
    @NotBlank
    private String userPassword;

    /**
     * 使用者權限類型
     */
    private UserCategory roles;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public UserCategory getRoles() {
        return roles;
    }
    public void setRoles(UserCategory roles) {
        this.roles = roles;
    }
}
