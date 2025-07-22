package com.romi.my_dinnerdive.repository;

import com.romi.my_dinnerdive.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 繼承 JpaRepository 提供以下功能：<p>
 * - 基本 CRUD 操作（如 save, findById, deleteById）<p>
 * - 分頁與排序（如 findAll(Sort)、findAll(Pageable)）<p>
 * - 批次處理（如 saveAllAndFlush, deleteAllInBatch）<p>
 * - 自動解析命名查詢（如 findByXxx, existsByXxx）
 * <p>
 * 泛型說明：
 * - User：對應操作的實體類別<p>
 * - Integer：User 的主鍵型別（對應 userId）
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 根據帳號查詢使用者（由 Spring Data JPA 自動轉成 SQL）
     * <p>
     * 對應查詢語句：SELECT * FROM users WHERE username = ?
     *
     * @param username 使用者帳號
     * @return 查到的使用者（用 Optional 包裝，避免回傳 null）
     */
    Optional<User> findByUsername(String username);
}