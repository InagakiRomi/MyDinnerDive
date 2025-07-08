package com.romi.my_dinnerdive.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * 使用者登入請求資料傳輸物件（DTO）
 * 用於接收前端登入請求的帳號與密碼
 */
public class UserLoginRequest {
    
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
}
