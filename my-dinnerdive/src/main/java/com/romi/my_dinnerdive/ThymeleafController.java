package com.romi.my_dinnerdive;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {
    @GetMapping("/dinnerHome")
    public String dinnerHome(){
            return "index";
    }
}
