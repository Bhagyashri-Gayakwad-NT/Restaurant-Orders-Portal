package com.nt.order.microservice.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderInDTOTest {

  private Validator validator;

  @BeforeEach
  void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void testOrderInDTO_Valid() {
    List<CartItemDTO> cartItems = new ArrayList<>();
    cartItems.add(new CartItemDTO());
    OrderInDTO orderInDTO = new OrderInDTO(1, 2, 3, cartItems);

    Set<ConstraintViolation<OrderInDTO>> violations = validator.validate(orderInDTO);

    assertTrue(violations.isEmpty(), "There should be no validation errors");
  }

  @Test
  void testOrderInDTO_NullUserId() {
    List<CartItemDTO> cartItems = new ArrayList<>();
    cartItems.add(new CartItemDTO());
    OrderInDTO orderInDTO = new OrderInDTO(null, 2, 3, cartItems);

    Set<ConstraintViolation<OrderInDTO>> violations = validator.validate(orderInDTO);

    assertFalse(violations.isEmpty(), "Validation error expected for null userId");
    assertEquals("User ID cannot be null", violations.iterator().next().getMessage());
  }

  @Test
  void testOrderInDTO_NullRestaurantId() {
    List<CartItemDTO> cartItems = new ArrayList<>();
    cartItems.add(new CartItemDTO());
    OrderInDTO orderInDTO = new OrderInDTO(1, null, 3, cartItems);

    Set<ConstraintViolation<OrderInDTO>> violations = validator.validate(orderInDTO);

    assertFalse(violations.isEmpty(), "Validation error expected for null restaurantId");
    assertEquals("Restaurant ID cannot be null", violations.iterator().next().getMessage());
  }

  @Test
  void testOrderInDTO_NullAddressId() {
    List<CartItemDTO> cartItems = new ArrayList<>();
    cartItems.add(new CartItemDTO());
    OrderInDTO orderInDTO = new OrderInDTO(1, 2, null, cartItems);

    Set<ConstraintViolation<OrderInDTO>> violations = validator.validate(orderInDTO);

    assertFalse(violations.isEmpty(), "Validation error expected for null addressId");
    assertEquals("Delivery Address ID cannot be null", violations.iterator().next().getMessage());
  }

  @Test
  void testOrderInDTO_EmptyCartItems() {
    OrderInDTO orderInDTO = new OrderInDTO(1, 2, 3, new ArrayList<>());

    Set<ConstraintViolation<OrderInDTO>> violations = validator.validate(orderInDTO);

    assertFalse(violations.isEmpty(), "Validation error expected for empty cartItems");
    assertEquals("Cart items cannot be empty", violations.iterator().next().getMessage());
  }

  @Test
  void testGettersAndSetters() {
    List<CartItemDTO> cartItems = new ArrayList<>();
    cartItems.add(new CartItemDTO());

    OrderInDTO orderInDTO = new OrderInDTO();
    orderInDTO.setUserId(1);
    orderInDTO.setRestaurantId(2);
    orderInDTO.setAddressId(3);
    orderInDTO.setCartItems(cartItems);

    assertEquals(1, orderInDTO.getUserId());
    assertEquals(2, orderInDTO.getRestaurantId());
    assertEquals(3, orderInDTO.getAddressId());
    assertEquals(cartItems, orderInDTO.getCartItems());
  }

  @Test
  void testEqualsAndHashCode() {
    List<CartItemDTO> cartItems = new ArrayList<>();
    cartItems.add(new CartItemDTO());
    OrderInDTO order1 = new OrderInDTO(1, 2, 3, cartItems);
    OrderInDTO order2 = new OrderInDTO(1, 2, 3, cartItems);

    assertEquals(order1, order2);
    assertEquals(order1.hashCode(), order2.hashCode());
  }

  @Test
  void testToString() {
    List<CartItemDTO> cartItems = new ArrayList<>();
    cartItems.add(new CartItemDTO());
    OrderInDTO orderInDTO = new OrderInDTO(1, 2, 3, cartItems);

    String result = orderInDTO.toString();

    assertTrue(result.contains("userId=1"));
    assertTrue(result.contains("restaurantId=2"));
    assertTrue(result.contains("addressId=3"));
    assertTrue(result.contains("cartItems"));
  }
}
