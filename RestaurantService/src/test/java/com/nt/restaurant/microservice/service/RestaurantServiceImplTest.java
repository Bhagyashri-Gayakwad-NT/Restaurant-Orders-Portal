package com.nt.restaurant.microservice.service;

import com.nt.restaurant.microservice.dto.*;
import com.nt.restaurant.microservice.entities.Restaurant;
import com.nt.restaurant.microservice.exception.InvalidRequestException;
import com.nt.restaurant.microservice.exception.ResourceNotFoundException;
import com.nt.restaurant.microservice.exception.UnauthorizedException;
import com.nt.restaurant.microservice.repository.RestaurantRepository;
import com.nt.restaurant.microservice.serviceimpl.RestaurantServiceImpl;
import com.nt.restaurant.microservice.serviceimpl.UserFClient;
import com.nt.restaurant.microservice.util.Constants;
import com.nt.restaurant.microservice.util.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class RestaurantServiceImplTest {

  @Mock
  private RestaurantRepository restaurantRepository;

  @Mock
  private UserFClient userFClient;

  @InjectMocks
  private RestaurantServiceImpl restaurantService;

  private RestaurantInDTO restaurantInDTO;
  private Restaurant restaurant;
  private UserOutDTO userOutDTO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);

    // Setup mock RestaurantInDTO object
    restaurantInDTO = new RestaurantInDTO();
    restaurantInDTO.setUserId(1);
    restaurantInDTO.setRestaurantName("Test Restaurant");
    restaurantInDTO.setRestaurantAddress("Test address");
    restaurantInDTO.setContactNumber("9876543210");
    restaurantInDTO.setDescription("Test description");
    restaurantInDTO.setRestaurantImage(null); // Assuming image is optional

    // Setup mock Restaurant entity
    restaurant = new Restaurant();
    restaurant.setRestaurantId(1);
    restaurant.setRestaurantName("Test Restaurant");

    // Setup mock UserOutDTO for valid restaurant owner
    userOutDTO = new UserOutDTO();
    userOutDTO.setRole(Role.RESTAURANT_OWNER.name());
  }

//  @Test
//  void testAddRestaurant_Success() {
//    // Mock a valid MultipartFile for the restaurant image
//    MultipartFile mockImage = mock(MultipartFile.class);
//
//    // Assuming we're testing with an image, not empty
//    when(mockImage.isEmpty()).thenReturn(false);
//
//    // Simulate getting bytes from the image
//    try {
//      when(mockImage.getBytes()).thenReturn("dummy-image".getBytes());
//    } catch (IOException e) {
//      fail("IOException should not be thrown here");
//    }
//
//    when(userFClient.getUserProfile(anyInt())).thenReturn(userOutDTO);
//    when(restaurantRepository.existsByRestaurantNameIgnoreCase(anyString())).thenReturn(false);
//    when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);
//
//    // Call the method you want to test
//    CommonResponse response = restaurantService.addRestaurant(restaurantInDTO, mockImage);
//
//    // Verify the response and repository interactions
//    assertEquals(Constants.RESTAURANT_ADDED_SUCCESS, response.getMessage());
//    verify(restaurantRepository, times(1)).save(any(Restaurant.class));
//  }


  @Test
  void testAddRestaurant_UserNotFound() {
    when(userFClient.getUserProfile(anyInt())).thenThrow(new ResourceNotFoundException(Constants.USER_NOT_FOUND));

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
      () -> restaurantService.addRestaurant(restaurantInDTO, null));

    assertEquals(Constants.USER_NOT_FOUND, exception.getMessage());
  }

  @Test
  void testAddRestaurant_UserNotRestaurantOwner() {
    userOutDTO.setRole(Role.USER.name());
    when(userFClient.getUserProfile(anyInt())).thenReturn(userOutDTO);

    UnauthorizedException exception = assertThrows(UnauthorizedException.class,
      () -> restaurantService.addRestaurant(restaurantInDTO, null));

    assertEquals(Constants.USER_NOT_RESTAURANT_OWNER, exception.getMessage());
  }

  @Test
  void testAddRestaurant_RestaurantNameExists() {
    when(userFClient.getUserProfile(anyInt())).thenReturn(userOutDTO);
    when(restaurantRepository.existsByRestaurantNameIgnoreCase(anyString())).thenReturn(true);

    InvalidRequestException exception = assertThrows(InvalidRequestException.class,
      () -> restaurantService.addRestaurant(restaurantInDTO, null));

    assertEquals(Constants.RESTAURANT_NAME_EXISTS, exception.getMessage());
  }

  @Test
  void testGetRestaurantById_Success() {
    when(restaurantRepository.findById(anyInt())).thenReturn(Optional.of(restaurant));

    RestaurantOutDTO result = restaurantService.getRestaurantById(1);

    assertEquals(restaurant.getRestaurantName(), result.getRestaurantName());
  }

  @Test
  void testGetRestaurantById_NotFound() {
    when(restaurantRepository.findById(anyInt())).thenReturn(Optional.empty());

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
      () -> restaurantService.getRestaurantById(1));

    assertEquals(Constants.RESTAURANT_NOT_FOUND, exception.getMessage());
  }

  @Test
  void testGetRestaurantsByUserId_Success() {
    Restaurant restaurant = new Restaurant();
    restaurant.setRestaurantId(1);
    restaurant.setRestaurantName("Test Restaurant");
    restaurant.setUserId(1);

    List<Restaurant> restaurants = Collections.singletonList(restaurant);
    when(restaurantRepository.findByUserId(anyInt())).thenReturn(restaurants);

    List<RestaurantOutDTO> result = restaurantService.getRestaurantsByUserId(1);

    assertEquals(1, result.size());
    assertEquals(restaurant.getRestaurantName(), result.get(0).getRestaurantName());
  }

  @Test
  void testGetAllRestaurants_Success() {
    List<Restaurant> restaurants = Arrays.asList(restaurant);
    when(restaurantRepository.findAll()).thenReturn(restaurants);

    List<RestaurantOutDTO> result = restaurantService.getAllRestaurants();

    assertEquals(1, result.size());
    assertEquals(restaurant.getRestaurantName(), result.get(0).getRestaurantName());
  }

  @Test
  void testGetRestaurantImage_Success() {
    byte[] imageBytes = "test-image".getBytes();
    restaurant.setRestaurantImage(imageBytes); // Ensure this method expects byte[]
    when(restaurantRepository.findById(anyInt())).thenReturn(Optional.of(restaurant));
    byte[] result = restaurantService.getRestaurantImage(1);
    assertNotNull(result);
    assertArrayEquals(imageBytes, result);
  }


}
