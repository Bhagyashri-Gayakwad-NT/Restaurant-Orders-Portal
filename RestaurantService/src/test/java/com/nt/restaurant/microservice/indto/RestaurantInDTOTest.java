package com.nt.restaurant.microservice.indto;

import com.nt.restaurant.microservice.dto.RestaurantInDTO;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RestaurantInDTOTest {

  @Test
  void testDefaultConstructor() {
    RestaurantInDTO restaurantInDTO = new RestaurantInDTO();
    assertNull(restaurantInDTO.getUserId());
    assertNull(restaurantInDTO.getRestaurantName());
    assertNull(restaurantInDTO.getRestaurantAddress());
    assertNull(restaurantInDTO.getContactNumber());
    assertNull(restaurantInDTO.getDescription());
    assertNull(restaurantInDTO.getRestaurantImage());
  }

  @Test
  void testParameterizedConstructor() {
    MultipartFile mockImage = mock(MultipartFile.class);
    RestaurantInDTO restaurantInDTO = new RestaurantInDTO(
      1,
      "Test Restaurant",
      "123 Test St",
      "9876543210",
      "Delicious food",
      mockImage
    );

    assertEquals(1, restaurantInDTO.getUserId());
    assertEquals("Test Restaurant", restaurantInDTO.getRestaurantName());
    assertEquals("123 Test St", restaurantInDTO.getRestaurantAddress());
    assertEquals("9876543210", restaurantInDTO.getContactNumber());
    assertEquals("Delicious food", restaurantInDTO.getDescription());
    assertEquals(mockImage, restaurantInDTO.getRestaurantImage());
  }

  @Test
  void testSettersAndGetters() {
    RestaurantInDTO restaurantInDTO = new RestaurantInDTO();
    MultipartFile mockImage = mock(MultipartFile.class);

    restaurantInDTO.setUserId(1);
    restaurantInDTO.setRestaurantName("Another Restaurant");
    restaurantInDTO.setRestaurantAddress("456 Test Ave");
    restaurantInDTO.setContactNumber("9876543211");
    restaurantInDTO.setDescription("Tasty meals");
    restaurantInDTO.setRestaurantImage(mockImage);

    assertEquals(1, restaurantInDTO.getUserId());
    assertEquals("Another Restaurant", restaurantInDTO.getRestaurantName());
    assertEquals("456 Test Ave", restaurantInDTO.getRestaurantAddress());
    assertEquals("9876543211", restaurantInDTO.getContactNumber());
    assertEquals("Tasty meals", restaurantInDTO.getDescription());
    assertEquals(mockImage, restaurantInDTO.getRestaurantImage());
  }

  @Test
  void testEquals() {
    MultipartFile mockImage1 = mock(MultipartFile.class);
    MultipartFile mockImage2 = mock(MultipartFile.class);

    RestaurantInDTO restaurantInDTO1 = new RestaurantInDTO(1, "Restaurant A", "Address A", "9876543210", "Description A", mockImage1);
    RestaurantInDTO restaurantInDTO2 = new RestaurantInDTO(1, "Restaurant A", "Address A", "9876543210", "Description A", mockImage1);
    RestaurantInDTO restaurantInDTO3 = new RestaurantInDTO(2, "Restaurant B", "Address B", "9876543211", "Description B", mockImage2);

    assertEquals(restaurantInDTO1, restaurantInDTO2);
    assertNotEquals(restaurantInDTO1, restaurantInDTO3);
  }

  @Test
  void testHashCode() {
    MultipartFile mockImage = mock(MultipartFile.class);
    RestaurantInDTO restaurantInDTO = new RestaurantInDTO(1, "Test Restaurant", "123 Test St",
      "9876543210", "Delicious food", mockImage);

    assertNotNull(restaurantInDTO.hashCode());
  }

  @Test
  void testToString() {
    MultipartFile mockImage = mock(MultipartFile.class);
    RestaurantInDTO restaurantInDTO = new RestaurantInDTO(1, "Test Restaurant",
      "123 Test St", "9876543210", "Delicious food", mockImage);

    String expectedString = "RestaurantInDto{" +
      "userId=1" +
      ", restaurantName='Test Restaurant'" +
      ", restaurantAddress='123 Test St'" +
      ", contactNumber='9876543210'" +
      ", description='Delicious food'" +
      ", restaurantImage=" + mockImage +
      '}';

    assertEquals(expectedString, restaurantInDTO.toString());
  }
}
