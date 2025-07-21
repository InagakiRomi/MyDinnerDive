package com.romi.my_dinnerdive.constant;

/**
 * 使用者分類列舉（用於設定角色權限）
 */
public enum UserCategory {

    // 管理員帳號
    ADMIN("管理員"),

    // 一般使用者帳號
    USER("一般帳號"),

     // 訪客帳號
    GUEST("訪客");

    // 顯示名稱，對應中文標籤或 UI 呈現使用
    private final String displayName;

    // 建構子：將顯示名稱傳入枚舉
    UserCategory(String displayName) {
        this.displayName = displayName;
    }

    /**
     * 取得該角色的顯示名稱（例如：在前端顯示「管理員」或「一般帳號」）
     */
    public String getDisplayName() {
        return displayName;
    }
}
