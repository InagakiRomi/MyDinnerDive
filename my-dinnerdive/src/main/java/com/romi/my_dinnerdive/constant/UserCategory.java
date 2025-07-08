package com.romi.my_dinnerdive.constant;

public enum UserCategory {

    ADMIN("管理員"),
    USER("一般帳號");

    private final String displayName;

    UserCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
