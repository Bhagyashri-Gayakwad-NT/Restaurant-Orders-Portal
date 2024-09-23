package com.nt.restaurant.microservice.outdto;

import com.nt.restaurant.microservice.dto.FoodCategoryOutDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FoodCategoryOutDTOTest {

  private FoodCategoryOutDTO dto1;
  private FoodCategoryOutDTO dto2;

  @BeforeEach
  void setUp() {
    dto1 = new FoodCategoryOutDTO(1, 100, "FoodCategory");
    dto2 = new FoodCategoryOutDTO(1, 100, "FoodCategory");
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
    FoodCategoryOutDTO dto = new FoodCategoryOutDTO(2, 200, "FoodName");
    assertEquals(2, dto.getFoodCategoryId());
    assertEquals(200, dto.getRestaurantId());
    assertEquals("FoodName", dto.getFoodCategoryName());
  }

  @Test
  void testGettersAndSetters() {
    dto1.setFoodCategoryId(10);
    dto1.setRestaurantId(200);
    dto1.setFoodCategoryName("Category");

    assertEquals(10, dto1.getFoodCategoryId());
    assertEquals(200, dto1.getRestaurantId());
    assertEquals("Category", dto1.getFoodCategoryName());
  }

  @Test
  void testEqualsAndHashCode() {
    FoodCategoryOutDTO dto2 = new FoodCategoryOutDTO(1, 100, "FoodCategory");
    assertEquals(dto1, dto2);
    assertEquals(dto1.hashCode(), dto2.hashCode());

    FoodCategoryOutDTO dto3 = new FoodCategoryOutDTO(2, 100, "FoodCategory");
    assertNotEquals(dto1, dto3);
    assertNotEquals(dto1.hashCode(), dto3.hashCode());

    FoodCategoryOutDTO dto4 = new FoodCategoryOutDTO(1, 200, "FoodCategory");
    assertNotEquals(dto1, dto4);
    assertNotEquals(dto1.hashCode(), dto4.hashCode());

    FoodCategoryOutDTO dto5 = new FoodCategoryOutDTO(1, 100, "FoodName");
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

