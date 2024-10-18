package com.nt.restaurant.microservice.dtoconverter;

import com.nt.restaurant.microservice.dto.RestaurantInDTO;
import com.nt.restaurant.microservice.dto.RestaurantOutDTO;
import com.nt.restaurant.microservice.dtoconvertion.DtoConverter;
import com.nt.restaurant.microservice.entities.Restaurant;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DtoConverterTest {

  @Test
  void fromInDTOToEntity_ShouldConvertInDTOToEntity() throws IOException {
    RestaurantInDTO restaurantInDTO = mock(RestaurantInDTO.class);
    byte[] imageBytes = "sample image content".getBytes();
    MockMultipartFile mockImageFile = new MockMultipartFile("restaurantImage", "image.jpg", "image/jpeg", imageBytes);

    when(restaurantInDTO.getUserId()).thenReturn(1);
    when(restaurantInDTO.getRestaurantName()).thenReturn("Test Restaurant ");
    when(restaurantInDTO.getRestaurantAddress()).thenReturn(" 123 Test St ");
    when(restaurantInDTO.getContactNumber()).thenReturn("1234567890");
    when(restaurantInDTO.getDescription()).thenReturn("Test Description");

    when(restaurantInDTO.getRestaurantImage()).thenReturn(mockImageFile);

    Restaurant restaurant = DtoConverter.fromInDTOToEntity(restaurantInDTO);

    assertNotNull(restaurant);
    assertEquals(1, restaurant.getUserId());
    assertEquals("Test Restaurant", restaurant.getRestaurantName());
    assertEquals("123 Test St", restaurant.getRestaurantAddress());
    assertEquals("1234567890", restaurant.getContactNumber());
    assertEquals("Test Description", restaurant.getDescription());
    assertEquals(LocalDate.now(), restaurant.getRegistrationDate());
    assertTrue(restaurant.isOpen());
    assertArrayEquals(imageBytes, restaurant.getRestaurantImage());

  }

  @Test
  void fromEntityToOutDTO_ShouldConvertEntityToOutDTO() {
    Restaurant restaurant = new Restaurant();
    restaurant.setRestaurantId(1);
    restaurant.setRestaurantName("Test Restaurant");
    restaurant.setRestaurantAddress("123 Test St");
    restaurant.setContactNumber("1234567890");
    restaurant.setDescription("Test Description");
    restaurant.setRegistrationDate(LocalDate.of(2023, 10, 1));
    restaurant.setOpen(true);
    byte[] imageBytes = "sample image".getBytes();
    restaurant.setRestaurantImage(imageBytes);

    RestaurantOutDTO restaurantOutDTO = DtoConverter.fromEntityToOutDTO(restaurant);

    assertNotNull(restaurantOutDTO);
    assertEquals(1, restaurantOutDTO.getRestaurantId());
    assertEquals("Test Restaurant", restaurantOutDTO.getRestaurantName());
    assertEquals("123 Test St", restaurantOutDTO.getRestaurantAddress());
    assertEquals("1234567890", restaurantOutDTO.getContactNumber());
    assertEquals(LocalDate.of(2023, 10, 1), restaurantOutDTO.getRegistrationDate());
    assertEquals("Test Description", restaurantOutDTO.getDescription());
    assertTrue(restaurantOutDTO.isOpen());

    String expectedBase64Image = Base64.getEncoder().encodeToString(imageBytes);
    assertEquals(expectedBase64Image, restaurantOutDTO.getRestaurantImage());
  }

  @Test
  void fromEntityToOutDTO_ShouldHandleNullImage() {
    Restaurant restaurant = new Restaurant();
    restaurant.setRestaurantId(1);
    restaurant.setRestaurantName("Test Restaurant");
    restaurant.setRestaurantAddress("123 Test St");
    restaurant.setContactNumber("1234567890");
    restaurant.setDescription("Test Description");
    restaurant.setRegistrationDate(LocalDate.of(2023, 10, 1));
    restaurant.setOpen(true);
    restaurant.setRestaurantImage(null);

    RestaurantOutDTO restaurantOutDTO = DtoConverter.fromEntityToOutDTO(restaurant);

    assertNotNull(restaurantOutDTO);
    assertEquals(1, restaurantOutDTO.getRestaurantId());
    assertEquals("Test Restaurant", restaurantOutDTO.getRestaurantName());
    assertEquals("123 Test St", restaurantOutDTO.getRestaurantAddress());
    assertEquals("1234567890", restaurantOutDTO.getContactNumber());
    assertEquals(LocalDate.of(2023, 10, 1), restaurantOutDTO.getRegistrationDate());
    assertEquals("Test Description", restaurantOutDTO.getDescription());
    assertTrue(restaurantOutDTO.isOpen());
    assertNull(restaurantOutDTO.getRestaurantImage());
  }
}
