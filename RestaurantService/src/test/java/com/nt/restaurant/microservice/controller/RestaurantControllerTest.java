package com.nt.restaurant.microservice.controller;

import com.nt.restaurant.microservice.dto.CommonResponse;
import com.nt.restaurant.microservice.dto.RestaurantInDTO;
import com.nt.restaurant.microservice.dto.RestaurantOutDTO;
import com.nt.restaurant.microservice.service.RestaurantService;
import com.nt.restaurant.microservice.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RestaurantControllerTest {

  private MockMvc mockMvc;

  @Mock
  private RestaurantService restaurantService;

  @InjectMocks
  private RestaurantController restaurantController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(restaurantController).build();
  }

  @Test
  void testAddRestaurant() throws Exception {
    RestaurantInDTO restaurantInDTO = new RestaurantInDTO();
    restaurantInDTO.setRestaurantName("Test Restaurant");
    restaurantInDTO.setUserId(1);
    restaurantInDTO.setRestaurantAddress("Test Address");
    restaurantInDTO.setContactNumber("9876543210");
    restaurantInDTO.setDescription("Test Description");

    MockMultipartFile image = new MockMultipartFile("restaurantImage", "image.jpg", MediaType.IMAGE_JPEG_VALUE, "image content".getBytes());

    CommonResponse response = new CommonResponse(Constants.RESTAURANT_ADDED_SUCCESS);

    when(restaurantService.addRestaurant(any(RestaurantInDTO.class), any(MultipartFile.class))).thenReturn(response);

    mockMvc.perform(multipart("/restaurant/addRestaurant")
        .file(image)
        .param("restaurantName", "Test Restaurant")
        .param("userId", "1")
        .param("restaurantAddress", "Test Street")
        .param("contactNumber", "9876543210")
        .param("description", "Test Description")
        .contentType(MediaType.MULTIPART_FORM_DATA))
      .andDo(print())
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.message").value(Constants.RESTAURANT_ADDED_SUCCESS));
  }


  @Test
  void testGetRestaurantById() throws Exception {
    int restaurantId = 1;
    RestaurantOutDTO restaurantOutDTO = new RestaurantOutDTO();
    restaurantOutDTO.setRestaurantId(restaurantId);
    restaurantOutDTO.setRestaurantName("Test Restaurant");

    when(restaurantService.getRestaurantById(eq(restaurantId))).thenReturn(restaurantOutDTO);

    mockMvc.perform(get("/restaurant/getRestaurant/{restaurantId}", restaurantId))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.restaurantId").value(restaurantId))
      .andExpect(jsonPath("$.restaurantName").value("Test Restaurant"));
  }

  @Test
  void testGetRestaurantsByUserId() throws Exception {
    int userId = 1;
    List<RestaurantOutDTO> restaurants = new ArrayList<>();
    RestaurantOutDTO restaurant1 = new RestaurantOutDTO();
    restaurant1.setRestaurantId(1);
    restaurant1.setRestaurantName("Restaurant 1");
    restaurants.add(restaurant1);

    when(restaurantService.getRestaurantsByUserId(eq(userId))).thenReturn(restaurants);

    mockMvc.perform(get("/restaurant/restaurants/{userId}", userId))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.size()").value(restaurants.size()))
      .andExpect(jsonPath("$[0].restaurantName").value("Restaurant 1"));
  }

  @Test
  void testGetRestaurantImage() throws Exception {
    int restaurantId = 1;
    byte[] imageBytes = "image data".getBytes();

    when(restaurantService.getRestaurantImage(eq(restaurantId))).thenReturn(imageBytes);

    mockMvc.perform(get("/restaurant/{id}/image", restaurantId))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.IMAGE_JPEG))
      .andExpect(content().bytes(imageBytes));
  }

  @Test
  void testGetAllRestaurants() throws Exception {
    List<RestaurantOutDTO> restaurants = new ArrayList<>();
    RestaurantOutDTO restaurant1 = new RestaurantOutDTO();
    restaurant1.setRestaurantId(1);
    restaurant1.setRestaurantName("Restaurant 1");
    restaurants.add(restaurant1);

    when(restaurantService.getAllRestaurants()).thenReturn(restaurants);

    mockMvc.perform(get("/restaurant"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.size()").value(restaurants.size()))
      .andExpect(jsonPath("$[0].restaurantName").value("Restaurant 1"));
  }
}
