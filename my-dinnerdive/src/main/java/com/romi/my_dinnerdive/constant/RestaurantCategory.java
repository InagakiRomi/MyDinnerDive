package com.romi.my_dinnerdive.constant;

/**
 * 餐廳分類列舉（用於餐廳分類）
 */
public enum RestaurantCategory {

    MAIN("主食"),
    SNACK("輕食"),
    DRINK("飲料");

    // 顯示名稱，對應中文標籤或 UI 呈現使用
    private final String displayName;

    // 建構子：將顯示名稱傳入枚舉
    RestaurantCategory(String displayName) {
        this.displayName = displayName;
    }

    /**
     * 取得該餐廳的分類（例如：在前端顯示「主食」或「輕食」）
     */
    public String getDisplayName() {
        return displayName;
    }
}
