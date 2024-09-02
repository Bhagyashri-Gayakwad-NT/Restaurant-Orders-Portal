package com.nt.restaurant.microservice.entites;

import com.nt.restaurant.microservice.entities.FoodCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodCategoryTest {

  @Test
  void testFoodCategoryConstructorAndGetters() {
    Integer foodCategoryId = 1;
    Integer restaurantId = 101;
    String foodCategoryName = "Appetizers";

    FoodCategory foodCategory = new FoodCategory(foodCategoryId, restaurantId, foodCategoryName);

    assertEquals(foodCategoryId, foodCategory.getFoodCategoryId());
    assertEquals(restaurantId, foodCategory.getRestaurantId());
    assertEquals(foodCategoryName, foodCategory.getFoodCategoryName());
  }

  @Test
  void testFoodCategorySetters() {
    FoodCategory foodCategory = new FoodCategory();

    Integer foodCategoryId = 2;
    Integer restaurantId = 102;
    String foodCategoryName = "Main Course";

    foodCategory.setFoodCategoryId(foodCategoryId);
    foodCategory.setRestaurantId(restaurantId);
    foodCategory.setFoodCategoryName(foodCategoryName);

    assertEquals(foodCategoryId, foodCategory.getFoodCategoryId());
    assertEquals(restaurantId, foodCategory.getRestaurantId());
    assertEquals(foodCategoryName, foodCategory.getFoodCategoryName());
  }

  @Test
  void testFoodCategoryEqualsAndHashCode() {
    FoodCategory foodCategory1 = new FoodCategory(1, 101, "Appetizers");
    FoodCategory foodCategory2 = new FoodCategory(1, 101, "Appetizers");
    FoodCategory foodCategory3 = new FoodCategory(2, 102, "Desserts");

    assertEquals(foodCategory1, foodCategory2);
    assertEquals(foodCategory1.hashCode(), foodCategory2.hashCode());

    assertNotEquals(foodCategory1, foodCategory3);
    assertNotEquals(foodCategory1.hashCode(), foodCategory3.hashCode());
  }

  @Test
  void testFoodCategoryToString() {
    FoodCategory foodCategory = new FoodCategory(1, 101, "Appetizers");

    String expectedString = "FoodCategory{foodCategoryId=1, restaurantId=101, foodCategoryName='Appetizers'}";

    assertEquals(expectedString, foodCategory.toString());
  }
}