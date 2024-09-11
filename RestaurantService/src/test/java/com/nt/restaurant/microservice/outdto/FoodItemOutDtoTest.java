package com.nt.restaurant.microservice.outdto;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FoodItemOutDTOTest {

  @Test
  void testFoodItemOutDTOEqualsAndHashCode() {
    byte[] image1 = {1, 2, 3};
    byte[] image2 = {1, 2, 3};

    FoodItemOutDTO dto1 = new FoodItemOutDTO(1, 2, 3, "Pizza", "Delicious", 10.0, true, image1);
    FoodItemOutDTO dto2 = new FoodItemOutDTO(1, 2, 3, "Pizza", "Delicious", 10.0, true, image2);

    // Check equality
    assertEquals(dto1, dto2);
    // Check hash codes
    assertEquals(dto1.hashCode(), dto2.hashCode());
  }

  @Test
  void testFoodItemOutDTONotEquals() {
    byte[] image1 = {1, 2, 3};
    byte[] image2 = {4, 5, 6};

    FoodItemOutDTO dto1 = new FoodItemOutDTO(1, 2, 3, "Pizza", "Delicious", 10.0, true, image1);
    FoodItemOutDTO dto2 = new FoodItemOutDTO(2, 3, 4, "Burger", "Tasty", 12.0, false, image2);

    // Check inequality
    assertNotEquals(dto1, dto2);
  }

  @Test
  void testFoodItemOutDTOToString() {
    byte[] image = {1, 2, 3};

    FoodItemOutDTO dto = new FoodItemOutDTO(1, 2, 3, "Pizza", "Delicious", 10.0, true, image);

    String expected = "FoodItemOutDTO{" +
      "foodItemId=1" +
      ", categoryId=2" +
      ", restaurantId=3" +
      ", FoodItemName='Pizza'" +
      ", description='Delicious'" +
      ", Price=10.0" +
      ", isAvailable=true" +
      ", foodItemImage=" + Arrays.toString(image) +
      '}';
    assertEquals(expected, dto.toString());
  }

  @Test
  void testFoodItemOutDTOSettersAndGetters() {
    byte[] image = {1, 2, 3};

    FoodItemOutDTO dto = new FoodItemOutDTO();
    dto.setFoodItemId(1);
    dto.setCategoryId(2);
    dto.setRestaurantId(3);
    dto.setFoodItemName("Pizza");
    dto.setDescription("Delicious");
    dto.setPrice(10.0);
    dto.setAvailable(true);
    dto.setFoodItemImage(image);

    assertEquals(1, dto.getFoodItemId());
    assertEquals(2, dto.getCategoryId());
    assertEquals(3, dto.getRestaurantId());
    assertEquals("Pizza", dto.getFoodItemName());
    assertEquals("Delicious", dto.getDescription());
    assertEquals(10.0, dto.getPrice());
    assertTrue(dto.isAvailable());
    assertArrayEquals(image, dto.getFoodItemImage());
  }
}
