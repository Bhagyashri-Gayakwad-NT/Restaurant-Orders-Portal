package com.nt.order.microservice.dtos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CartOutDTOTest {

  @Test
  public void testGetterAndSetter() {
    CartOutDTO cartOutDTO = new CartOutDTO();

    assertNull(cartOutDTO.getCartId());
    Integer cartId = 1;
    cartOutDTO.setCartId(cartId);
    assertEquals(cartId, cartOutDTO.getCartId());

    assertNull(cartOutDTO.getUserId());
    Integer userId = 2;
    cartOutDTO.setUserId(userId);
    assertEquals(userId, cartOutDTO.getUserId());

    assertNull(cartOutDTO.getRestaurantId());
    Integer restaurantId = 3;
    cartOutDTO.setRestaurantId(restaurantId);
    assertEquals(restaurantId, cartOutDTO.getRestaurantId());

    assertNull(cartOutDTO.getFoodItemId());
    Integer foodItemId = 4;
    cartOutDTO.setFoodItemId(foodItemId);
    assertEquals(foodItemId, cartOutDTO.getFoodItemId());

    assertNull(cartOutDTO.getQuantity());
    Integer quantity = 5;
    cartOutDTO.setQuantity(quantity);
    assertEquals(quantity, cartOutDTO.getQuantity());

    assertNull(cartOutDTO.getPrice());
    Double price = 10.0;
    cartOutDTO.setPrice(price);
    assertEquals(price, cartOutDTO.getPrice());
  }

  @Test
  public void testToString() {
    CartOutDTO cartOutDTO = new CartOutDTO();

    cartOutDTO.setCartId(1);
    cartOutDTO.setUserId(2);
    cartOutDTO.setRestaurantId(3);
    cartOutDTO.setFoodItemId(4);
    cartOutDTO.setQuantity(5);
    cartOutDTO.setPrice(10.0);

    assertEquals("CartOutDTO{cartId=1, userId=2, restaurantId=3, foodItemId=4, quantity=5, price=10.0}",
      cartOutDTO.toString());
  }

  @Test
  public void testEqualsAndHashcode() {
    CartOutDTO cartOutDTO1 = buildCartOutDTO(1, 2, 3, 4, 5, 10.0);

    assertEquals(cartOutDTO1, cartOutDTO1);
    assertEquals(cartOutDTO1.hashCode(), cartOutDTO1.hashCode());

    assertNotEquals(cartOutDTO1, new Object());

    CartOutDTO cartOutDTO2 = buildCartOutDTO(1, 2, 3, 4, 5, 10.0);
    assertEquals(cartOutDTO1, cartOutDTO2);
    assertEquals(cartOutDTO1.hashCode(), cartOutDTO2.hashCode());

    cartOutDTO2 = buildCartOutDTO(1, 2, 3, 4, 5, 20.0);
    assertNotEquals(cartOutDTO1, cartOutDTO2);
    assertNotEquals(cartOutDTO1.hashCode(), cartOutDTO2.hashCode());

    cartOutDTO2 = buildCartOutDTO(1, 2, 3, 4, 6, 10.0);
    assertNotEquals(cartOutDTO1, cartOutDTO2);
    assertNotEquals(cartOutDTO1.hashCode(), cartOutDTO2.hashCode());

    cartOutDTO2 = buildCartOutDTO(1, 2, 3, 5, 5, 10.0);
    assertNotEquals(cartOutDTO1, cartOutDTO2);
    assertNotEquals(cartOutDTO1.hashCode(), cartOutDTO2.hashCode());

    cartOutDTO2 = buildCartOutDTO(1, 2, 4, 4, 5, 10.0);
    assertNotEquals(cartOutDTO1, cartOutDTO2);
    assertNotEquals(cartOutDTO1.hashCode(), cartOutDTO2.hashCode());

    cartOutDTO1 = new CartOutDTO();
    cartOutDTO2 = new CartOutDTO();
    assertEquals(cartOutDTO1, cartOutDTO2);
    assertEquals(cartOutDTO1.hashCode(), cartOutDTO2.hashCode());
  }

  private CartOutDTO buildCartOutDTO(Integer cartId, Integer userId, Integer restaurantId,
                                     Integer foodItemId, Integer quantity, Double price) {
    CartOutDTO cartOutDTO = new CartOutDTO();

    cartOutDTO.setCartId(cartId);
    cartOutDTO.setUserId(userId);
    cartOutDTO.setRestaurantId(restaurantId);
    cartOutDTO.setFoodItemId(foodItemId);
    cartOutDTO.setQuantity(quantity);
    cartOutDTO.setPrice(price);

    return cartOutDTO;
  }
}
