package com.romi.my_dinnerdive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.romi.my_dinnerdive.constant.UserCategory;
import com.romi.my_dinnerdive.dto.UserLoginRequest;
import com.romi.my_dinnerdive.dto.UserRegisterRequest;
import com.romi.my_dinnerdive.model.User;
import com.romi.my_dinnerdive.service.UserService;

import jakarta.validation.Valid;

/**
 * 控制器類別：UserController
 * 
 * 提供與使用者相關的 RESTful API，包含註冊新使用者的功能。
 */
@RestController
public class UserController {
    
    @Autowired
    private UserService userService;

    /**
     * 註冊新使用者。
     *
     * @param userRegisterRequest 包含使用者註冊資訊的請求物件
     * @return 若註冊成功，回傳新使用者資料與 HTTP 201；否則回傳 HTTP 400。
     */
    @PostMapping("/users/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest,
                                         @RequestParam(defaultValue = "USER") UserCategory roles){
        
        if (userRegisterRequest.getRoles() == null) {
        userRegisterRequest.setRoles(roles);
        }

        Integer userId = userService.register(userRegisterRequest);
        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    /**
     * 使用者登入。
     *
     * @param userLoginRequest 包含使用者登入資訊的請求物件
     * @return 若登入成功，回傳使用者資料與 HTTP 200；否則回傳 HTTP 400。
     */
    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
        User user = userService.login(userLoginRequest);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
