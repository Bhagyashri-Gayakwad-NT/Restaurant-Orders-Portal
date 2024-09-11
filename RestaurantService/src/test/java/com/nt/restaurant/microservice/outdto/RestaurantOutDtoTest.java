package com.nt.restaurant.microservice.outdto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RestaurantOutDTOTest {

  @Test
  void testRestaurantOutDTOEqualsAndHashCode() {
    RestaurantOutDTO dto1 = new RestaurantOutDTO(1, "Restaurant Name", "Address", "9876543210",
      LocalDate.now(), "Description", true, "imageData");
    RestaurantOutDTO dto2 = new RestaurantOutDTO(1, "Restaurant Name", "Address", "9876543210",
      LocalDate.now(), "Description", true, "imageData");

    // Check equality
    assertEquals(dto1, dto2);
    // Check hash codes
    assertEquals(dto1.hashCode(), dto2.hashCode());
  }

  @Test
  void testRestaurantOutDTONotEquals() {
    RestaurantOutDTO dto1 = new RestaurantOutDTO(1, "Restaurant Name", "Address", "9876543210",
      LocalDate.now(), "Description", true, "imageData");
    RestaurantOutDTO dto2 = new RestaurantOutDTO(2, "Another Name", "Another Address", "8765432109",
      LocalDate.now(), "Another Description", false, "imageData2");

    // Check inequality
    assertNotEquals(dto1, dto2);
  }

  @Test
  void testRestaurantOutDTOToString() {
    RestaurantOutDTO dto = new RestaurantOutDTO(1, "Restaurant Name", "Address", "9876543210",
      LocalDate.of(2024, 9, 10), "Description", true, "imageData");

    // Expected string representation
    String expected = "RestaurantOutDto{restaurantId=1, restaurantName='Restaurant Name', " +
      "restaurantAddress='Address', contactNumber='9876543210', " +
      "registrationDate=2024-09-10, description='Description', isOpen=true, " +
      "restaurantImage='imageData'}";
    assertEquals(expected, dto.toString());
  }

  @Test
  void testRestaurantOutDTOSettersAndGetters() {
    RestaurantOutDTO dto = new RestaurantOutDTO();
    dto.setRestaurantId(1);
    dto.setRestaurantName("Restaurant Name");
    dto.setRestaurantAddress("Address");
    dto.setContactNumber("9876543210");
    dto.setRegistrationDate(LocalDate.of(2024, 9, 10));
    dto.setDescription("Description");
    dto.setOpen(true);
    dto.setRestaurantImage("imageData");

    assertEquals(1, dto.getRestaurantId());
    assertEquals("Restaurant Name", dto.getRestaurantName());
    assertEquals("Address", dto.getRestaurantAddress());
    assertEquals("9876543210", dto.getContactNumber());
    assertEquals(LocalDate.of(2024, 9, 10), dto.getRegistrationDate());
    assertEquals("Description", dto.getDescription());
    assertTrue(dto.isOpen());
    assertEquals("imageData", dto.getRestaurantImage());
  }
}