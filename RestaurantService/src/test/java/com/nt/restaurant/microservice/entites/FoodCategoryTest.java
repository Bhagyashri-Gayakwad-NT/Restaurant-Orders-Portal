package com.nt.restaurant.microservice.entites;

import com.nt.restaurant.microservice.entities.FoodCategory;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class FoodCategoryTest {

  @Test
  public void testDefaultConstructor() {
    FoodCategory foodCategory = new FoodCategory();
    assertNull(foodCategory.getFoodCategoryId());
    assertNull(foodCategory.getRestaurantId());
    assertNull(foodCategory.getFoodCategoryName());
  }

  @Test
  public void testParameterizedConstructorWithId() {
    FoodCategory foodCategory = new FoodCategory(1, 1, "Appetizers");
    assertEquals(1, foodCategory.getFoodCategoryId());
    assertEquals(1, foodCategory.getRestaurantId());
    assertEquals("Appetizers", foodCategory.getFoodCategoryName());
  }

  @Test
  public void testParameterizedConstructorWithoutId() {
    FoodCategory foodCategory = new FoodCategory(1, "TestCategory");
    assertNull(foodCategory.getFoodCategoryId());
    assertEquals(1, foodCategory.getRestaurantId());
    assertEquals("TestCategory", foodCategory.getFoodCategoryName());
  }

  @Test
  public void testSettersAndGetters() {
    FoodCategory foodCategory = new FoodCategory();
    foodCategory.setFoodCategoryId(2);
    foodCategory.setRestaurantId(3);
    foodCategory.setFoodCategoryName("Test Course");

    assertEquals(2, foodCategory.getFoodCategoryId());
    assertEquals(3, foodCategory.getRestaurantId());
    assertEquals("Test Course", foodCategory.getFoodCategoryName());
  }

  @Test
  public void testEquals_SameObject() {
    FoodCategory foodCategory = new FoodCategory(1, 1, "TestCategory");
    assertEquals(foodCategory, foodCategory);
  }

  @Test
  public void testEquals_DifferentObjects_SameValues() {
    FoodCategory foodCategory1 = new FoodCategory(1, 1, "TestCategory");
    FoodCategory foodCategory2 = new FoodCategory(1, 1, "TestCategory");
    assertEquals(foodCategory1, foodCategory2);
  }

  @Test
  public void testEquals_DifferentObjects_DifferentValues() {
    FoodCategory foodCategory1 = new FoodCategory(1, 1, "TestCategory");
    FoodCategory foodCategory2 = new FoodCategory(2, 2, "TestCategory");
    assertNotEquals(foodCategory1, foodCategory2);
  }

  @Test
  public void testHashCode() {
    FoodCategory foodCategory = new FoodCategory(1, 1, "TestCategory");
    assertEquals(Objects.hash(1, 1, "TestCategory"), foodCategory.hashCode());
  }

  @Test
  public void testToString() {
    FoodCategory foodCategory = new FoodCategory(1, 1, "TestCategory");
    String expected = "FoodCategory{foodCategoryId=1, restaurantId=1, foodCategoryName='TestCategory'}";
    assertEquals(expected, foodCategory.toString());
  }
}
