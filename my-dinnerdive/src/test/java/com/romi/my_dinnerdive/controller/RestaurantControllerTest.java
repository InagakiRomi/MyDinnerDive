package com.romi.my_dinnerdive.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.romi.my_dinnerdive.constant.RestaurantCategory;
import com.romi.my_dinnerdive.dto.RestaurantRequest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

@SpringBootTest
@AutoConfigureMockMvc
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getRestaurant_success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get("/restaurants/{restaurantId}", 1);

        mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.restaurantName", equalTo("兩藍拉麵")))
                .andExpect(jsonPath("$.category", equalTo("主食")))
                .andExpect(jsonPath("$.imageUrl", notNullValue()))
                .andExpect(jsonPath("$.visitedCount", notNullValue()))
                .andExpect(jsonPath("$.lastEat", notNullValue()))
                .andExpect(jsonPath("$.lastVisitedAt", notNullValue()))
                .andExpect(jsonPath("$.note", notNullValue()));
    }

    @Test
    public void getProduct_notFound() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/restaurants/{restaurants}", 20000);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(404));
    }

    @Transactional
    @Test
    void createRestaurant_success() throws Exception{
        RestaurantRequest restaurantRequest = new RestaurantRequest();
        restaurantRequest.setRestaurantName("都不NONO");
        restaurantRequest.setCategory(RestaurantCategory.飲料);
        restaurantRequest.setImageUrl("http://test.com");
        restaurantRequest.setNote("布丁五姊妹好喝");

        String json = objectMapper.writeValueAsString(restaurantRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.restaurantName", equalTo("都不NONO")))
                .andExpect(jsonPath("$.category", equalTo("飲料")))
                .andExpect(jsonPath("$.imageUrl", equalTo("http://test.com")))
                .andExpect(jsonPath("$.visitedCount", equalTo(0)))
	            .andExpect(jsonPath("$.lastEat", nullValue()))
                .andExpect(jsonPath("$.lastVisitedAt", notNullValue()))
                .andExpect(jsonPath("$.note", equalTo("布丁五姊妹好喝")));
    }

    @Transactional
    @Test
    void updateRestaurant_success() throws Exception{
        RestaurantRequest restaurantRequest = new RestaurantRequest();
        restaurantRequest.setRestaurantName("好棒棒喔");
        restaurantRequest.setCategory(RestaurantCategory.輕食);
        restaurantRequest.setImageUrl("http://test.food");
        restaurantRequest.setVisitedCount(6);
        restaurantRequest.setNote("肌肉猛男開的專賣店");

        String json = objectMapper.writeValueAsString(restaurantRequest);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/restaurants/{restaurantId}", 3)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.restaurantName", equalTo("好棒棒喔")))
                .andExpect(jsonPath("$.category", equalTo("輕食")))
                .andExpect(jsonPath("$.imageUrl", equalTo("http://test.food")))
                .andExpect(jsonPath("$.visitedCount", equalTo(6)))
	            .andExpect(jsonPath("$.lastEat", nullValue()))
                .andExpect(jsonPath("$.lastVisitedAt", notNullValue()))
                .andExpect(jsonPath("$.note", equalTo("肌肉猛男開的專賣店")));
    }

    @Transactional
    @Test
    void deleteRestaurant_success() throws Exception{

    }

    @Test
    void getRandomRestaurant_success() throws Exception{

    }

    @Transactional
    @Test
    void chooseRestaurant_success() throws Exception{

    }
}
