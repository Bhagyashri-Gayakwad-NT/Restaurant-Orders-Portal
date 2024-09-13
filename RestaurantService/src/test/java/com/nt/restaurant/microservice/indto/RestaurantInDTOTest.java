package com.nt.restaurant.microservice.indto;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RestaurantInDTOTest {

  private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private final Validator validator = factory.getValidator();

  @Test
  void testConstructorAndGetters() {
    MockMultipartFile image = new MockMultipartFile("restaurantImage", "restaurant.jpg", "image/jpeg", new byte[] {1, 2, 3});
    RestaurantInDTO dto = new RestaurantInDTO(1, "My Restaurant", "123 Street", "9876543210", "Great restaurant", image);

    assertEquals(1, dto.getUserId());
    assertEquals("My Restaurant", dto.getRestaurantName());
    assertEquals("123 Street", dto.getRestaurantAddress());
    assertEquals("9876543210", dto.getContactNumber());
    assertEquals("Great restaurant", dto.getDescription());
    assertEquals(image, dto.getRestaurantImage());
  }

  @Test
  void testSetters() {
    MockMultipartFile image = new MockMultipartFile("restaurantImage", "restaurant.jpg", "image/jpeg", new byte[] {1, 2, 3});
    RestaurantInDTO dto = new RestaurantInDTO();

    dto.setUserId(2);
    dto.setRestaurantName("Another Restaurant");
    dto.setRestaurantAddress("456 Avenue");
    dto.setContactNumber("8765432109");
    dto.setDescription("Nice place to eat");
    dto.setRestaurantImage(image);

    assertEquals(2, dto.getUserId());
    assertEquals("Another Restaurant", dto.getRestaurantName());
    assertEquals("456 Avenue", dto.getRestaurantAddress());
    assertEquals("8765432109", dto.getContactNumber());
    assertEquals("Nice place to eat", dto.getDescription());
    assertEquals(image, dto.getRestaurantImage());
  }

  @Test
  void testNotEquals() {
    MockMultipartFile image1 = new MockMultipartFile("restaurantImage", "restaurant.jpg", "image/jpeg", new byte[] {1, 2, 3});
    MockMultipartFile image2 = new MockMultipartFile("restaurantImage", "restaurant.jpg", "image/jpeg", new byte[] {4, 5, 6});

    RestaurantInDTO dto1 = new RestaurantInDTO(1, "Restaurant A", "123 Street", "9876543210", "Good place", image1);
    RestaurantInDTO dto2 = new RestaurantInDTO(2, "Restaurant B", "456 Avenue", "8765432109", "Nice place", image2);

    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());
  }

  @Test
  void testToString() {
    MockMultipartFile image = new MockMultipartFile("restaurantImage", "restaurant.jpg", "image/jpeg", new byte[] {1, 2, 3});
    RestaurantInDTO dto = new RestaurantInDTO(1, "Restaurant A", "123 Street", "9876543210", "Good place", image);

    String expected =
      "RestaurantInDto{userId=1, restaurantName='Restaurant A', " +
        "restaurantAddress='123 Street', contactNumber='9876543210'," +
        " description='Good place', restaurantImage=" +
        image + "}";
    assertEquals(expected, dto.toString());
  }

  @Test
  void testValidation_Success() {
    MockMultipartFile image = new MockMultipartFile("restaurantImage", "restaurant.jpg", "image/jpeg", new byte[] {1, 2, 3});
    RestaurantInDTO dto = new RestaurantInDTO(1, "Restaurant", "123 Street", "9876543210", "Good restaurant", image);

    Set<ConstraintViolation<RestaurantInDTO>> violations = validator.validate(dto);
    assertTrue(violations.isEmpty());
  }

  @Test
  void testValidation_Failure() {
    MockMultipartFile image = new MockMultipartFile("restaurantImage", "restaurant.jpg", "image/jpeg", new byte[] {1, 2, 3});
    RestaurantInDTO dto =
      new RestaurantInDTO(null, "", "", "123", "Description that is way too long.........................................." +
        "...................................." +
        ".........................................." +
        "..................................................",
        image);

    Set<ConstraintViolation<RestaurantInDTO>> violations = validator.validate(dto);
    assertEquals(6, violations.size());
  }
}
