package com.nt.restaurant.microservice.entites;
import com.nt.restaurant.microservice.entities.FoodCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodCategoryTest {

  @Test
  void testConstructorAndGetters() {
    FoodCategory foodCategory = new FoodCategory(1, 101, "Appetizers");

    assertEquals(1, foodCategory.getFoodCategoryId());
    assertEquals(101, foodCategory.getRestaurantId());
    assertEquals("Appetizers", foodCategory.getFoodCategoryName());
  }

  @Test
  void testSetters() {
    FoodCategory foodCategory = new FoodCategory();

    foodCategory.setFoodCategoryId(2);
    foodCategory.setRestaurantId(102);
    foodCategory.setFoodCategoryName("Main Course");

    assertEquals(2, foodCategory.getFoodCategoryId());
    assertEquals(102, foodCategory.getRestaurantId());
    assertEquals("Main Course", foodCategory.getFoodCategoryName());
  }

  @Test
  void testEquals() {
    FoodCategory foodCategory1 = new FoodCategory(1, 101, "Appetizers");
    FoodCategory foodCategory2 = new FoodCategory(1, 101, "Appetizers");

    assertEquals(foodCategory1, foodCategory2);
    assertEquals(foodCategory1.hashCode(), foodCategory2.hashCode());
  }

  @Test
  void testNotEquals() {
    FoodCategory foodCategory1 = new FoodCategory(1, 101, "Appetizers");
    FoodCategory foodCategory2 = new FoodCategory(2, 102, "Desserts");

    assertNotEquals(foodCategory1, foodCategory2);
    assertNotEquals(foodCategory1.hashCode(), foodCategory2.hashCode());
  }

  @Test
  void testToString() {
    FoodCategory foodCategory = new FoodCategory(1, 101, "Appetizers");

    String expected = "FoodCategory{foodCategoryId=1, restaurantId=101, foodCategoryName='Appetizers'}";
    assertEquals(expected, foodCategory.toString());
  }}