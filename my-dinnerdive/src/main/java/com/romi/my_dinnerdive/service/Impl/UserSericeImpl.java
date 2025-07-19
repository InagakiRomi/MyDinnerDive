package com.romi.my_dinnerdive.service.Impl;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.romi.my_dinnerdive.constant.UserCategory;
import com.romi.my_dinnerdive.dao.UserDao;
import com.romi.my_dinnerdive.dto.UserLoginRequest;
import com.romi.my_dinnerdive.dto.UserRegisterRequest;
import com.romi.my_dinnerdive.logging.LoggingDemo;
import com.romi.my_dinnerdive.model.User;
import com.romi.my_dinnerdive.service.UserService;

@Component
public class UserSericeImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoggingDemo loggingDemo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public User getUserById(Integer userId){
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest){

        Logger logger = loggingDemo.printUserLog();

        // 檢查帳號是否已存在
        User existing = userDao.getUserByUsername(userRegisterRequest.getUsername());
        if (existing != null) {
            logger.log(Level.WARNING, MessageFormat.format("該帳號 {0} 已經被註冊", userRegisterRequest.getUsername()));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "帳號已存在");
        }

        // 密碼加密
        String hashedPassword = passwordEncoder.encode(userRegisterRequest.getUserPassword());
        userRegisterRequest.setUserPassword(hashedPassword);

        // 設定預設角色
        if (userRegisterRequest.getRoles() == null) {
            userRegisterRequest.setRoles(UserCategory.USER); // 預設 USER
        }

        // 建立使用者
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest){
        Logger logger = loggingDemo.printUserLog();

        // 找到使用者
        User user = userDao.getUserByUsername(userLoginRequest.getUsername());

        if (user == null) {
            logger.log(Level.WARNING, MessageFormat.format("該帳號 {0} 尚未註冊", userLoginRequest.getUsername()));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "帳號不存在");
        }

        // 使用 BCrypt 驗證密碼
        if (passwordEncoder.matches(userLoginRequest.getUserPassword(), user.getUserPassword())) {
            return user;
        } else {
            logger.log(Level.WARNING, MessageFormat.format("帳號 {0} 的密碼不正確", userLoginRequest.getUsername()));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "密碼錯誤");
        }
    }
}
