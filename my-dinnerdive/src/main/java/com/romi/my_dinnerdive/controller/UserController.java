package com.romi.my_dinnerdive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.romi.my_dinnerdive.config.SecurityHelper;
import com.romi.my_dinnerdive.constant.UserCategory;
import com.romi.my_dinnerdive.dao.UserDao;
import com.romi.my_dinnerdive.dto.UserLoginRequest;
import com.romi.my_dinnerdive.dto.UserRegisterRequest;
import com.romi.my_dinnerdive.model.User;
import com.romi.my_dinnerdive.service.UserService;

import jakarta.validation.Valid;

/** 提供與使用者帳號相關的 API，包括註冊與登入功能 */
@RestController
public class UserController {

    // 注入 UserService，負責帳號邏輯處理（如註冊、驗證）
    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private SecurityHelper securityHelper;

    /**
     * 註冊新使用者帳號
     *
     * @param userRegisterRequest ：前端傳入的註冊資料（包含帳號、密碼、信箱等），會先進行格式驗證
     * @param roles ：使用者角色，預設為 USER（如果前端未指定）
     * @return 註冊成功時回傳使用者資料與 HTTP 201 Created；失敗時回傳 HTTP 400 Bad Request（由 Service 處理例外）
     */
    @PostMapping("/users/register")
    public ResponseEntity<User> register(
            // 接收註冊表單資料 
            @RequestBody @Valid UserRegisterRequest userRegisterRequest,
            // 角色預設為 USER
            @RequestParam(defaultValue = "USER") UserCategory roles){
        
        // 如果前端沒傳角色，手動指定為 USER
        if (userRegisterRequest.getRoles() == null) {
        userRegisterRequest.setRoles(roles);
        }

        // 呼叫 service 建立帳號，回傳新帳號的 userId
        Integer userId = userService.register(userRegisterRequest);

        // 根據 ID 查詢該使用者資料
        User user = userService.getUserById(userId);

        // 回傳使用者資料與 HTTP 201 Created
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    /**
     * 使用者登入
     *
     * @param userLoginRequest ：前端傳入的登入資料（帳號與密碼），已加上格式驗證
     * @return 登入成功時回傳使用者資料與 HTTP 200 OK；失敗時由 Service 拋出例外並處理為 400
     */
    @PostMapping("/users/login")
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
        User user = userService.login(userLoginRequest); // 驗證帳密成功後返回 User

        // 建立 UserDetails 給 Spring Security 認得
        securityHelper.authenticateUser(user);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/users/quickLogin")
    public ResponseEntity<User> autoLogin() {
        // 假設這是你要自動登入的帳號
        String autoLoginUsername = "guest";

        // 取得帳號資料
        User user = userDao.getUserByUsername(autoLoginUsername);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "自動登入帳號不存在");
        }

        // 建立 UserDetails 給 Spring Security 認得
        securityHelper.authenticateUser(user);

        return ResponseEntity.ok(user);
    }
}
