package com.nt.restaurant.microservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.restaurant.microservice.dto.FoodItemOutDTO;
import com.nt.restaurant.microservice.service.FoodItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FoodItemController.class)
public class FoodItemControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FoodItemService foodItemService;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    objectMapper = new ObjectMapper();
  }

  @Test
  void getFoodItemsByCategoryTest() throws Exception {
    FoodItemOutDTO foodItemOutDTO = new FoodItemOutDTO();
    // Set foodItemOutDTO properties
    List<FoodItemOutDTO> foodItems = Arrays.asList(foodItemOutDTO);

    when(foodItemService.getFoodItemsByCategory(1)).thenReturn(foodItems);

    mockMvc.perform(MockMvcRequestBuilders.get("/foodItems/getFoodItem/{categoryId}", 1))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists());

    verify(foodItemService, times(1)).getFoodItemsByCategory(1);
  }

  @Test
  void getFoodItemsByRestaurantTest() throws Exception {
    FoodItemOutDTO foodItemOutDTO = new FoodItemOutDTO();
    // Set foodItemOutDTO properties
    List<FoodItemOutDTO> foodItems = Arrays.asList(foodItemOutDTO);

    when(foodItemService.getFoodItemsByRestaurant(1)).thenReturn(foodItems);

    mockMvc.perform(MockMvcRequestBuilders.get("/foodItems/getFoodItems/{restaurantId}", 1))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$[0]").exists());

    verify(foodItemService, times(1)).getFoodItemsByRestaurant(1);
  }

  @Test
  void getFoodItemImageTest() throws Exception {
    byte[] imageBytes = new byte[] {1, 2, 3}; // Example image data

    when(foodItemService.getFoodItemImage(1)).thenReturn(imageBytes);

    mockMvc.perform(MockMvcRequestBuilders.get("/foodItems/{id}/image", 1))
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.content().contentType(MediaType.IMAGE_JPEG))
      .andExpect(MockMvcResultMatchers.content().bytes(imageBytes));

    verify(foodItemService, times(1)).getFoodItemImage(1);
  }
}
