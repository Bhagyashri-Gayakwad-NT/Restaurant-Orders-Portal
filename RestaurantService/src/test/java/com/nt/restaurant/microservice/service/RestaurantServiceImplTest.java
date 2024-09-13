package com.nt.restaurant.microservice.service;

import com.nt.restaurant.microservice.entities.Restaurant;
import com.nt.restaurant.microservice.exception.InvalidImageFileException;
import com.nt.restaurant.microservice.exception.NotFoundException;
import com.nt.restaurant.microservice.indto.RestaurantInDTO;
import com.nt.restaurant.microservice.outdto.CommonResponse;
import com.nt.restaurant.microservice.outdto.RestaurantOutDTO;
import com.nt.restaurant.microservice.outdto.UserOutDTO;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
  void testAddRestaurant_Success() throws Exception {
    // Setup
    RestaurantInDTO restaurantInDTO = new RestaurantInDTO(1, "Test Restaurant", "123 Test St", "9876543210", "Best restaurant",
      new MockMultipartFile("image", "image.jpg", "image/jpeg", new byte[0]));
    MultipartFile image = mock(MultipartFile.class);
    when(image.getContentType()).thenReturn("image/jpeg");
    when(image.getBytes()).thenReturn(new byte[0]); // Ensure this is not null

    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setRole(Role.RESTAURANT_OWNER.name());

    when(userFClient.getUserProfile(1)).thenReturn(userOutDTO);

    Restaurant restaurant = new Restaurant();
    restaurant.setUserId(1);
    restaurant.setRestaurantName("Test Restaurant");
    restaurant.setRestaurantAddress("123 Test St");
    restaurant.setContactNumber("9876543210");
    restaurant.setDescription("Best restaurant");
    restaurant.setRegistrationDate(LocalDate.now());
    restaurant.setOpen(true);
    restaurant.setRestaurantImage(new byte[0]);

    when(restaurantRepository.save(any(Restaurant.class))).thenReturn(restaurant);

    // Execute
    CommonResponse response = restaurantService.addRestaurant(restaurantInDTO, image);

    // Verify
    assertEquals(Constants.RESTAURANT_ADDED_SUCCESS, response.getMessage());
    verify(restaurantRepository, times(1)).save(any(Restaurant.class));
  }


  @Test
  public void testAddRestaurant_InvalidImageType() throws Exception {
    // Setup input DTO
    RestaurantInDTO restaurantInDTO = new RestaurantInDTO();
    restaurantInDTO.setUserId(1);
    restaurantInDTO.setRestaurantName("Test Restaurant");
    restaurantInDTO.setRestaurantAddress("123 Test St");
    restaurantInDTO.setContactNumber("1234567890");
    restaurantInDTO.setDescription("A test restaurant");
    restaurantInDTO.setRestaurantImage(mock(MultipartFile.class)); // Mock image as MultipartFile

    // Setup mock MultipartFile with invalid image type
    MultipartFile image = mock(MultipartFile.class);
    when(image.getBytes()).thenReturn("testImage".getBytes());
    when(image.getContentType()).thenReturn("image/gif"); // Invalid image type

    // Mock user profile
    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setRole(Role.RESTAURANT_OWNER.name());
    when(userFClient.getUserProfile(1)).thenReturn(userOutDTO);

    // Perform the test
    InvalidImageFileException exception = assertThrows(
      InvalidImageFileException.class,
      () -> restaurantService.addRestaurant(restaurantInDTO, image)
    );

    // Validate exception message
    assertEquals(Constants.INVALID_FILE_TYPE, exception.getMessage());
  }

  @Test
  public void testAddRestaurant_UserNotRestaurantOwner() throws Exception {
    RestaurantInDTO restaurantInDTO = new RestaurantInDTO();
    restaurantInDTO.setUserId(1);

    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setRole(Role.USER.name());

    when(userFClient.getUserProfile(1)).thenReturn(userOutDTO);

    NotFoundException exception = assertThrows(
      NotFoundException.class,
      () -> restaurantService.addRestaurant(restaurantInDTO, null)
    );
    assertEquals(Constants.USER_NOT_RESTAURANT_OWNER, exception.getMessage());
  }

  @Test
  public void testAddRestaurant_UserNotFound() throws Exception {
    RestaurantInDTO restaurantInDTO = new RestaurantInDTO();
    restaurantInDTO.setUserId(1);

    when(userFClient.getUserProfile(1)).thenThrow(new RuntimeException("User not found"));

    NotFoundException exception = assertThrows(
      NotFoundException.class,
      () -> restaurantService.addRestaurant(restaurantInDTO, null)
    );
    assertEquals(Constants.USER_NOT_FOUND, exception.getMessage());
  }

  @Test
  public void testGetRestaurantById_Success() {
    Restaurant restaurant = new Restaurant();
    restaurant.setRestaurantId(1);
    restaurant.setRestaurantName("Test Restaurant");

    when(restaurantRepository.findById(1)).thenReturn(Optional.of(restaurant));

    RestaurantOutDTO restaurantOutDTO = restaurantService.getRestaurantById(1);

    assertNotNull(restaurantOutDTO);
    assertEquals(1, restaurantOutDTO.getRestaurantId());
    assertEquals("Test Restaurant", restaurantOutDTO.getRestaurantName());
  }

  @Test
  public void testGetRestaurantById_NotFound() {
    when(restaurantRepository.findById(1)).thenReturn(Optional.empty());

    NotFoundException exception = assertThrows(
      NotFoundException.class,
      () -> restaurantService.getRestaurantById(1)
    );
    assertEquals(Constants.RESTAURANT_NOT_FOUND, exception.getMessage());
  }
}
