package com.nt.restaurant.microservice.controller;

import com.nt.restaurant.microservice.indto.RestaurantInDTO;
import com.nt.restaurant.microservice.outdto.CommonResponse;
import com.nt.restaurant.microservice.outdto.RestaurantOutDTO;
import com.nt.restaurant.microservice.service.RestaurantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RestaurantControllerTest {

  @InjectMocks
  private RestaurantController restaurantController;

  @Mock
  private RestaurantService restaurantService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testAddRestaurant_Success() throws Exception {
    RestaurantInDTO restaurantInDTO = new RestaurantInDTO(1, "Test Restaurant", "123 Test St", "9876543210", "Best restaurant",
      new MockMultipartFile("image", "image.jpg", "image/jpeg", new byte[0]));
    MultipartFile image = new MockMultipartFile("image", "image.jpg", "image/jpeg", new byte[0]);

    CommonResponse commonResponse = new CommonResponse("Restaurant added successfully");
    when(restaurantService.addRestaurant(any(RestaurantInDTO.class), any(MultipartFile.class))).thenReturn(commonResponse);

    ResponseEntity<CommonResponse> response = restaurantController.addRestaurant(restaurantInDTO, image);

    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals("Restaurant added successfully", response.getBody().getMessage());
    verify(restaurantService, times(1)).addRestaurant(any(RestaurantInDTO.class), any(MultipartFile.class));
  }

  @Test
  void testGetRestaurantById_Success() {
    RestaurantOutDTO restaurantOutDTO = new RestaurantOutDTO();
    restaurantOutDTO.setRestaurantId(1);
    restaurantOutDTO.setRestaurantName("Test Restaurant");

    when(restaurantService.getRestaurantById(anyInt())).thenReturn(restaurantOutDTO);

    ResponseEntity<RestaurantOutDTO> response = restaurantController.getRestaurantById(1);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(1, response.getBody().getRestaurantId());
    assertEquals("Test Restaurant", response.getBody().getRestaurantName());
    verify(restaurantService, times(1)).getRestaurantById(anyInt());
  }

  @Test
  void testGetRestaurantsByUserId_Success() {
    RestaurantOutDTO restaurantOutDTO = new RestaurantOutDTO();
    restaurantOutDTO.setRestaurantId(1);
    restaurantOutDTO.setRestaurantName("Test Restaurant");

    List<RestaurantOutDTO> restaurantList = Collections.singletonList(restaurantOutDTO);
    when(restaurantService.getRestaurantsByUserId(anyInt())).thenReturn(restaurantList);

    ResponseEntity<List<RestaurantOutDTO>> response = restaurantController.getRestaurantsByUserId(1);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(1, response.getBody().size());
    assertEquals("Test Restaurant", response.getBody().get(0).getRestaurantName());
    verify(restaurantService, times(1)).getRestaurantsByUserId(anyInt());
  }

  @Test
  void testGetRestaurantImage_Success() {
    byte[] image = new byte[0]; // Placeholder for actual image data

    when(restaurantService.getRestaurantImage(anyInt())).thenReturn(image);

    ResponseEntity<byte[]> response = restaurantController.getRestaurantImage(1);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertArrayEquals(image, response.getBody());
    assertEquals("image/jpeg", response.getHeaders().getContentType().toString());
    verify(restaurantService, times(1)).getRestaurantImage(anyInt());
  }

  @Test
  void testGetAllRestaurants_Success() {
    RestaurantOutDTO restaurantOutDTO = new RestaurantOutDTO();
    restaurantOutDTO.setRestaurantId(1);
    restaurantOutDTO.setRestaurantName("Test Restaurant");

    List<RestaurantOutDTO> restaurantList = Collections.singletonList(restaurantOutDTO);
    when(restaurantService.getAllRestaurants()).thenReturn(restaurantList);

    ResponseEntity<List<RestaurantOutDTO>> response = restaurantController.getAllRestaurants();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(1, response.getBody().size());
    assertEquals("Test Restaurant", response.getBody().get(0).getRestaurantName());
    verify(restaurantService, times(1)).getAllRestaurants();
  }
}
