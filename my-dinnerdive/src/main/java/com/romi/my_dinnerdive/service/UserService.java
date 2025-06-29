package com.romi.my_dinnerdive.service;

import com.romi.my_dinnerdive.dto.UserRegisterRequest;
import com.romi.my_dinnerdive.model.User;

/**
 * 服務介面：UserService
 * 
 * 提供與使用者相關的業務邏輯處理，包括獲取使用者資訊和註冊新使用者。
 */
public interface UserService {

    /**
     * 根據使用者 ID 獲取使用者資訊。
     *
     * @param userId 使用者的 ID
     * @return 對應的 User 物件，如果找不到則返回 null
     */
    User getUserById(Integer userId);

    /**
     * 註冊新使用者。
     *
     * @param userRegisterRequest 包含使用者註冊資訊的請求物件
     * @return 新使用者的 ID
     */
    Integer register(UserRegisterRequest userRegisterRequest);
}
