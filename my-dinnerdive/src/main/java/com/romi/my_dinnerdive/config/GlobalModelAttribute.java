package com.romi.my_dinnerdive.config;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/** 可以自動在所有的 @Controller 方法中加入共用資料 */
@ControllerAdvice
public class GlobalModelAttribute {

    /**
     * 這個方法會在每一次執行 Controller 裡的任何方法前被自動呼叫，並且將我們要加入的資料進 model 裡
     * @param model ：當前頁面的 Model，用來傳資料給 Thymeleaf
     * @param principal ：Spring Security 提供的登入使用者資訊
     */
    @ModelAttribute
    public void addUserInfoToModel(Model model, Principal principal) {
        // 判斷是否有登入（principal 不為 null 表示已登入）
        if (principal != null) {
            // 把登入使用者名稱放到 model 裡
            model.addAttribute("username", principal.getName());
        }
    }
}