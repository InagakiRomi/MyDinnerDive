package com.romi.my_dinnerdive.dao;

import com.romi.my_dinnerdive.dto.UserRegisterRequest;
import com.romi.my_dinnerdive.model.User;

/** 定義與 users 資料表相關的資料操作方法 */
public interface UserDao {
    
    /** 根據帳號名稱查詢使用者資料 */
    User getUserById(Integer userId);

    /** 根據電子郵件獲取使用者資訊 */
    User getUserByUsername(String username);

    /** 建立新使用者帳號 */
    Integer createUser(UserRegisterRequest userRegisterRequest);
}
