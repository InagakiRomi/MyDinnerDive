package com.romi.my_dinnerdive;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.romi.my_dinnerdive.logging.LoggingDemo;
import com.romi.my_dinnerdive.model.Restaurant;
import com.romi.my_dinnerdive.model.User;
import com.romi.my_dinnerdive.repository.UserRepository;
import com.romi.my_dinnerdive.service.RestaurantService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


/** 頁面導向控制器（負責導到 Thymeleaf HTML 畫面） */
@Controller
public class ThymeleafController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoggingDemo loggingDemo;

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

    /** 使用者自動登入 */
    @GetMapping("/users/quickLogin")
    public String quickLogin(HttpServletRequest request) {
        Logger logger = loggingDemo.printUserLog();

        // 從資料庫找帳號是 guest 的使用者
        String quickUsername = "guest";
        Optional<User> optionalUser = userRepository.findByUsername(quickUsername);

        // 宣告一個變數來存取使用者
        User user = null;;

        // 檢查是否有找到使用者
        if (!optionalUser.isPresent()) {
            logger.log(Level.SEVERE, MessageFormat.format("該快速登入帳號 {0} 尚未被註冊，請用後台新增該帳號", quickUsername));
            return dinnerHome();
        }

        user = optionalUser.get();

        // 取得使用者的角色名稱，組合出權限字串，例如 "ROLE_ADMIN"
        String roleName = "ROLE_" + user.getRoles().name();

        // 建立權限物件
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName);

        // 放入一個 ArrayList
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);

        // 建立 Spring Security 的認證 token
        String username = user.getUsername();
        Object credentials = null; // 不需驗證密碼
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, credentials, authorities);

        //把建立好的認證物件放進 SecurityContext 中
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);

        // 告訴 Spring Security 目前的登入狀態
        SecurityContextHolder.setContext(context);

        // 建立一個新的 HTTP Session
        HttpSession session = request.getSession(true);

        // 把 SecurityContext 放到 Session 裡，讓下一次請求還記得這個登入狀態
        session.setAttribute("SPRING_SECURITY_CONTEXT", context);

        return randomRestaurantPage();
    }
}