package com.romi.my_dinnerdive;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 前端頁面控制器：ThymeleafController
 *
 * 處理導向 Thymeleaf 模板頁面的請求，將使用者導引至對應的 HTML 視圖。
 */
@Controller
public class ThymeleafController {

    /**
     * 導向首頁畫面（index.html）。
     *
     * 對應 Thymeleaf 模板目錄下的 {@code resources/templates/index.html}。
     *
     * @return 字串 "index"，作為 Thymeleaf 視圖名稱
     */
    @GetMapping("/dinnerHome")
    public String dinnerHome() {
        return "index";
    }
}