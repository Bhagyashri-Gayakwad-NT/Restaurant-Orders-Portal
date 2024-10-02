package com.nt.order.microservice.dtos;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RestaurantOutDTOTest {

  @Test
  void testNoArgsConstructor() {
    // Given
    RestaurantOutDTO dto = new RestaurantOutDTO();

    // Then
    assertNull(dto.getRestaurantId(), "Restaurant ID should be null");
    assertNull(dto.getRestaurantName(), "Restaurant name should be null");
    assertNull(dto.getRestaurantAddress(), "Restaurant address should be null");
    assertNull(dto.getContactNumber(), "Contact number should be null");
    assertNull(dto.getRegistrationDate(), "Registration date should be null");
    assertNull(dto.getDescription(), "Description should be null");
    assertFalse(dto.isOpen(), "Restaurant should be closed by default");
    assertNull(dto.getRestaurantImage(), "Restaurant image should be null");
  }

  @Test
  void testAllArgsConstructor() {
    // Given
    Integer restaurantId = 1;
    String restaurantName = "Test Restaurant";
    String restaurantAddress = "Test Address";
    String contactNumber = "1234567890";
    LocalDate registrationDate = LocalDate.now();
    String description = "Test Description";
    boolean isOpen = true;
    String restaurantImage = "TestImage.png";

    // When
    RestaurantOutDTO dto = new RestaurantOutDTO(restaurantId, restaurantName, restaurantAddress, contactNumber,
      registrationDate, description, isOpen, restaurantImage);

    // Then
    assertEquals(restaurantId, dto.getRestaurantId());
    assertEquals(restaurantName, dto.getRestaurantName());
    assertEquals(restaurantAddress, dto.getRestaurantAddress());
    assertEquals(contactNumber, dto.getContactNumber());
    assertEquals(registrationDate, dto.getRegistrationDate());
    assertEquals(description, dto.getDescription());
    assertTrue(dto.isOpen());
    assertEquals(restaurantImage, dto.getRestaurantImage());
  }

  @Test
  void testSettersAndGetters() {
    // Given
    RestaurantOutDTO dto = new RestaurantOutDTO();
    Integer restaurantId = 2;
    String restaurantName = "Another Test Restaurant";
    String restaurantAddress = "Another Address";
    String contactNumber = "0987654321";
    LocalDate registrationDate = LocalDate.now();
    String description = "Another Description";
    boolean isOpen = false;
    String restaurantImage = "AnotherImage.png";

    // When
    dto.setRestaurantId(restaurantId);
    dto.setRestaurantName(restaurantName);
    dto.setRestaurantAddress(restaurantAddress);
    dto.setContactNumber(contactNumber);
    dto.setRegistrationDate(registrationDate);
    dto.setDescription(description);
    dto.setOpen(isOpen);
    dto.setRestaurantImage(restaurantImage);

    // Then
    assertEquals(restaurantId, dto.getRestaurantId());
    assertEquals(restaurantName, dto.getRestaurantName());
    assertEquals(restaurantAddress, dto.getRestaurantAddress());
    assertEquals(contactNumber, dto.getContactNumber());
    assertEquals(registrationDate, dto.getRegistrationDate());
    assertEquals(description, dto.getDescription());
    assertFalse(dto.isOpen());
    assertEquals(restaurantImage, dto.getRestaurantImage());
  }

  @Test
  void testEqualsAndHashCode() {
    // Given
    RestaurantOutDTO dto1 = new RestaurantOutDTO(1, "Test Restaurant", "Test Address", "1234567890",
      LocalDate.now(), "Test Description", true, "TestImage.png");
    RestaurantOutDTO dto2 = new RestaurantOutDTO(1, "Test Restaurant", "Test Address", "1234567890",
      LocalDate.now(), "Test Description", true, "TestImage.png");

    // Then
    assertEquals(dto1, dto2, "DTO objects should be equal");
    assertEquals(dto1.hashCode(), dto2.hashCode(), "Hash codes should match for equal DTO objects");
  }

  @Test
  void testToString() {
    // Given
    RestaurantOutDTO dto = new RestaurantOutDTO(1, "Test Restaurant", "Test Address", "1234567890",
      LocalDate.now(), "Test Description", true, "TestImage.png");

    // Then
    String expected = "RestaurantOutDTO{restaurantId=1, restaurantName='Test Restaurant', restaurantAddress='Test Address', " +
      "contactNumber='1234567890', registrationDate=" + LocalDate.now() + ", description='Test Description', isOpen=true, " +
      "restaurantImage='TestImage.png'}";
    assertEquals(expected, dto.toString(), "The toString output should match the expected format");
  }
}
