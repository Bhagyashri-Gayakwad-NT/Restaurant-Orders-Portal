package com.nt.restaurant.microservice.outdto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FoodCategoryOutDTOTest {

  private FoodCategoryOutDTO dto1;
  private FoodCategoryOutDTO dto2;

  @BeforeEach
  void setUp() {
    dto1 = new FoodCategoryOutDTO(1, 100, "Appetizers");
    dto2 = new FoodCategoryOutDTO(1, 100, "Appetizers");
  }

  @Test
  void testDefaultConstructor() {
    FoodCategoryOutDTO dto = new FoodCategoryOutDTO();
    assertNull(dto.getFoodCategoryId());
    assertNull(dto.getRestaurantId());
    assertNull(dto.getFoodCategoryName());
  }

  @Test
  void testParameterizedConstructor() {
    FoodCategoryOutDTO dto = new FoodCategoryOutDTO(2, 200, "Main Course");
    assertEquals(2, dto.getFoodCategoryId());
    assertEquals(200, dto.getRestaurantId());
    assertEquals("Main Course", dto.getFoodCategoryName());
  }

  @Test
  void testGettersAndSetters() {
    dto1.setFoodCategoryId(10);
    dto1.setRestaurantId(200);
    dto1.setFoodCategoryName("Desserts");

    assertEquals(10, dto1.getFoodCategoryId());
    assertEquals(200, dto1.getRestaurantId());
    assertEquals("Desserts", dto1.getFoodCategoryName());
  }

  @Test
  void testEqualsAndHashCode() {
    FoodCategoryOutDTO dto2 = new FoodCategoryOutDTO(1, 100, "Appetizers");
    assertEquals(dto1, dto2);
    assertEquals(dto1.hashCode(), dto2.hashCode());

    FoodCategoryOutDTO dto3 = new FoodCategoryOutDTO(2, 100, "Appetizers");
    assertNotEquals(dto1, dto3);
    assertNotEquals(dto1.hashCode(), dto3.hashCode());

    FoodCategoryOutDTO dto4 = new FoodCategoryOutDTO(1, 200, "Appetizers");
    assertNotEquals(dto1, dto4);
    assertNotEquals(dto1.hashCode(), dto4.hashCode());

    FoodCategoryOutDTO dto5 = new FoodCategoryOutDTO(1, 100, "Main Course");
    assertNotEquals(dto1, dto5);
    assertNotEquals(dto1.hashCode(), dto5.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "FoodCategoryOutDTO{" +
      "foodCategoryId=" + dto1.getFoodCategoryId() +
      ", restaurantId=" + dto1.getRestaurantId() +
      ", foodCategoryName='" + dto1.getFoodCategoryName() + '\'' +
      '}';
    assertEquals(expectedString, dto1.toString());
  }
}

