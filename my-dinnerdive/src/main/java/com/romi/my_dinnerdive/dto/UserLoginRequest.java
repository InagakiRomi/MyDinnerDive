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
    private String account;

    /**
     * 使用者密碼（不能為 null）
     */
    @NotBlank
    private String memberPassword;

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
}
