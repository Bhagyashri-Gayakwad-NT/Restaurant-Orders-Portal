//package com.nt.restaurant.microservice.controller;
//import com.nt.restaurant.microservice.indto.RestaurantInDTO;
//import com.nt.restaurant.microservice.outdto.CommonResponse;
//import com.nt.restaurant.microservice.outdto.RestaurantOutDTO;
//import com.nt.restaurant.microservice.service.RestaurantService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockMultipartFile;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class RestaurantControllerTest {
//
//  @Mock
//  private RestaurantService restaurantService;
//
//  @InjectMocks
//  private RestaurantController restaurantController;
//
//  @BeforeEach
//  void setUp() {
//    MockitoAnnotations.openMocks(this);
//  }
//
//  @Test
//  void testAddRestaurant() {
//    // Mock RestaurantInDTO
//    RestaurantInDTO restaurantInDTO = new RestaurantInDTO();
//    restaurantInDTO.setRestaurantName("Test Restaurant");
//
//    // Mock service response
//    CommonResponse commonResponse = new CommonResponse("Restaurant added successfully", true);
//    when(restaurantService.addRestaurant(restaurantInDTO)).thenReturn(commonResponse);
//
//    // Call the controller
//    ResponseEntity<CommonResponse> response = restaurantController.addRestaurant(restaurantInDTO);
//
//    // Verify the service and check assertions
//    verify(restaurantService, times(1)).addRestaurant(restaurantInDTO);
//    assertEquals(HttpStatus.CREATED, response.getStatusCode());
//    assertEquals("Restaurant added successfully", response.getBody().getMessage());
//  }
//
//  @Test
//  void testGetRestaurantById_Success() {
//    // Mock RestaurantOutDTO
//    RestaurantOutDTO restaurantOutDTO = new RestaurantOutDTO();
//    restaurantOutDTO.setRestaurantId(1);
//    restaurantOutDTO.setRestaurantName("Test Restaurant");
//
//    // Mock service response
//    when(restaurantService.getRestaurantById(1)).thenReturn(restaurantOutDTO);
//
//    // Call the controller
//    ResponseEntity<RestaurantOutDTO> response = restaurantController.getRestaurantById(1);
//
//    // Verify the service and check assertions
//    verify(restaurantService, times(1)).getRestaurantById(1);
//    assertEquals(HttpStatus.OK, response.getStatusCode());
//    assertEquals("Test Restaurant", response.getBody().getRestaurantName());
//  }
//
//  @Test
//  void testGetRestaurantById_NotFound() {
//    // Mock service response
//    when(restaurantService.getRestaurantById(2)).thenReturn(null);
//
//    // Call the controller
//    ResponseEntity<RestaurantOutDTO> response = restaurantController.getRestaurantById(2);
//
//    // Verify the service and check assertions
//    verify(restaurantService, times(1)).getRestaurantById(2);
//    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//  }
//
//  @Test
//  void testGetRestaurantsByUserId() {
//    // Mock list of RestaurantOutDTO
//    RestaurantOutDTO restaurant1 = new RestaurantOutDTO();
//    restaurant1.setRestaurantId(1);
//    restaurant1.setRestaurantName("Restaurant 1");
//
//    RestaurantOutDTO restaurant2 = new RestaurantOutDTO();
//    restaurant2.setRestaurantId(2);
//    restaurant2.setRestaurantName("Restaurant 2");
//
//    List<RestaurantOutDTO> restaurants = Arrays.asList(restaurant1, restaurant2);
//
//    // Mock service response
//    when(restaurantService.getRestaurantsByUserId(1)).thenReturn(restaurants);
//
//    // Call the controller
//    ResponseEntity<List<RestaurantOutDTO>> response = restaurantController.getRestaurantsByUserId(1);
//
//    // Verify the service and check assertions
//    verify(restaurantService, times(1)).getRestaurantsByUserId(1);
//    assertEquals(HttpStatus.OK, response.getStatusCode());
//    assertEquals(2, response.getBody().size());
//  }
//
//  @Test
//  void testGetRestaurantImage() {
//    // Mock image data
//    byte[] mockImageData = "imageData".getBytes();
//
//    // Mock service response
//    when(restaurantService.getRestaurantImage(1)).thenReturn(mockImageData);
//
//    // Call the controller
//    ResponseEntity<byte[]> response = restaurantController.getRestaurantImage(1);
//
//    // Verify the service and check assertions
//    verify(restaurantService, times(1)).getRestaurantImage(1);
//    assertEquals(HttpStatus.OK, response.getStatusCode());
//    assertNotNull(response.getBody());
//    assertEquals("imageData", new String(response.getBody()));
//  }
//
//  @Test
//  void testGetAllRestaurants() {
//    // Mock list of RestaurantOutDTO
//    RestaurantOutDTO restaurant1 = new RestaurantOutDTO();
//    restaurant1.setRestaurantId(1);
//    restaurant1.setRestaurantName("Restaurant 1");
//
//    RestaurantOutDTO restaurant2 = new RestaurantOutDTO();
//    restaurant2.setRestaurantId(2);
//    restaurant2.setRestaurantName("Restaurant 2");
//
//    List<RestaurantOutDTO> restaurants = Arrays.asList(restaurant1, restaurant2);
//
//    // Mock service response
//    when(restaurantService.getAllRestaurants()).thenReturn(restaurants);
//
//    // Call the controller
//    ResponseEntity<List<RestaurantOutDTO>> response = restaurantController.getAllRestaurants();
//
//    // Verify the service and check assertions
//    verify(restaurantService, times(1)).getAllRestaurants();
//    assertEquals(HttpStatus.OK, response.getStatusCode());
//    assertEquals(2, response.getBody().size());
//  }
//}