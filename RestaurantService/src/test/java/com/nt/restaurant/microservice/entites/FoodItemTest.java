package com.nt.restaurant.microservice.entites;

import com.nt.restaurant.microservice.entities.FoodItem;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class FoodItemTest {

  private static final Integer FOOD_ITEM_ID = 1;
  private static final Integer CATEGORY_ID = 2;
  private static final Integer RESTAURANT_ID = 3;
  private static final String FOOD_ITEM_NAME = "Sample Food";
  private static final String DESCRIPTION = "Test Description";
  private static final Double PRICE = 9.99;
  private static final boolean IS_AVAILABLE = true;
  private static final byte[] IMAGE = new byte[]{1, 2, 3};

  @Test
  public void testDefaultConstructor() {
    FoodItem foodItem = new FoodItem();
    assertNull(foodItem.getFoodItemId());
    assertNull(foodItem.getCategoryId());
    assertNull(foodItem.getRestaurantId());
    assertNull(foodItem.getFoodItemName());
    assertNull(foodItem.getDescription());
    assertNull(foodItem.getPrice());
    assertFalse(foodItem.isAvailable());
    assertNull(foodItem.getFoodItemImage());
  }

  @Test
  public void testParameterizedConstructor() {
    FoodItem foodItem = new FoodItem(FOOD_ITEM_ID, CATEGORY_ID, RESTAURANT_ID, FOOD_ITEM_NAME,
      DESCRIPTION, PRICE, IS_AVAILABLE, IMAGE);
    assertEquals(FOOD_ITEM_ID, foodItem.getFoodItemId());
    assertEquals(CATEGORY_ID, foodItem.getCategoryId());
    assertEquals(RESTAURANT_ID, foodItem.getRestaurantId());
    assertEquals(FOOD_ITEM_NAME, foodItem.getFoodItemName());
    assertEquals(DESCRIPTION, foodItem.getDescription());
    assertEquals(PRICE, foodItem.getPrice());
    assertTrue(foodItem.isAvailable());
    assertArrayEquals(IMAGE, foodItem.getFoodItemImage());
  }

  @Test
  public void testEquals() {
    FoodItem foodItem1 = new FoodItem(FOOD_ITEM_ID, CATEGORY_ID, RESTAURANT_ID, FOOD_ITEM_NAME,
      DESCRIPTION, PRICE, IS_AVAILABLE, IMAGE);
    FoodItem foodItem2 = new FoodItem(FOOD_ITEM_ID, CATEGORY_ID, RESTAURANT_ID, FOOD_ITEM_NAME,
      DESCRIPTION, PRICE, IS_AVAILABLE, IMAGE);
    assertEquals(foodItem1, foodItem2);
  }

  @Test
  public void testNotEqualsDifferentId() {
    FoodItem foodItem1 = new FoodItem(FOOD_ITEM_ID, CATEGORY_ID, RESTAURANT_ID, FOOD_ITEM_NAME,
      DESCRIPTION, PRICE, IS_AVAILABLE, IMAGE);
    FoodItem foodItem2 = new FoodItem(2, CATEGORY_ID, RESTAURANT_ID, FOOD_ITEM_NAME,
      DESCRIPTION, PRICE, IS_AVAILABLE, IMAGE);
    assertNotEquals(foodItem1, foodItem2);
  }

  @Test
  public void testHashCode() {
    FoodItem foodItem = new FoodItem(FOOD_ITEM_ID, CATEGORY_ID, RESTAURANT_ID, FOOD_ITEM_NAME,
      DESCRIPTION, PRICE, IS_AVAILABLE, IMAGE);
    int expectedHashCode = Objects.hash(FOOD_ITEM_ID, CATEGORY_ID, RESTAURANT_ID, FOOD_ITEM_NAME,
      DESCRIPTION, PRICE, IS_AVAILABLE) + Arrays.hashCode(IMAGE);
    assertEquals(expectedHashCode, foodItem.hashCode());
  }

  @Test
  public void testToString() {
    FoodItem foodItem = new FoodItem(FOOD_ITEM_ID, CATEGORY_ID, RESTAURANT_ID, FOOD_ITEM_NAME,
      DESCRIPTION, PRICE, IS_AVAILABLE, IMAGE);
    String expectedString = "FoodItem{" +
      "foodItemId=" + FOOD_ITEM_ID +
      ", categoryId=" + CATEGORY_ID +
      ", restaurantId=" + RESTAURANT_ID +
      ", foodItemName='" + FOOD_ITEM_NAME + '\'' +
      ", description='" + DESCRIPTION + '\'' +
      ", Price=" + PRICE +
      ", isAvailable=" + IS_AVAILABLE +
      ", foodItemImage=" + Arrays.toString(IMAGE) +
      '}';
    assertEquals(expectedString, foodItem.toString());
  }

  @Test
  public void testSettersAndGetters() {
    FoodItem foodItem = new FoodItem();

    foodItem.setFoodItemId(FOOD_ITEM_ID);
    assertEquals(FOOD_ITEM_ID, foodItem.getFoodItemId());

    foodItem.setCategoryId(CATEGORY_ID);
    assertEquals(CATEGORY_ID, foodItem.getCategoryId());

    foodItem.setRestaurantId(RESTAURANT_ID);
    assertEquals(RESTAURANT_ID, foodItem.getRestaurantId());

    foodItem.setFoodItemName(FOOD_ITEM_NAME);
    assertEquals(FOOD_ITEM_NAME, foodItem.getFoodItemName());

    foodItem.setDescription(DESCRIPTION);
    assertEquals(DESCRIPTION, foodItem.getDescription());

    foodItem.setPrice(PRICE);
    assertEquals(PRICE, foodItem.getPrice());

    foodItem.setAvailable(IS_AVAILABLE);
    assertTrue(foodItem.isAvailable());

    foodItem.setFoodItemImage(IMAGE);
    assertArrayEquals(IMAGE, foodItem.getFoodItemImage());
  }
}
