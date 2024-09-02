package com.nt.restaurant.microservice.outdto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantOutDTOTest {

  @Test
  void testRestaurantOutDTOEqualsAndHashCode() {
    RestaurantOutDTO dto1 = new RestaurantOutDTO(1, "Restaurant Name", "Address", "9876543210",
      LocalDate.now(), "Description", true, "imageData");
    RestaurantOutDTO dto2 = new RestaurantOutDTO(1, "Restaurant Name", "Address", "9876543210",
      LocalDate.now(), "Description", true, "imageData");

    assertEquals(dto1, dto2);
    assertEquals(dto1.hashCode(), dto2.hashCode());
  }

  @Test
  void testRestaurantOutDTONotEquals() {
    RestaurantOutDTO dto1 = new RestaurantOutDTO(1, "Restaurant Name", "Address", "9876543210",
      LocalDate.now(), "Description", true, "imageData");
    RestaurantOutDTO dto2 = new RestaurantOutDTO(2, "Another Name", "Another Address", "8765432109",
      LocalDate.now(), "Another Description", false, "imageData2");

    assertNotEquals(dto1, dto2);
  }

  @Test
  void testRestaurantOutDTOToString() {
    RestaurantOutDTO dto = new RestaurantOutDTO(1, "Restaurant Name", "Address", "9876543210",
      LocalDate.now(), "Description", true, "imageData");

    String expected = "RestaurantOutDto{restaurantId=1, restaurantName='Restaurant Name', " +
      "restaurantAddress='Address', contactNumber='9876543210', " +
      "registrationDate=" + LocalDate.now() + ", description='Description', isOpen=true, " +
      "restaurantImage='imageData'}";
    assertEquals(expected, dto.toString());
  }
}