package com.nt.restaurant.microservice.indto;

import com.nt.restaurant.microservice.dto.FoodItemInDTO;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.*;

class FoodItemInDTOTest {

  @Test
  void testDefaultConstructor() {
    FoodItemInDTO foodItemInDTO = new FoodItemInDTO();
    assertNull(foodItemInDTO.getFoodCategoryId());
    assertNull(foodItemInDTO.getRestaurantId());
    assertNull(foodItemInDTO.getFoodItemName());
    assertNull(foodItemInDTO.getDescription());
    assertNull(foodItemInDTO.getPrice());
    assertFalse(foodItemInDTO.isAvailable());
    assertNull(foodItemInDTO.getFoodItemImage());
  }

  @Test
  void testParameterizedConstructor() {
    MultipartFile image = new MockMultipartFile("image", new byte[0]);
    FoodItemInDTO foodItemInDTO = new FoodItemInDTO(1, 1, "Pasta", "Delicious pasta", 12.99, true, image);

    assertEquals(1, foodItemInDTO.getFoodCategoryId());
    assertEquals(1, foodItemInDTO.getRestaurantId());
    assertEquals("Pasta", foodItemInDTO.getFoodItemName());
    assertEquals("Delicious pasta", foodItemInDTO.getDescription());
    assertEquals(12.99, foodItemInDTO.getPrice());
    assertTrue(foodItemInDTO.isAvailable());
    assertEquals(image, foodItemInDTO.getFoodItemImage());
  }

  @Test
  void testSettersAndGetters() {
    FoodItemInDTO foodItemInDTO = new FoodItemInDTO();
    MultipartFile image = new MockMultipartFile("image", new byte[0]);

    foodItemInDTO.setFoodCategoryId(2);
    foodItemInDTO.setRestaurantId(3);
    foodItemInDTO.setFoodItemName("Burger");
    foodItemInDTO.setDescription("Tasty burger");
    foodItemInDTO.setPrice(8.50);
    foodItemInDTO.setAvailable(false);
    foodItemInDTO.setFoodItemImage(image);

    assertEquals(2, foodItemInDTO.getFoodCategoryId());
    assertEquals(3, foodItemInDTO.getRestaurantId());
    assertEquals("Burger", foodItemInDTO.getFoodItemName());
    assertEquals("Tasty burger", foodItemInDTO.getDescription());
    assertEquals(8.50, foodItemInDTO.getPrice());
    assertFalse(foodItemInDTO.isAvailable());
    assertEquals(image, foodItemInDTO.getFoodItemImage());
  }

  @Test
  void testEquals() {
    MultipartFile image = new MockMultipartFile("image", new byte[0]);
    FoodItemInDTO foodItemInDTO1 = new FoodItemInDTO(1, 1, "Pizza", "Cheesy pizza", 10.00, true, image);
    FoodItemInDTO foodItemInDTO2 = new FoodItemInDTO(1, 1, "Pizza", "Cheesy pizza", 10.00, true, image);
    FoodItemInDTO foodItemInDTO3 = new FoodItemInDTO(2, 1, "Pizza", "Cheesy pizza", 10.00, true, image);

    assertEquals(foodItemInDTO1, foodItemInDTO2);
    assertNotEquals(foodItemInDTO1, foodItemInDTO3);
  }

  @Test
  void testHashCode() {
    MultipartFile image = new MockMultipartFile("image", new byte[0]);
    FoodItemInDTO foodItemInDTO = new FoodItemInDTO(1, 1, "Tacos", "Spicy tacos", 9.99, true, image);

    assertNotNull(foodItemInDTO.hashCode());
  }

  @Test
  void testToString() {
    MultipartFile image = new MockMultipartFile("image", new byte[0]);
    FoodItemInDTO foodItemInDTO = new FoodItemInDTO(1, 1, "Salad", "Healthy salad", 5.99, true, image);

    String expectedString = "FoodItemInDTO{" +
      "foodCategoryId=1" +
      ", restaurantId=1" +
      ", foodItemName='Salad'" +
      ", description='Healthy salad'" +
      ", price=5.99" +
      ", isAvailable=true" +
      ", foodItemImage=" + image +
      '}';

    assertEquals(expectedString, foodItemInDTO.toString());
  }
}
