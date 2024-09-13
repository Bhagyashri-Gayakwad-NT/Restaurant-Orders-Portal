package com.nt.restaurant.microservice.entites;

import com.nt.restaurant.microservice.entities.FoodItem;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FoodItemTest {

  @Test
  void testConstructorAndGetters() {
    byte[] image = new byte[] {1, 2, 3, 4, 5};
    FoodItem foodItem = new FoodItem(1, 101, 1001, "Burger", "Delicious chicken burger", 5.99, true, image);

    assertEquals(1, foodItem.getFoodItemId());
    assertEquals(101, foodItem.getCategoryId());
    assertEquals(1001, foodItem.getRestaurantId());
    assertEquals("Burger", foodItem.getFoodItemName());
    assertEquals("Delicious chicken burger", foodItem.getDescription());
    assertEquals(5.99, foodItem.getPrice());
    assertTrue(foodItem.isAvailable());
    assertArrayEquals(image, foodItem.getFoodItemImage());
  }

  @Test
  void testSetters() {
    byte[] image = new byte[] {1, 2, 3, 4, 5};
    FoodItem foodItem = new FoodItem();

    foodItem.setFoodItemId(2);
    foodItem.setCategoryId(102);
    foodItem.setRestaurantId(1002);
    foodItem.setFoodItemName("Pizza");
    foodItem.setDescription("Tasty pepperoni pizza");
    foodItem.setPrice(8.99);
    foodItem.setAvailable(false);
    foodItem.setFoodItemImage(image);

    assertEquals(2, foodItem.getFoodItemId());
    assertEquals(102, foodItem.getCategoryId());
    assertEquals(1002, foodItem.getRestaurantId());
    assertEquals("Pizza", foodItem.getFoodItemName());
    assertEquals("Tasty pepperoni pizza", foodItem.getDescription());
    assertEquals(8.99, foodItem.getPrice());
    assertFalse(foodItem.isAvailable());
    assertArrayEquals(image, foodItem.getFoodItemImage());
  }

  @Test
  void testEquals() {
    byte[] image1 = new byte[] {1, 2, 3, 4, 5};
    byte[] image2 = new byte[] {1, 2, 3, 4, 5};

    FoodItem foodItem1 = new FoodItem(1, 101, 1001, "Burger", "Delicious chicken burger", 5.99, true, image1);
    FoodItem foodItem2 = new FoodItem(1, 101, 1001, "Burger", "Delicious chicken burger", 5.99, true, image2);

    assertEquals(foodItem1, foodItem2);
    assertEquals(foodItem1.hashCode(), foodItem2.hashCode());
  }

  @Test
  void testNotEquals() {
    byte[] image1 = new byte[] {1, 2, 3, 4, 5};
    byte[] image2 = new byte[] {6, 7, 8, 9, 10};

    FoodItem foodItem1 = new FoodItem(1, 101, 1001, "Burger", "Delicious chicken burger", 5.99, true, image1);
    FoodItem foodItem2 = new FoodItem(2, 102, 1002, "Pizza", "Tasty pepperoni pizza", 8.99, false, image2);

    assertNotEquals(foodItem1, foodItem2);
    assertNotEquals(foodItem1.hashCode(), foodItem2.hashCode());
  }

  @Test
  void testToString() {
    byte[] image = new byte[] {1, 2, 3, 4, 5};
    FoodItem foodItem = new FoodItem(1, 101, 1001, "Burger", "Delicious chicken burger", 5.99, true, image);

    String expected = "FoodItem{foodItemId=1, categoryId=101, restaurantId=1001, foodItemName='Burger', " +
      "description='Delicious chicken burger', Price=5.99, isAvailable=true, foodItemImage=" + Arrays.toString(image) + "}";
    assertEquals(expected, foodItem.toString());
  }
}
