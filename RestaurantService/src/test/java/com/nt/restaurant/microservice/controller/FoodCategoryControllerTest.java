package com.nt.restaurant.microservice.controller;

import com.nt.restaurant.microservice.dto.CommonResponse;
import com.nt.restaurant.microservice.dto.FoodCategoryOutDTO;
import com.nt.restaurant.microservice.service.FoodCategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FoodCategoryControllerTest {

  @InjectMocks
  private FoodCategoryController foodCategoryController;

  @Mock
  private FoodCategoryService foodCategoryService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testAddFoodCategory_Success() {
    RestaurantController.FoodCategoryInDTO foodCategoryInDTO = new RestaurantController.FoodCategoryInDTO();
    foodCategoryInDTO.setRestaurantId(1);
    foodCategoryInDTO.setFoodCategoryName("Appetizers");

    CommonResponse commonResponse = new CommonResponse();
    commonResponse.setMessage("Food category added successfully");

    when(foodCategoryService.addFoodCategory(any(RestaurantController.FoodCategoryInDTO.class))).thenReturn(commonResponse);


    ResponseEntity<CommonResponse> response = foodCategoryController.addFoodCategory(foodCategoryInDTO);


    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals("Food category added successfully", response.getBody().getMessage());
    verify(foodCategoryService, times(1)).addFoodCategory(any(RestaurantController.FoodCategoryInDTO.class));
  }

  @Test
  void testGetFoodCategoryByRestaurantId_Success() {
    Integer restaurantId = 1;
    FoodCategoryOutDTO foodCategoryOutDTO = new FoodCategoryOutDTO();
    foodCategoryOutDTO.setFoodCategoryId(1);
    foodCategoryOutDTO.setFoodCategoryName("Appetizers");

    List<FoodCategoryOutDTO> foodCategoryList = new ArrayList<>();
    foodCategoryList.add(foodCategoryOutDTO);

    when(foodCategoryService.getFoodCategoryByRestaurantId(anyInt())).thenReturn(foodCategoryList);

    ResponseEntity<List<FoodCategoryOutDTO>> response = foodCategoryController.getFoodCategoryByRestaurantId(restaurantId);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertFalse(response.getBody().isEmpty());
    assertEquals("Appetizers", response.getBody().get(0).getFoodCategoryName());
    verify(foodCategoryService, times(1)).getFoodCategoryByRestaurantId(anyInt());
  }

  @Test
  void testUpdateFoodCategory_Success() {
    Integer id = 1;
    RestaurantController.FoodCategoryInDTO foodCategoryInDTO = new RestaurantController.FoodCategoryInDTO();
    foodCategoryInDTO.setRestaurantId(1);
    foodCategoryInDTO.setFoodCategoryName("Main Course");

    CommonResponse commonResponse = new CommonResponse();
    commonResponse.setMessage("Food category updated successfully");

    when(foodCategoryService.updateFoodCategory(anyInt(),
      any(RestaurantController.FoodCategoryInDTO.class))).thenReturn(commonResponse);

    ResponseEntity<CommonResponse> response = foodCategoryController.updateFoodCategory(id, foodCategoryInDTO);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals("Food category updated successfully", response.getBody().getMessage());
    verify(foodCategoryService, times(1)).updateFoodCategory(anyInt(), any(RestaurantController.FoodCategoryInDTO.class));
  }
}

