package com.romi.my_dinnerdive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.romi.my_dinnerdive.model.Restaurant;
import com.romi.my_dinnerdive.service.RestaurantService;


/** 頁面導向控制器（負責導到 Thymeleaf HTML 畫面） */
@Controller
public class ThymeleafController {

    @Autowired
    private RestaurantService restaurantService;

    /** 登入頁面 */
    @GetMapping("/dinnerHome")
    public String dinnerHome() {
        return "index";
    }
    
    /** 抽選餐廳頁面 */
    @GetMapping("/dinnerHome/randomRestaurant")
    public String randomRestaurantPage() {
        return "dinnerHome/randomRestaurant";
    }
    
    /** 餐廳一覽頁面 */
    @GetMapping("dinnerHome/listRestaurant")
    public String readPage() {
        return "dinnerHome/listRestaurant";
    }

    /** 新增餐廳頁面 */
    @GetMapping("dinnerHome/createRestaurant")
    public String createPage() {
        return "dinnerHome/createRestaurant";
    }

    /** 修改餐廳資料頁面 */
    @GetMapping("dinnerHome/restaurants/{restaurantId}/edit")
    public String updatePage(@PathVariable Integer restaurantId, Model model) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        model.addAttribute("restaurants", restaurant);
        return "dinnerHome/updateRestaurant";
    }

    /** 使用者註冊頁面 */
    @GetMapping("/dinnerHome/memberRegister")
    public String memberRegister() {
        return "dinnerHome/memberRegister";
    }
}