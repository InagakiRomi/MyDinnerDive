package com.romi.my_dinnerdive.dao;

import com.romi.my_dinnerdive.dto.UserRegisterRequest;
import com.romi.my_dinnerdive.model.User;

/**
 * 使用者 DAO 介面，定義與資料庫互動的方法。
 */
public interface UserDao {
    
    /**
     * 根據使用者 ID 獲取使用者資訊。
     *
     * @param userId 使用者的 ID
     * @return 對應的 User 物件，如果找不到則返回 null
     */
    User getUserById(Integer userId);

    /**
     * 根據電子郵件獲取使用者資訊。
     *
     * @param email 使用者的電子郵件
     * @return 對應的 User 物件，如果找不到則返回 null
     */
    User getUserByEmail(String email);

    /**
     * 註冊新使用者。
     *
     * @param userRegisterRequest 包含使用者註冊資訊的請求物件
     * @return 新使用者的 ID
     */
    Integer createUser(UserRegisterRequest userRegisterRequest);
}
