package com.romi.my_dinnerdive.service.Impl;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

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
    
    @Override
    public User getUserById(Integer userId){
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest){

        Logger logger = loggingDemo.printUserLog();

        // 檢查註冊的帳號
        User user = userDao.getUserByAccount(userRegisterRequest.getAccount());
        
        if(user != null){
            logger.log(Level.WARNING, MessageFormat.format("該帳號 {0} 已經被註冊", userRegisterRequest.getAccount()));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //使用 MD5 生成密碼的雜湊值
        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
        userRegisterRequest.setPassword(hashedPassword);

        // 創建帳號
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest){
        Logger logger = loggingDemo.printUserLog();
        
        User user = userDao.getUserByAccount(userLoginRequest.getAccount());

        //檢查 user 是否存在
        if(user == null){
            logger.log(Level.WARNING, MessageFormat.format("該帳號 {0} 尚未註冊", userLoginRequest.getAccount()));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
  
        //使用 MD5 生成密碼的雜湊值
        String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());

        //比較密碼
        if(user.getPassword().equals(hashedPassword)){
            return user;
        }else{
            logger.log(Level.WARNING, MessageFormat.format("帳號 {0} 的密碼不正確", userLoginRequest.getAccount()));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
