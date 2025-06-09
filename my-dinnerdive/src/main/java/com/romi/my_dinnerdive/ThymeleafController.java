package com.romi.my_dinnerdive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.romi.my_dinnerdive.model.Restaurant;
import com.romi.my_dinnerdive.service.RestaurantService;


/**
 * 前端頁面控制器：ThymeleafController
 *
 * 處理導向 Thymeleaf 模板頁面的請求，將使用者導引至對應的 HTML 視圖。
 */
@Controller
public class ThymeleafController {

    @Autowired
    private RestaurantService restaurantService;

    /**
     * 導向首頁畫面（index.html）。
     *
     * 對應 Thymeleaf 模板目錄下的 {@code resources/templates/index.html}。
     *
     * @return 字串 "index"，作為 Thymeleaf 視圖名稱
     */
    @GetMapping("/dinnerHome")
    public String dinnerHome(Model model) {
        // 從資料庫隨機獲取餐廳資訊
        Restaurant restaurant = restaurantService.getRandomRestaurant();
        
        // 將餐廳資訊添加到模型中，以便在 Thymeleaf 模板中使用
        model.addAttribute("restaurants", restaurant);

        // 返回 Thymeleaf 模板名稱
        return "index";
    }

    /**
     * 跳轉餐廳一覽頁面
     */
    @GetMapping("/listRestaurant")
    public String readPage() {
        return "dinnerHome/listRestaurant";
    }

    /**
     * 跳轉新增餐廳頁面
     */
    @GetMapping("/createRestaurant")
    public String createPage() {
        return "dinnerHome/createRestaurant";
    }

    /**
     * 跳轉修改對應ID餐廳頁面
     */
    @GetMapping("/restaurants/{restaurantId}/edit")
    public String updatePage() {
        return "dinnerHome/updateRestaurant";
    }
}