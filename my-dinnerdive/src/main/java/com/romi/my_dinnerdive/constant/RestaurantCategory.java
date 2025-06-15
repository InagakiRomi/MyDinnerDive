package com.romi.my_dinnerdive.constant;

/**
 * 枚舉類別：RestaurantCategory
 * 
 * 定義可接受的餐廳類別，作為系統中餐廳分類的標準。 
 * 此列舉有助於限制輸入值，確保資料一致性與類型安全。
 */
public enum RestaurantCategory {

    MAIN("主食"),
    SNACK("輕食"),
    DRINK("飲料");

    private final String displayName;

    RestaurantCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
