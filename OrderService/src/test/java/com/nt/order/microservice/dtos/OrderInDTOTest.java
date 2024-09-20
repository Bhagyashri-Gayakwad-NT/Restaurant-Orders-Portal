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

import static org.junit.jupiter.api.Assertions.*;

public class OrderInDTOTest {

  private Validator validator;

  @BeforeEach
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void testValidOrderInDTO() {
    // Given a valid OrderInDTO
    List<CartItemDTO> cartItems = new ArrayList<>();
    cartItems.add(new CartItemDTO(1, 1, 10.0));
    OrderInDTO orderInDTO = new OrderInDTO(1, 1, 1, cartItems);

    // When validating the OrderInDTO
    Set<ConstraintViolation<OrderInDTO>> violations = validator.validate(orderInDTO);

    // Then no violations should be found
    assertTrue(violations.isEmpty(), "Expected no validation violations");
  }

  @Test
  public void testNullUserId() {
    // Given a null userId
    List<CartItemDTO> cartItems = new ArrayList<>();
    cartItems.add(new CartItemDTO(1, 1, 10.0));
    OrderInDTO orderInDTO = new OrderInDTO(null, 1, 1, cartItems);

    // When validating the OrderInDTO
    Set<ConstraintViolation<OrderInDTO>> violations = validator.validate(orderInDTO);

    // Then there should be a violation for userId being null
    assertFalse(violations.isEmpty(), "Expected validation violation for null userId");
    violations.forEach(violation -> assertEquals("User ID cannot be null", violation.getMessage()));
  }

  @Test
  public void testNullRestaurantId() {
    // Given a null restaurantId
    List<CartItemDTO> cartItems = new ArrayList<>();
    cartItems.add(new CartItemDTO(1, 1, 10.0));
    OrderInDTO orderInDTO = new OrderInDTO(1, null, 1, cartItems);

    // When validating the OrderInDTO
    Set<ConstraintViolation<OrderInDTO>> violations = validator.validate(orderInDTO);

    // Then there should be a violation for restaurantId being null
    assertFalse(violations.isEmpty(), "Expected validation violation for null restaurantId");
    violations.forEach(violation -> assertEquals("Restaurant ID cannot be null", violation.getMessage()));
  }

  @Test
  public void testNullAddressId() {
    // Given a null addressId
    List<CartItemDTO> cartItems = new ArrayList<>();
    cartItems.add(new CartItemDTO(1, 1, 10.0));
    OrderInDTO orderInDTO = new OrderInDTO(1, 1, null, cartItems);

    // When validating the OrderInDTO
    Set<ConstraintViolation<OrderInDTO>> violations = validator.validate(orderInDTO);

    // Then there should be a violation for addressId being null
    assertFalse(violations.isEmpty(), "Expected validation violation for null addressId");
    violations.forEach(violation -> assertEquals("Delivery Address ID cannot be null", violation.getMessage()));
  }

  @Test
  public void testEmptyCartItems() {
    // Given an empty cartItems list
    List<CartItemDTO> cartItems = new ArrayList<>();
    OrderInDTO orderInDTO = new OrderInDTO(1, 1, 1, cartItems);

    // When validating the OrderInDTO
    Set<ConstraintViolation<OrderInDTO>> violations = validator.validate(orderInDTO);

    // Then there should be a violation for cartItems being empty
    assertFalse(violations.isEmpty(), "Expected validation violation for empty cartItems");
    violations.forEach(violation -> assertEquals("Cart items cannot be empty", violation.getMessage()));
  }

  @Test
  public void testInvalidCartItem() {
    // Given a CartItemDTO with invalid fields
    List<CartItemDTO> cartItems = new ArrayList<>();
    cartItems.add(new CartItemDTO(null, 0, 0.0));
    OrderInDTO orderInDTO = new OrderInDTO(1, 1, 1, cartItems);

    // When validating the OrderInDTO
    Set<ConstraintViolation<OrderInDTO>> violations = validator.validate(orderInDTO);

    // Then there should be violations for the invalid CartItemDTO
    assertFalse(violations.isEmpty(), "Expected validation violations for invalid CartItemDTO");
  }
}
