package com.romi.my_dinnerdive.constant;

/** 定義帳號有哪些使用者，用於設定角色權限 */
public enum UserCategory {

    // 帳號分類，每個括號裡的是「對外顯示的中文名稱」
    ADMIN("管理員"),
    USER("一般使用者"),
    GUEST("訪客");

    // 用來存放「對外顯示的分類名稱」的變數，例如「管理員」
    private final String displayName;

    // 建構子：當 enum 被建立時，會把 displayName 的值設定進來
    UserCategory(String displayName) {
        this.displayName = displayName;
    }

    /** 取得分類的中文名稱 */
    public String getDisplayName() {
        return displayName;
    }
}
