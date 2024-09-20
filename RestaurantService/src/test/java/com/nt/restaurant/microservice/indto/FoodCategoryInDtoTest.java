package com.nt.restaurant.microservice.indto;

import com.nt.restaurant.microservice.dto.FoodCategoryInDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodCategoryInDTOTest {

  @Test
  void testDefaultConstructor() {
    FoodCategoryInDTO foodCategoryInDTO = new FoodCategoryInDTO();
    assertNull(foodCategoryInDTO.getRestaurantId());
    assertNull(foodCategoryInDTO.getFoodCategoryName());
  }

  @Test
  void testParameterizedConstructor() {
    FoodCategoryInDTO foodCategoryInDTO = new FoodCategoryInDTO(1, "Appetizers");

    assertEquals(1, foodCategoryInDTO.getRestaurantId());
    assertEquals("Appetizers", foodCategoryInDTO.getFoodCategoryName());
  }

  @Test
  void testSettersAndGetters() {
    FoodCategoryInDTO foodCategoryInDTO = new FoodCategoryInDTO();

    foodCategoryInDTO.setRestaurantId(2);
    foodCategoryInDTO.setFoodCategoryName("Main Course");

    assertEquals(2, foodCategoryInDTO.getRestaurantId());
    assertEquals("Main Course", foodCategoryInDTO.getFoodCategoryName());
  }

  @Test
  void testEquals() {
    FoodCategoryInDTO foodCategoryInDTO1 = new FoodCategoryInDTO(1, "Desserts");
    FoodCategoryInDTO foodCategoryInDTO2 = new FoodCategoryInDTO(1, "Desserts");
    FoodCategoryInDTO foodCategoryInDTO3 = new FoodCategoryInDTO(2, "Beverages");

    assertEquals(foodCategoryInDTO1, foodCategoryInDTO2);
    assertNotEquals(foodCategoryInDTO1, foodCategoryInDTO3);
  }

  @Test
  void testHashCode() {
    FoodCategoryInDTO foodCategoryInDTO = new FoodCategoryInDTO(1, "Snacks");

    assertNotNull(foodCategoryInDTO.hashCode());
  }

  @Test
  void testToString() {
    FoodCategoryInDTO foodCategoryInDTO = new FoodCategoryInDTO(1, "Salads");

    String expectedString = "FoodCategoryInDTO{" +
      "restaurantId=1" +
      ", foodCategoryName='Salads'" +
      '}';

    assertEquals(expectedString, foodCategoryInDTO.toString());
  }
}
