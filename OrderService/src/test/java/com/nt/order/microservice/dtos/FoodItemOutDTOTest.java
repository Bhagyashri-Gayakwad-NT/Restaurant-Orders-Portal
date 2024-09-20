package com.nt.order.microservice.dtos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;

class FoodItemOutDTOTest {

  @Test
  void testDefaultConstructor() {
    // Given
    FoodItemOutDTO dto = new FoodItemOutDTO();

    // Then
    assertNull(dto.getFoodItemId());
    assertNull(dto.getCategoryId());
    assertNull(dto.getRestaurantId());
    assertNull(dto.getFoodItemName());
    assertNull(dto.getDescription());
    assertNull(dto.getPrice());
    assertFalse(dto.isAvailable());
    assertNull(dto.getFoodItemImage());
  }

  @Test
  void testParameterizedConstructor() {
    // Given
    Integer foodItemId = 1;
    Integer categoryId = 2;
    Integer restaurantId = 3;
    String foodItemName = "SampleFood";
    String description = "Sample Description";
    Double price = 100.0;
    boolean isAvailable = true;
    byte[] foodItemImage = new byte[]{1, 2, 3};

    // When
    FoodItemOutDTO dto = new FoodItemOutDTO(foodItemId, categoryId, restaurantId, foodItemName, description, price, isAvailable, foodItemImage);

    // Then
    assertEquals(foodItemId, dto.getFoodItemId());
    assertEquals(categoryId, dto.getCategoryId());
    assertEquals(restaurantId, dto.getRestaurantId());
    assertEquals(foodItemName, dto.getFoodItemName());
    assertEquals(description, dto.getDescription());
    assertEquals(price, dto.getPrice());
    assertTrue(dto.isAvailable());
    assertArrayEquals(foodItemImage, dto.getFoodItemImage());
  }

  @Test
  void testSettersAndGetters() {
    // Given
    FoodItemOutDTO dto = new FoodItemOutDTO();
    Integer foodItemId = 4;
    Integer categoryId = 5;
    Integer restaurantId = 6;
    String foodItemName = "AnotherFood";
    String description = "Another Description";
    Double price = 200.0;
    boolean isAvailable = false;
    byte[] foodItemImage = new byte[]{4, 5, 6};

    // When
    dto.setFoodItemId(foodItemId);
    dto.setCategoryId(categoryId);
    dto.setRestaurantId(restaurantId);
    dto.setFoodItemName(foodItemName);
    dto.setDescription(description);
    dto.setPrice(price);
    dto.setAvailable(isAvailable);
    dto.setFoodItemImage(foodItemImage);

    // Then
    assertEquals(foodItemId, dto.getFoodItemId());
    assertEquals(categoryId, dto.getCategoryId());
    assertEquals(restaurantId, dto.getRestaurantId());
    assertEquals(foodItemName, dto.getFoodItemName());
    assertEquals(description, dto.getDescription());
    assertEquals(price, dto.getPrice());
    assertFalse(dto.isAvailable());
    assertArrayEquals(foodItemImage, dto.getFoodItemImage());
  }

  @Test
  void testEqualsAndHashCode() {
    // Given
    FoodItemOutDTO dto1 = new FoodItemOutDTO(1, 2, 3, "FoodName", "Description", 100.0, true, new byte[]{1, 2, 3});
    FoodItemOutDTO dto2 = new FoodItemOutDTO(1, 2, 3, "FoodName", "Description", 100.0, true, new byte[]{1, 2, 3});
    FoodItemOutDTO dto3 = new FoodItemOutDTO(2, 3, 4, "DifferentFood", "Different Description", 200.0, false, new byte[]{4, 5, 6});

    // Then
    assertEquals(dto1, dto2);
    assertNotEquals(dto1, dto3);
    assertEquals(dto1.hashCode(), dto2.hashCode());
    assertNotEquals(dto1.hashCode(), dto3.hashCode());
  }

  @Test
  void testToString() {
    // Given
    FoodItemOutDTO dto = new FoodItemOutDTO(1, 2, 3, "FoodItem", "Food Description", 150.0, true, new byte[]{7, 8, 9});

    // When
    String result = dto.toString();

    // Then
    assertTrue(result.contains("foodItemId=1"));
    assertTrue(result.contains("categoryId=2"));
    assertTrue(result.contains("restaurantId=3"));
    assertTrue(result.contains("FoodItemName='FoodItem'"));
    assertTrue(result.contains("description='Food Description'"));
    assertTrue(result.contains("Price=150.0"));
    assertTrue(result.contains("isAvailable=true"));
    assertTrue(result.contains("foodItemImage=[7, 8, 9]"));
  }
}
