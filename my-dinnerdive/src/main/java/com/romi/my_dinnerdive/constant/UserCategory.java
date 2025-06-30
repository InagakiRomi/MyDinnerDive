package com.romi.my_dinnerdive.constant;

public enum UserCategory {

    一般帳號("一般帳號"),
    管理員("管理員");

    private final String displayName;

    UserCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
