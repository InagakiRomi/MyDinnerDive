package com.romi.my_dinnerdive.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @Test
    void getRestaurants_success() {

    }

    @Test
    void getRestaurant_success() {

    }

    @Transactional
    @Test
    void createRestaurant_success() {

    }

    @Transactional
    @Test
    void updateRestaurant_success() {
        
    }

    @Transactional
    @Test
    void deleteRestaurant_success() {

    }

    @Test
    void getRandomRestaurant_success() {

    }

    @Transactional
    @Test
    void chooseRestaurant_success() {

    }
}
