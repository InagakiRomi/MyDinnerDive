package com.romi.my_dinnerdive.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserRegisterRequest {
    
    @NotNull
    @Pattern(regexp = "^[A-Za-z]+$", message = "帳號只能包含英文大小寫字母")
    private String account;

    @NotNull
    private String password;

    public String getAccount() {
        return account;
    }
    public void setAccount(String account) {
        this.account = account;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
