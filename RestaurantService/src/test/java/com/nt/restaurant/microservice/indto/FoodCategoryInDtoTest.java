package com.nt.restaurant.microservice.indto;

import com.nt.restaurant.microservice.dto.FoodCategoryInDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class FoodCategoryInDTOTest {

  @Test
  void testDefaultConstructor() {
    FoodCategoryInDTO foodCategoryInDTO = new FoodCategoryInDTO();
    assertNull(foodCategoryInDTO.getRestaurantId());
    assertNull(foodCategoryInDTO.getFoodCategoryName());
  }

  @Test
  void testParameterizedConstructor() {
    FoodCategoryInDTO foodCategoryInDTO = new FoodCategoryInDTO(1, "Test Category");

    assertEquals(1, foodCategoryInDTO.getRestaurantId());
    assertEquals("Test Category", foodCategoryInDTO.getFoodCategoryName());
  }

  @Test
  void testSettersAndGetters() {
    FoodCategoryInDTO foodCategoryInDTO = new FoodCategoryInDTO();

    foodCategoryInDTO.setRestaurantId(2);
    foodCategoryInDTO.setFoodCategoryName("Category");

    assertEquals(2, foodCategoryInDTO.getRestaurantId());
    assertEquals("Category", foodCategoryInDTO.getFoodCategoryName());
  }

  @Test
  void testEquals() {
    FoodCategoryInDTO foodCategoryInDTO1 = new FoodCategoryInDTO(1, "CategoryOne");
    FoodCategoryInDTO foodCategoryInDTO2 = new FoodCategoryInDTO(1, "CategoryOne");
    FoodCategoryInDTO foodCategoryInDTO3 = new FoodCategoryInDTO(2, "CategoryTwo");

    assertEquals(foodCategoryInDTO1, foodCategoryInDTO2);
    assertNotEquals(foodCategoryInDTO1, foodCategoryInDTO3);
  }

  @Test
  void testHashCode() {
    FoodCategoryInDTO foodCategoryInDTO = new FoodCategoryInDTO(1, "FoodOne");

    assertNotNull(foodCategoryInDTO.hashCode());
  }

  @Test
  void testToString() {
    FoodCategoryInDTO foodCategoryInDTO = new FoodCategoryInDTO(1, "FoodTwo");

    String expectedString = "FoodCategoryInDTO{" +
      "restaurantId=1" +
      ", foodCategoryName='FoodTwo'" +
      '}';

    assertEquals(expectedString, foodCategoryInDTO.toString());
  }
}
