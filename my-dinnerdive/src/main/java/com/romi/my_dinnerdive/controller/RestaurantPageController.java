package com.romi.my_dinnerdive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 餐廳相關頁面控制器
 * 
 * 提供餐廳相關的靜態頁面路由，包含讀取、建立、更新和刪除餐廳的頁面。
 */
@Controller
@RequestMapping("/dinnerHome")
public class RestaurantPageController {

    /**
     * 跳轉讀取餐廳頁面
     */
    @GetMapping("/read")
    public String readPage() {
        return "dinnerHome/read";
    }

    /**
     * 跳轉新增餐廳頁面
     */
    @GetMapping("/create")
    public String createPage() {
        return "dinnerHome/create";
    }

    /**
     * 跳轉修改餐廳頁面
     */
    @GetMapping("/update")
    public String updatePage() {
        return "dinnerHome/update";
    }

    /**
     * 跳轉刪除餐廳頁面
     */
    @GetMapping("/delete")
    public String deletePage() {
        return "dinnerHome/delete";
    }
}
