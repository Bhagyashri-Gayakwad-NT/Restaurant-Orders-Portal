package com.nt.restaurant.microservice.service;

import com.nt.restaurant.microservice.dtoconvertion.DtoConverter;
import com.nt.restaurant.microservice.entities.Restaurant;
import com.nt.restaurant.microservice.exception.NotFoundException;
import com.nt.restaurant.microservice.exception.UserNotRestaurantOwnerException;
import com.nt.restaurant.microservice.indto.RestaurantInDTO;
import com.nt.restaurant.microservice.outdto.CommonResponse;
import com.nt.restaurant.microservice.outdto.RestaurantOutDTO;
import com.nt.restaurant.microservice.repository.RestaurantRepository;
import com.nt.restaurant.microservice.serviceimpl.RestaurantServiceImpl;
import com.nt.restaurant.microservice.serviceimpl.UserFClient;
import com.nt.restaurant.microservice.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RestaurantServiceImplTest {

  @InjectMocks
  private RestaurantServiceImpl restaurantService;

  @Mock
  private RestaurantRepository restaurantRepository;

  @Mock
  private UserFClient userFClient;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testAddRestaurantSuccess() {
    // Arrange
    RestaurantInDTO restaurantInDTO =
      new RestaurantInDTO(1, "Test Restaurant", "Test Address", "9876543210", "Test Description", mock(MultipartFile.class));
//    UserOutDTO userOutDTO = new UserOutDTO(1, "Test User", "RESTAURANT_OWNER"); // Changed to match the owner role

//    when(userFClient.getUserProfile(1)).thenReturn(userOutDTO); // User with owner role
    Restaurant restaurant = new Restaurant();
    when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);
    when(DtoConverter.fromEntityToOutDTO(any(Restaurant.class))).thenReturn(new RestaurantOutDTO());

    // Act
    CommonResponse response = restaurantService.addRestaurant(restaurantInDTO);

    // Assert
    assertEquals(Constants.RESTAURANT_ADDED_SUCCESS, response.getMessage());
    verify(restaurantRepository, times(1)).save(any(Restaurant.class));
    verify(userFClient, times(1)).getUserProfile(1);
  }


  @Test
  public void testAddRestaurantUserNotRestaurantOwner() {
    // Arrange
    RestaurantInDTO restaurantInDTO =
      new RestaurantInDTO(1, "Test Restaurant", "Test Address", "9876543210", "Test Description", mock(MultipartFile.class));
//    UserOutDTO userOutDTO = new UserOutDTO(1, "Test User", "USER");

//    when(userFClient.getUserProfile(1)).thenReturn(userOutDTO);

    // Act & Assert
    UserNotRestaurantOwnerException thrown =
      assertThrows(UserNotRestaurantOwnerException.class, () -> restaurantService.addRestaurant(restaurantInDTO));
    assertEquals(Constants.USER_NOT_RESTAURANT_OWNER, thrown.getMessage());
  }

  @Test
  public void testGetRestaurantByIdSuccess() {
    // Arrange
    Restaurant restaurant = new Restaurant();
    restaurant.setRestaurantId(1);
    RestaurantOutDTO restaurantOutDTO = new RestaurantOutDTO();
    when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));
    when(DtoConverter.fromEntityToOutDTO(restaurant)).thenReturn(restaurantOutDTO);

    // Act
    RestaurantOutDTO result = restaurantService.getRestaurantById(1);

    // Assert
    assertNotNull(result);
    assertEquals(restaurantOutDTO, result);
  }

  @Test
  public void testGetRestaurantByIdNotFound() {
    // Arrange
    when(restaurantRepository.findById(1)).thenReturn(Optional.empty());

    // Act & Assert
    NotFoundException thrown = assertThrows(NotFoundException.class, () -> restaurantService.getRestaurantById(1));
    assertEquals("Restaurant not found with ID: 1", thrown.getMessage());
  }

  @Test
  public void testGetRestaurantsByUserIdSuccess() {
    // Arrange
    Restaurant restaurant = new Restaurant();
    restaurant.setRestaurantId(1);
    List<Restaurant> restaurants = new ArrayList<>();
    restaurants.add(restaurant);
    RestaurantOutDTO restaurantOutDTO = new RestaurantOutDTO();
    when(restaurantRepository.findByUserId(1)).thenReturn(restaurants);
    when(DtoConverter.fromEntityToOutDTO(restaurant)).thenReturn(restaurantOutDTO);

    // Act
    List<RestaurantOutDTO> result = restaurantService.getRestaurantsByUserId(1);

    // Assert
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(restaurantOutDTO, result.get(0));
  }

  @Test
  public void testGetAllRestaurants() {
    // Arrange
    Restaurant restaurant = new Restaurant();
    List<Restaurant> restaurants = new ArrayList<>();
    restaurants.add(restaurant);
    RestaurantOutDTO restaurantOutDTO = new RestaurantOutDTO();
    when(restaurantRepository.findAll()).thenReturn(restaurants);
    when(DtoConverter.fromEntityToOutDTO(restaurant)).thenReturn(restaurantOutDTO);

    // Act
    List<RestaurantOutDTO> result = restaurantService.getAllRestaurants();

    // Assert
    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals(restaurantOutDTO, result.get(0));
  }

  @Test
  public void testGetRestaurantImageSuccess() {
    // Arrange
    Restaurant restaurant = new Restaurant();
    restaurant.setRestaurantImage(new byte[] {1, 2, 3});
    when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));

    // Act
    byte[] image = restaurantService.getRestaurantImage(1);

    // Assert
    assertArrayEquals(new byte[] {1, 2, 3}, image);
  }

  @Test
  public void testGetRestaurantImageNotFound() {
    // Arrange
    when(restaurantRepository.findById(1)).thenReturn(Optional.empty());

    // Act & Assert
    NotFoundException thrown = assertThrows(NotFoundException.class, () -> restaurantService.getRestaurantImage(1));
    assertEquals("Restaurant not found with ID: 1", thrown.getMessage());
  }
}