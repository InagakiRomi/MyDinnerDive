package com.romi.my_dinnerdive.repository;

import com.romi.my_dinnerdive.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * UserRepository 是操作 User 實體資料的資料存取層 (DAO)
 * 繼承 JpaRepository，可以使用內建的 CRUD 操作與分頁查詢功能
 *
 * 第一個型別參數：User -> 指定實體類別
 * 第二個型別參數：Long -> 指定主鍵欄位的型別（你的 User 主鍵是 Integer，但這裡用 Long 會導致型別不一致 ❗）
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 根據使用者名稱查詢 User
     * Spring Data JPA 會自動依照命名規則解析成 SQL：
     * SELECT * FROM users WHERE username = ?
     */
    Optional<User> findByUsername(String username);
}