package com.nt.restaurant.microservice.indto;

import com.nt.restaurant.microservice.dto.FoodItemUpdateInDTO;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FoodItemUpdateInDTOTest {

  @Test
  void testDefaultConstructor() {
    FoodItemUpdateInDTO dto = new FoodItemUpdateInDTO();
    assertNull(dto.getFoodItemName());
    assertNull(dto.getDescription());
    assertNull(dto.getPrice());
    assertNull(dto.getFoodItemImage());
  }

  @Test
  void testParameterizedConstructor() {
    MultipartFile image = new MockMultipartFile("file", "filename.png", "image/png", new byte[1]);
    FoodItemUpdateInDTO dto = new FoodItemUpdateInDTO("Pizza", "Delicious pizza", 10.99, image);

    assertEquals("Pizza", dto.getFoodItemName());
    assertEquals("Delicious pizza", dto.getDescription());
    assertEquals(10.99, dto.getPrice());
    assertEquals(image, dto.getFoodItemImage());
  }

  @Test
  void testGettersAndSetters() {
    FoodItemUpdateInDTO dto = new FoodItemUpdateInDTO();
    dto.setFoodItemName("Burger");
    dto.setDescription("Juicy burger");
    dto.setPrice(5.99);
    MultipartFile image = new MockMultipartFile("file", "burger.png", "image/png", new byte[1]);
    dto.setFoodItemImage(image);

    assertEquals("Burger", dto.getFoodItemName());
    assertEquals("Juicy burger", dto.getDescription());
    assertEquals(5.99, dto.getPrice());
    assertEquals(image, dto.getFoodItemImage());
  }

  @Test
  void testEquals() {
    MultipartFile image = new MockMultipartFile("file", "filename.png", "image/png", new byte[1]);
    FoodItemUpdateInDTO dto1 = new FoodItemUpdateInDTO("Pasta", "Creamy pasta", 7.99, image);
    FoodItemUpdateInDTO dto2 = new FoodItemUpdateInDTO("Pasta", "Creamy pasta", 7.99, image);
    FoodItemUpdateInDTO dto3 = new FoodItemUpdateInDTO("Pizza", "Delicious pizza", 10.99, image);

    assertTrue(dto1.equals(dto2));
    assertFalse(dto1.equals(dto3));
    assertFalse(dto1.equals(null));
    assertFalse(dto1.equals(new Object()));
  }

  @Test
  void testHashCode() {
    MultipartFile image = new MockMultipartFile("file", "filename.png", "image/png", new byte[1]);
    FoodItemUpdateInDTO dto1 = new FoodItemUpdateInDTO("Sushi", "Fresh sushi", 12.99, image);
    FoodItemUpdateInDTO dto2 = new FoodItemUpdateInDTO("Sushi", "Fresh sushi", 12.99, image);

    assertEquals(dto1.hashCode(), dto2.hashCode());
  }

  @Test
  void testToString() {
    MultipartFile image = new MockMultipartFile("file", "filename.png", "image/png", new byte[1]);
    FoodItemUpdateInDTO dto = new FoodItemUpdateInDTO("Noodles", "Spicy noodles", 8.99, image);

    String expected = "FoodItemUpdateInDTO{" +
      "foodItemName='Noodles'" +
      ", description='Spicy noodles'" +
      ", price=8.99" +
      ", foodItemImage=" + image +
      '}';
    assertEquals(expected, dto.toString());
  }
}
