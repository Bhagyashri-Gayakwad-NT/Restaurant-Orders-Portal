package com.nt.restaurant.microservice.service;

import com.nt.restaurant.microservice.dto.CommonResponse;
import com.nt.restaurant.microservice.dto.RestaurantInDTO;
import com.nt.restaurant.microservice.dto.RestaurantOutDTO;
import com.nt.restaurant.microservice.dto.UserOutDTO;
import com.nt.restaurant.microservice.exception.InvalidRequestException;
import com.nt.restaurant.microservice.exception.ResourceNotFoundException;
import com.nt.restaurant.microservice.exception.UnauthorizedException;
import com.nt.restaurant.microservice.entities.Restaurant;
import com.nt.restaurant.microservice.repository.RestaurantRepository;
import com.nt.restaurant.microservice.serviceimpl.RestaurantServiceImpl;
import com.nt.restaurant.microservice.serviceimpl.UserFClient;
import com.nt.restaurant.microservice.util.Constants;
import com.nt.restaurant.microservice.util.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceImplTest {

  @InjectMocks
  private RestaurantServiceImpl restaurantService;

  @Mock
  private RestaurantRepository restaurantRepository;

  @Mock
  private UserFClient userFClient;

  private RestaurantInDTO restaurantInDTO;
  private MultipartFile image;
  private UserOutDTO userOutDTO;

  @BeforeEach
  void setUp() {
    restaurantInDTO = new RestaurantInDTO();
    restaurantInDTO.setUserId(1);
    restaurantInDTO.setRestaurantName("Test Restaurant");
    image = mock(MultipartFile.class);
    userOutDTO = new UserOutDTO();
    userOutDTO.setRole(Role.RESTAURANT_OWNER.name());
  }

  @Test
  void testAddRestaurant_Success() throws Exception {
    when(userFClient.getUserProfile(1)).thenReturn(userOutDTO);
    when(restaurantRepository.existsByRestaurantNameIgnoreCase("test restaurant")).thenReturn(false);
    when(image.getContentType()).thenReturn("image/jpeg");
    when(image.isEmpty()).thenReturn(false);
    when(image.getBytes()).thenReturn(new byte[]{1, 2, 3});

    CommonResponse response = restaurantService.addRestaurant(restaurantInDTO, image);

    assertEquals(Constants.RESTAURANT_ADDED_SUCCESS, response.getMessage());
    verify(restaurantRepository, times(1)).save(any(Restaurant.class));
  }

  @Test
  void testAddRestaurant_UserNotFound() throws Exception {
    when(userFClient.getUserProfile(1)).thenThrow(new RuntimeException());

    Exception exception = assertThrows(ResourceNotFoundException.class, () ->
      restaurantService.addRestaurant(restaurantInDTO, image));

    assertEquals(Constants.USER_NOT_FOUND, exception.getMessage());
  }

  @Test
  void testAddRestaurant_UserNotRestaurantOwner() throws Exception {
    userOutDTO.setRole(Role.USER.name());
    when(userFClient.getUserProfile(1)).thenReturn(userOutDTO);

    Exception exception = assertThrows(UnauthorizedException.class, () ->
      restaurantService.addRestaurant(restaurantInDTO, image));

    assertEquals(Constants.USER_NOT_RESTAURANT_OWNER, exception.getMessage());
  }

  @Test
  void testAddRestaurant_RestaurantNameExists() throws Exception {
    when(userFClient.getUserProfile(1)).thenReturn(userOutDTO);
    when(restaurantRepository.existsByRestaurantNameIgnoreCase("test restaurant")).thenReturn(true);

    Exception exception = assertThrows(InvalidRequestException.class, () ->
      restaurantService.addRestaurant(restaurantInDTO, image));

    assertEquals(Constants.RESTAURANT_NAME_EXISTS, exception.getMessage());
  }

  @Test
  void testAddRestaurant_InvalidImageType() throws Exception {
    when(userFClient.getUserProfile(1)).thenReturn(userOutDTO);
    when(restaurantRepository.existsByRestaurantNameIgnoreCase("test restaurant")).thenReturn(false);
    when(image.getContentType()).thenReturn("text/plain");
    when(image.isEmpty()).thenReturn(false);

    Exception exception = assertThrows(ResourceNotFoundException.class, () ->
      restaurantService.addRestaurant(restaurantInDTO, image));

    assertEquals(Constants.INVALID_FILE_TYPE, exception.getMessage());
  }

  @Test
  void testGetRestaurantById_Success() {
    Restaurant restaurant = new Restaurant();
    restaurant.setRestaurantId(1);
    when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));

    RestaurantOutDTO result = restaurantService.getRestaurantById(1);

    assertNotNull(result);
    assertEquals(1, result.getRestaurantId());
  }

  @Test
  void testGetRestaurantById_NotFound() {
    when(restaurantRepository.findById(1)).thenReturn(Optional.empty());

    Exception exception = assertThrows(ResourceNotFoundException.class, () ->
      restaurantService.getRestaurantById(1));

    assertEquals(Constants.RESTAURANT_NOT_FOUND, exception.getMessage());
  }

  @Test
  void testGetRestaurantsByUserId_Success() {
    when(restaurantRepository.findByUserId(1)).thenReturn(new ArrayList<>());

    List<RestaurantOutDTO> restaurants = restaurantService.getRestaurantsByUserId(1);

    assertNotNull(restaurants);
    assertTrue(restaurants.isEmpty());
  }

  @Test
  void testGetAllRestaurants_Success() {
    when(restaurantRepository.findAll()).thenReturn(new ArrayList<>());

    List<RestaurantOutDTO> restaurants = restaurantService.getAllRestaurants();

    assertNotNull(restaurants);
    assertTrue(restaurants.isEmpty());
  }

  // Add additional test cases for edge cases as needed.
}
