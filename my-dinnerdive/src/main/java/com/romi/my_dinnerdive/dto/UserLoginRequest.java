package com.romi.my_dinnerdive.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/** 用來接收前端傳入的登入資訊，通常包含帳號與密碼，會在進入 Controller 前先進行欄位驗證 */
public class UserLoginRequest {
    
    /** 使用者帳號 */
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "帳號只能包含英文與數字")
    private String username;

    /** 使用者密碼 */
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
