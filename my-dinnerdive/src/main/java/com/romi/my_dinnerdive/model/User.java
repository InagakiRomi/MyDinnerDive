package com.romi.my_dinnerdive.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.romi.my_dinnerdive.constant.UserCategory;

import jakarta.persistence.*;

/**
 * User 實體類別對應到資料庫中的 users 資料表
 */
@Entity
@Table(name = "users") // 指定資料表名稱為 "users"
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主鍵自動生成（MySQL 使用 AUTO_INCREMENT）
    private Integer userId;

    @Column(nullable = false, unique = true) // 帳號名稱不可為 null 且不可重複
    private String username;

    @JsonIgnore // 不讓密碼被序列化到 JSON（保護資料安全）
    @Column(nullable = false) // 密碼不可為 null
    private String userPassword;

    @Enumerated(EnumType.STRING) // 枚舉以字串形式存入資料庫（例如：ADMIN, USER）
    private UserCategory roles;

    @Temporal(TemporalType.TIMESTAMP) // 儲存建立時間，使用 TIMESTAMP 格式
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP) // 儲存最後修改時間，使用 TIMESTAMP 格式
    private Date lastModifiedDate;

    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    public UserCategory getRoles() {
        return roles;
    }
    public void setRoles(UserCategory roles) {
        this.roles = roles;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }
    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
