package com.nt.order.microservice.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CartTest {

  @Test
  public void testGetterAndSetter() {
    Cart cart = new Cart();

    assertNull(cart.getCartId());
    Integer cartId = 1;
    cart.setCartId(cartId);
    assertEquals(cartId, cart.getCartId());

    assertNull(cart.getUserId());
    Integer userId = 101;
    cart.setUserId(userId);
    assertEquals(userId, cart.getUserId());

    assertNull(cart.getRestaurantId());
    Integer restaurantId = 201;
    cart.setRestaurantId(restaurantId);
    assertEquals(restaurantId, cart.getRestaurantId());

    assertNull(cart.getFoodItemId());
    Integer foodItemId = 301;
    cart.setFoodItemId(foodItemId);
    assertEquals(foodItemId, cart.getFoodItemId());

    assertNull(cart.getQuantity());
    Integer quantity = 2;
    cart.setQuantity(quantity);
    assertEquals(quantity, cart.getQuantity());

    assertNull(cart.getPrice());
    Double price = 100.0;
    cart.setPrice(price);
    assertEquals(price, cart.getPrice());
  }

  @Test
  public void testToString() {
    Cart cart = new Cart();

    Integer cartId = 1;
    cart.setCartId(cartId);

    Integer userId = 101;
    cart.setUserId(userId);

    Integer restaurantId = 201;
    cart.setRestaurantId(restaurantId);

    Integer foodItemId = 301;
    cart.setFoodItemId(foodItemId);

    Integer quantity = 2;
    cart.setQuantity(quantity);

    Double price = 100.0;
    cart.setPrice(price);

    assertEquals("Cart{cartId=1, userId=101, restaurantId=201, foodItemId=301, quantity=2, price=100.0}", cart.toString());
  }

  @Test
  public void testEqualsAndHashCode() {
    Integer cartId = 1;
    Integer userId = 101;
    Integer restaurantId = 201;
    Integer foodItemId = 301;
    Integer quantity = 2;
    Double price = 100.0;

    Cart cart1 = buildCart(cartId, userId, restaurantId, foodItemId, quantity, price);

    assertEquals(cart1, cart1);
    assertEquals(cart1.hashCode(), cart1.hashCode());

    assertNotEquals(cart1, new Object());

    Cart cart2 = buildCart(cartId, userId, restaurantId, foodItemId, quantity, price);
    assertEquals(cart1, cart2);
    assertEquals(cart1.hashCode(), cart2.hashCode());

    cart2 = buildCart(cartId + 1, userId, restaurantId, foodItemId, quantity, price);
    assertNotEquals(cart1, cart2);
    assertNotEquals(cart1.hashCode(), cart2.hashCode());

    cart2 = buildCart(cartId, userId + 1, restaurantId, foodItemId, quantity, price);
    assertNotEquals(cart1, cart2);
    assertNotEquals(cart1.hashCode(), cart2.hashCode());

    cart2 = buildCart(cartId, userId, restaurantId + 1, foodItemId, quantity, price);
    assertNotEquals(cart1, cart2);
    assertNotEquals(cart1.hashCode(), cart2.hashCode());

    cart2 = buildCart(cartId, userId, restaurantId, foodItemId + 1, quantity, price);
    assertNotEquals(cart1, cart2);
    assertNotEquals(cart1.hashCode(), cart2.hashCode());

    cart2 = buildCart(cartId, userId, restaurantId, foodItemId, quantity + 1, price);
    assertNotEquals(cart1, cart2);
    assertNotEquals(cart1.hashCode(), cart2.hashCode());

    cart2 = buildCart(cartId, userId, restaurantId, foodItemId, quantity, price + 1);
    assertNotEquals(cart1, cart2);
    assertNotEquals(cart1.hashCode(), cart2.hashCode());

    cart1 = new Cart();
    cart2 = new Cart();
    assertEquals(cart1, cart2);
    assertEquals(cart1.hashCode(), cart2.hashCode());
  }

  private Cart buildCart(Integer cartId, Integer userId, Integer restaurantId, Integer foodItemId, Integer quantity, Double price) {
    Cart cart = new Cart();

    cart.setCartId(cartId);
    cart.setUserId(userId);
    cart.setRestaurantId(restaurantId);
    cart.setFoodItemId(foodItemId);
    cart.setQuantity(quantity);
    cart.setPrice(price);

    return cart;
  }
}
