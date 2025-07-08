package com.romi.my_dinnerdive.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.romi.my_dinnerdive.constant.UserCategory;

public class User {
    private Integer userId;
    private String username;

    @JsonIgnore
    private String userPassword;

    private UserCategory roles;
    private Date createdDate;
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
