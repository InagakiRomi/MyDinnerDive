package com.romi.my_dinnerdive.constant;

/**
 * UserCategory 枚舉類型，定義使用者帳號的分類。
 * 目前有兩種類別：管理員（ADMIN）與一般使用者（USER）。
 */
public enum UserCategory {

    // 管理員帳號
    ADMIN("管理員"),

    // 一般使用者帳號
    USER("一般帳號");

    // 顯示名稱，用來給使用者看到的名稱（例如前端畫面上）
    private final String displayName;

    /**
     * 建構子，透過傳入的 displayName 初始化列舉常數。
     *
     * @param displayName 顯示名稱（中文）
     */
    UserCategory(String displayName) {
        this.displayName = displayName;
    }

    /**
     * 取得顯示名稱。
     *
     * @return 中文的帳號類別名稱
     */
    public String getDisplayName() {
        return displayName;
    }
}