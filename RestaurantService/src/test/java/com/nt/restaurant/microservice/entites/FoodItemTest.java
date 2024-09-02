package com.nt.restaurant.microservice.entites;

import com.nt.restaurant.microservice.entities.FoodItem;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class FoodItemTest {

  @Test
  void testFoodItemConstructorAndGetters() {
    Integer foodItemId = 1;
    Integer categoryId = 101;
    Integer restaurantId = 1001;
    String foodItemName = "Burger";
    String description = "Delicious beef burger";
    Double price = 5.99;
    boolean isAvailable = true;
    byte[] foodItemImage = {1, 2, 3};

    FoodItem foodItem =
      new FoodItem(foodItemId, categoryId, restaurantId, foodItemName, description, price, isAvailable, foodItemImage);

    assertEquals(foodItemId, foodItem.getFoodItemId());
    assertEquals(categoryId, foodItem.getCategoryId());
    assertEquals(restaurantId, foodItem.getRestaurantId());
    assertEquals(foodItemName, foodItem.getFoodItemName());
    assertEquals(description, foodItem.getDescription());
    assertEquals(price, foodItem.getPrice());
    assertTrue(foodItem.isAvailable());
    assertArrayEquals(foodItemImage, foodItem.getFoodItemImage());
  }

  @Test
  void testFoodItemSetters() {
    FoodItem foodItem = new FoodItem();

    Integer foodItemId = 2;
    Integer categoryId = 102;
    Integer restaurantId = 1002;
    String foodItemName = "Pizza";
    String description = "Cheesy pizza with extra toppings";
    Double price = 8.99;
    boolean isAvailable = false;
    byte[] foodItemImage = {4, 5, 6};

    foodItem.setFoodItemId(foodItemId);
    foodItem.setCategoryId(categoryId);
    foodItem.setRestaurantId(restaurantId);
    foodItem.setFoodItemName(foodItemName);
    foodItem.setDescription(description);
    foodItem.setPrice(price);
    foodItem.setAvailable(isAvailable);
    foodItem.setFoodItemImage(foodItemImage);

    assertEquals(foodItemId, foodItem.getFoodItemId());
    assertEquals(categoryId, foodItem.getCategoryId());
    assertEquals(restaurantId, foodItem.getRestaurantId());
    assertEquals(foodItemName, foodItem.getFoodItemName());
    assertEquals(description, foodItem.getDescription());
    assertEquals(price, foodItem.getPrice());
    assertFalse(foodItem.isAvailable());
    assertArrayEquals(foodItemImage, foodItem.getFoodItemImage());
  }
}