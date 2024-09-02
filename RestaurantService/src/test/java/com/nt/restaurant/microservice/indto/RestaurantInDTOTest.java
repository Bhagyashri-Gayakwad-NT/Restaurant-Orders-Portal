package com.nt.restaurant.microservice.indto;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantInDTOTest {

  @Test
  void testRestaurantInDTOEqualsAndHashCode() {
    MockMultipartFile image = new MockMultipartFile("restaurantImage", "test.jpg", "image/jpeg", new byte[] {1, 2, 3});
    RestaurantInDTO dto1 = new RestaurantInDTO(101, "Restaurant Name", "Address", "9876543210",
      "Description", image);
    RestaurantInDTO dto2 = new RestaurantInDTO(101, "Restaurant Name", "Address", "9876543210",
      "Description", image);

    assertEquals(dto1, dto2);
    assertEquals(dto1.hashCode(), dto2.hashCode());
  }

  @Test
  void testRestaurantInDTONotEquals() {
    MockMultipartFile image1 = new MockMultipartFile("restaurantImage", "test.jpg", "image/jpeg", new byte[] {1, 2, 3});
    MockMultipartFile image2 = new MockMultipartFile("restaurantImage", "test2.jpg", "image/jpeg", new byte[] {4, 5, 6});
    RestaurantInDTO dto1 = new RestaurantInDTO(101, "Restaurant Name", "Address", "9876543210",
      "Description", image1);
    RestaurantInDTO dto2 = new RestaurantInDTO(102, "Another Name", "Another Address", "8765432109",
      "Another Description", image2);

    assertNotEquals(dto1, dto2);
  }

  @Test
  void testRestaurantInDTOToString() {
    MockMultipartFile image = new MockMultipartFile("restaurantImage", "test.jpg", "image/jpeg", new byte[] {1, 2, 3});
    RestaurantInDTO dto = new RestaurantInDTO(101, "Restaurant Name", "Address", "9876543210",
      "Description", image);

    String expected = "RestaurantInDto{userId=101, restaurantName='Restaurant Name', " +
      "restaurantAddress='Address', contactNumber='9876543210', " +
      "description='Description', restaurantImage=" + image.toString() + "}"; // Use image.toString()

    assertEquals(expected, dto.toString());
  }
}
