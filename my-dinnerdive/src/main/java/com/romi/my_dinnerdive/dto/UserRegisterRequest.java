package com.romi.my_dinnerdive.dto;

import com.romi.my_dinnerdive.constant.UserCategory;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * 用戶註冊請求資料傳輸物件（DTO）
 * 此類別用來接收前端傳來的註冊資訊
 */
public class UserRegisterRequest {
    
    /**
     * 使用者帳號（只能包含英文字母與數字，不能為 null）
     */
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "帳號只能包含英文與數字")
    private String account;

    /**
     * 使用者密碼（不能為 null）
     */
    @NotNull
    private String memberPassword;

    /**
     * 使用者權限類型
     */
    private UserCategory roles;

    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getMemberPassword() {
        return memberPassword;
    }
    public void setMemberPassword(String memberPassword) {
        this.memberPassword = memberPassword;
    }
    public UserCategory getRoles() {
        return roles;
    }
    public void setRoles(UserCategory roles) {
        this.roles = roles;
    }
}
