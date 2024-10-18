package com.nt.restaurant.microservice.controller;

import com.nt.restaurant.microservice.dto.CommonResponse;
import com.nt.restaurant.microservice.dto.FoodItemInDTO;
import com.nt.restaurant.microservice.dto.FoodItemOutDTO;
import com.nt.restaurant.microservice.dto.FoodItemUpdateInDTO;
import com.nt.restaurant.microservice.entities.FoodItem;
import com.nt.restaurant.microservice.service.FoodItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class FoodItemControllerTest {

  private MockMvc mockMvc;

  @InjectMocks
  private FoodItemController foodItemController;

  @Mock
  private FoodItemService foodItemService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(foodItemController).build();
  }



  @Test
  void addFoodItemTest() throws Exception {
    FoodItemInDTO foodItemInDTO = new FoodItemInDTO();
    foodItemInDTO.setFoodItemName("Sample Food");
    foodItemInDTO.setRestaurantId(1);
    foodItemInDTO.setFoodCategoryId(1);
    foodItemInDTO.setDescription("Delicious sample food item");
    foodItemInDTO.setPrice(10.99);
    foodItemInDTO.setAvailable(true);

    MockMultipartFile image = new MockMultipartFile("foodItemImage", "sampleImage.jpg", MediaType.IMAGE_JPEG_VALUE, "image content".getBytes());

    CommonResponse commonResponse = new CommonResponse();
    commonResponse.setMessage("Food item added successfully");

    when(foodItemService.addFoodItem(any(FoodItemInDTO.class), any())).thenReturn(commonResponse);

    mockMvc.perform(MockMvcRequestBuilders.multipart("/foodItems/addFoodItem")
        .file(image)
        .param("foodItemName", foodItemInDTO.getFoodItemName())
        .param("restaurantId", String.valueOf(foodItemInDTO.getRestaurantId()))
        .param("foodCategoryId", String.valueOf(foodItemInDTO.getFoodCategoryId()))
        .param("description", foodItemInDTO.getDescription())
        .param("price", String.valueOf(foodItemInDTO.getPrice())) // Send price
        .param("isAvailable", String.valueOf(foodItemInDTO.isAvailable()))
        .contentType(MediaType.MULTIPART_FORM_DATA))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.message").value("Food item added successfully"));
  }

  @Test
  void getFoodItemsByCategoryTest() throws Exception {
    Integer categoryId = 1;
    FoodItemOutDTO foodItemOutDTO = new FoodItemOutDTO();
    foodItemOutDTO.setFoodItemName("Sample Food");

    when(foodItemService.getFoodItemsByCategory(categoryId)).thenReturn(Collections.singletonList(foodItemOutDTO));

    mockMvc.perform(MockMvcRequestBuilders.get("/foodItems/getFoodItem/{categoryId}", categoryId)
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].foodItemName").value("Sample Food"));
  }

  @Test
  void getFoodItemsByRestaurantTest() throws Exception {
    Integer restaurantId = 1;
    FoodItemOutDTO foodItemOutDTO = new FoodItemOutDTO();
    foodItemOutDTO.setFoodItemName("Sample Restaurant Food");

    when(foodItemService.getFoodItemsByRestaurant(restaurantId)).thenReturn(Collections.singletonList(foodItemOutDTO));

    mockMvc.perform(MockMvcRequestBuilders.get("/foodItems/getFoodItems/{restaurantId}", restaurantId)
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].foodItemName").value("Sample Restaurant Food"));
  }


  @Test
  void updateFoodItemByFoodItemIdTest() throws Exception {
    Integer foodItemId = 1;
    FoodItemUpdateInDTO foodItemUpdateInDTO = new FoodItemUpdateInDTO();
    foodItemUpdateInDTO.setFoodItemName("Updated Food");
    foodItemUpdateInDTO.setDescription("Updated Description");
    foodItemUpdateInDTO.setPrice(10.99);

    CommonResponse commonResponse = new CommonResponse();
    commonResponse.setMessage("Food item updated successfully");

    when(foodItemService.updateFoodItemByFoodItemId(anyInt(), any(FoodItemUpdateInDTO.class)))
      .thenReturn(commonResponse);

    mockMvc.perform(MockMvcRequestBuilders.put("/foodItems/updateFoodItem/{foodItemId}", foodItemId)
        .param("foodItemName", foodItemUpdateInDTO.getFoodItemName())
        .param("description", foodItemUpdateInDTO.getDescription())
        .param("price", foodItemUpdateInDTO.getPrice().toString())
        .contentType(MediaType.MULTIPART_FORM_DATA))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.message").value("Food item updated successfully"));
  }

  @Test
  void getFoodItemByIdTest() throws Exception {
    Integer foodItemId = 1;
    FoodItem foodItem = new FoodItem();
    foodItem.setFoodItemName("Food Item Detail");

    when(foodItemService.findFoodItemById(foodItemId)).thenReturn(foodItem);

    mockMvc.perform(MockMvcRequestBuilders.get("/foodItems/{foodItemId}", foodItemId)
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.foodItemName").value("Food Item Detail"));
  }
}
