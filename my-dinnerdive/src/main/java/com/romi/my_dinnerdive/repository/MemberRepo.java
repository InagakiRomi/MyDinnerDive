package com.romi.my_dinnerdive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.romi.my_dinnerdive.model.User;

/**
 * MemberRepo 是操作 User 實體的資料存取介面。
 */
public interface MemberRepo extends JpaRepository<User, Long> {
    
    /**
     * 根據使用者名稱查詢 User。
     *
     * @param username 使用者帳號
     * @return 查詢到的 User 實體，若無則回傳 null
     */
    User findByUsername(String username);
}