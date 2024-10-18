package com.nt.restaurant.microservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.restaurant.microservice.dto.CommonResponse;
import com.nt.restaurant.microservice.dto.FoodCategoryInDTO;
import com.nt.restaurant.microservice.dto.FoodCategoryOutDTO;
import com.nt.restaurant.microservice.service.FoodCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class FoodCategoryControllerTest {

  private MockMvc mockMvc;

  @Mock
  private FoodCategoryService foodCategoryService;

  @InjectMocks
  private FoodCategoryController foodCategoryController;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(foodCategoryController).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  void addFoodCategoryTest() throws Exception {
    FoodCategoryInDTO foodCategoryInDTO = new FoodCategoryInDTO();
    foodCategoryInDTO.setFoodCategoryName("Appetizers");
    foodCategoryInDTO.setRestaurantId(1);

    CommonResponse commonResponse = new CommonResponse();
    commonResponse.setMessage("Food category added successfully");

    when(foodCategoryService.addFoodCategory(foodCategoryInDTO)).thenReturn(commonResponse);

    mockMvc.perform(post("/categories/addFoodCategory")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(foodCategoryInDTO)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.message").value("Food category added successfully"));
  }

  @Test
  void getFoodCategoryByRestaurantIdTest() throws Exception {
    int restaurantId = 1;

    FoodCategoryOutDTO foodCategoryOutDTO = new FoodCategoryOutDTO();
    foodCategoryOutDTO.setFoodCategoryId(1);
    foodCategoryOutDTO.setFoodCategoryName("Appetizers");

    List<FoodCategoryOutDTO> foodCategories = Collections.singletonList(foodCategoryOutDTO);

    when(foodCategoryService.getFoodCategoryByRestaurantId(restaurantId)).thenReturn(foodCategories);

    mockMvc.perform(get("/categories/foodCategory/{restaurantId}", restaurantId))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].foodCategoryName").value("Appetizers"));

  }


  @Test
  void updateFoodCategoryTest() throws Exception {
    int categoryId = 1;
    FoodCategoryInDTO foodCategoryInDTO = new FoodCategoryInDTO();
    foodCategoryInDTO.setFoodCategoryName("Main Course");
    foodCategoryInDTO.setRestaurantId(1);

    CommonResponse commonResponse = new CommonResponse();
    commonResponse.setMessage("Food category updated successfully");

    when(foodCategoryService.updateFoodCategory(categoryId, foodCategoryInDTO)).thenReturn(commonResponse);

    mockMvc.perform(put("/categories/updateFoodCategory/{id}", categoryId)
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(foodCategoryInDTO)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.message").value("Food category updated successfully"));
  }
}
