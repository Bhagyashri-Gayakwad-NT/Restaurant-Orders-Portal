package com.nt.restaurant.microservice.service;

import com.nt.restaurant.microservice.dto.CommonResponse;
import com.nt.restaurant.microservice.dto.RestaurantInDTO;
import com.nt.restaurant.microservice.dto.RestaurantOutDTO;
import com.nt.restaurant.microservice.dto.UserOutDTO;
import com.nt.restaurant.microservice.entities.Restaurant;
import com.nt.restaurant.microservice.exception.InvalidRequestException;
import com.nt.restaurant.microservice.exception.ResourceNotFoundException;
import com.nt.restaurant.microservice.exception.UnauthorizedException;
import com.nt.restaurant.microservice.repository.RestaurantRepository;
import com.nt.restaurant.microservice.serviceimpl.RestaurantServiceImpl;
import com.nt.restaurant.microservice.serviceimpl.UserFClient;
import com.nt.restaurant.microservice.util.Constants;
import com.nt.restaurant.microservice.util.Role;
import com.nt.restaurant.microservice.dtoconvertion.DtoConverter;
import com.nt.restaurant.microservice.service.RestaurantService;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantServiceImplTest {

  @Mock
  private RestaurantRepository restaurantRepository;

  @Mock
  private UserFClient userFClient;

  @InjectMocks
  private RestaurantServiceImpl restaurantService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testAddRestaurant_success() throws Exception {
    RestaurantInDTO restaurantInDTO = new RestaurantInDTO();
    restaurantInDTO.setUserId(1);
    restaurantInDTO.setRestaurantName("Test Restaurant");
    restaurantInDTO.setRestaurantAddress("123 Test Street");
    restaurantInDTO.setContactNumber("9876543210");
    restaurantInDTO.setDescription("A test description");

    MultipartFile mockImage = mock(MultipartFile.class);
    when(mockImage.getBytes()).thenReturn(new byte[]{1, 2, 3});
    when(mockImage.isEmpty()).thenReturn(false);
    when(mockImage.getContentType()).thenReturn("image/jpeg");

    restaurantInDTO.setRestaurantImage(mockImage);

    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setRole(Role.RESTAURANT_OWNER.name());

    when(userFClient.getUserProfile(1)).thenReturn(userOutDTO);
    when(restaurantRepository.existsByRestaurantName(anyString())).thenReturn(false);
    when(restaurantRepository.save(any(Restaurant.class))).thenReturn(new Restaurant());

    CommonResponse response = restaurantService.addRestaurant(restaurantInDTO, mockImage);

    assertEquals(Constants.RESTAURANT_ADDED_SUCCESS, response.getMessage());
  }

  @Test
  void testAddRestaurant_userNotFound() {
    RestaurantInDTO restaurantInDTO = new RestaurantInDTO();
    restaurantInDTO.setUserId(1);

    when(userFClient.getUserProfile(1)).thenThrow(FeignException.NotFound.class);

    assertThrows(ResourceNotFoundException.class, () -> restaurantService.addRestaurant(restaurantInDTO, null));
  }

  @Test
  void testAddRestaurant_notOwner() {
    RestaurantInDTO restaurantInDTO = new RestaurantInDTO();
    restaurantInDTO.setUserId(1);

    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setRole(Role.USER.name());

    when(userFClient.getUserProfile(1)).thenReturn(userOutDTO);

    // When and Then
    assertThrows(UnauthorizedException.class, () -> restaurantService.addRestaurant(restaurantInDTO, null));
  }

  @Test
  void testGetRestaurantById_success() {
    Restaurant restaurant = new Restaurant();
    restaurant.setRestaurantId(1);
    when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));

    RestaurantOutDTO restaurantOutDTO = restaurantService.getRestaurantById(1);

    assertNotNull(restaurantOutDTO);
  }

  @Test
  void testGetRestaurantById_notFound() {
    when(restaurantRepository.findById(1)).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> restaurantService.getRestaurantById(1));
  }

  @Test
  void testGetRestaurantsByUserId_success() {
    List<Restaurant> restaurants = new ArrayList<>();
    restaurants.add(new Restaurant());
    when(restaurantRepository.findByUserId(1)).thenReturn(restaurants);

    List<RestaurantOutDTO> result = restaurantService.getRestaurantsByUserId(1);

    assertEquals(1, result.size());
  }

  @Test
  void testGetAllRestaurants_success() {
    List<Restaurant> restaurants = new ArrayList<>();
    restaurants.add(new Restaurant());
    when(restaurantRepository.findAll()).thenReturn(restaurants);

    List<RestaurantOutDTO> result = restaurantService.getAllRestaurants();

    assertEquals(1, result.size());
  }

  @Test
  void testGetRestaurantImage_success() {
    Restaurant restaurant = new Restaurant();
    restaurant.setRestaurantImage(Base64.getEncoder().encode("test".getBytes()));
    when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));

    byte[] image = restaurantService.getRestaurantImage(1);

    assertNotNull(image);
  }
}
