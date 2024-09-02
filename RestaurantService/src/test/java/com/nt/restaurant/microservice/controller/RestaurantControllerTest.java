//package com.nt.restaurant.microservice.controller;
//
//import com.nt.restaurant.microservice.indto.RestaurantInDTO;
//import com.nt.restaurant.microservice.outdto.RestaurantOutDTO;
//import com.nt.restaurant.microservice.service.RestaurantService;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockMultipartFile;
//
//import java.time.LocalDate;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//class RestaurantControllerTest {
//
//  @Mock
//  private RestaurantService restaurantService;
//
//  @InjectMocks
//  private RestaurantController restaurantController;
//
//  public RestaurantControllerTest() {
//    MockitoAnnotations.openMocks(this);
//  }
//
//  @Test
//  void testAddRestaurant() {
//    RestaurantOutDTO restaurantOutDTO = new RestaurantOutDTO(1, "Restaurant Name", "Address", "9876543210",
//      LocalDate.now(), "Description", true, "imageData");
//
//    when(restaurantService.addRestaurant(any(RestaurantInDTO.class))).thenReturn(restaurantOutDTO);
//
//    ResponseEntity<RestaurantOutDTO> response = restaurantController.addRestaurant(restaurantInDTO);
//
//    assertEquals(HttpStatus.CREATED, response.getStatusCode());
//    assertNotNull(response.getBody());
//    assertEquals(restaurantOutDTO, response.getBody());
//
//    verify(restaurantService, times(1)).addRestaurant(any(RestaurantInDTO.class));
//  }
//}