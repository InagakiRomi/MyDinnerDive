package com.romi.my_dinnerdive.controller;

import com.romi.my_dinnerdive.model.Restaurant;
import com.romi.my_dinnerdive.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private RestaurantService restaurantService;

    /**
     * 首頁：初次載入頁面會同時取得一筆隨機餐廳資料。
     * 若未來首頁要加上其他區塊，也不會影響抽籤功能。
     */
    @GetMapping("/")
    public ModelAndView index() {
        Restaurant random = restaurantService.getRandomRestaurant();
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("restaurant", random);
        return mav;
    }
}