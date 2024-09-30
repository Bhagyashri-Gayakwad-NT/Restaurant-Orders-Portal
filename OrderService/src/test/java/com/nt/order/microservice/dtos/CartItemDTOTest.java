package com.nt.order.microservice.dtos;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CartItemDTOTest {

  private Validator validator;

  public CartItemDTOTest() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void testDefaultConstructor() {
    CartItemDTO cartItemDTO = new CartItemDTO();
    assertNull(cartItemDTO.getFoodItemId());
    assertNull(cartItemDTO.getQuantity());
    assertNull(cartItemDTO.getPrice());
  }

  @Test
  public void testParameterizedConstructor() {
    Integer foodItemId = 1;
    Integer quantity = 2;
    Double price = 10.0;
    CartItemDTO cartItemDTO = new CartItemDTO(foodItemId, quantity, price);
    assertEquals(foodItemId, cartItemDTO.getFoodItemId());
    assertEquals(quantity, cartItemDTO.getQuantity());
    assertEquals(price, cartItemDTO.getPrice());
  }

  @Test
  public void testSettersAndGetters() {
    CartItemDTO cartItemDTO = new CartItemDTO();

    Integer foodItemId = 1;
    cartItemDTO.setFoodItemId(foodItemId);
    assertEquals(foodItemId, cartItemDTO.getFoodItemId());

    Integer quantity = 2;
    cartItemDTO.setQuantity(quantity);
    assertEquals(quantity, cartItemDTO.getQuantity());

    Double price = 10.0;
    cartItemDTO.setPrice(price);
    assertEquals(price, cartItemDTO.getPrice());
  }

  @Test
  public void testValidation() {
    CartItemDTO cartItemDTO = new CartItemDTO();

    Set<ConstraintViolation<CartItemDTO>> violations = validator.validate(cartItemDTO);
    assertEquals(3, violations.size());

    cartItemDTO.setFoodItemId(1);
    violations = validator.validate(cartItemDTO);
    assertEquals(2, violations.size());

    cartItemDTO.setQuantity(0);
    violations = validator.validate(cartItemDTO);
    assertEquals(2, violations.size());

    cartItemDTO.setQuantity(1);
    cartItemDTO.setPrice(0.0);
    violations = validator.validate(cartItemDTO);
    assertEquals(1, violations.size());

    cartItemDTO.setPrice(10.0);
    violations = validator.validate(cartItemDTO);
    assertEquals(0, violations.size());
  }

  @Test
  public void testEqualsMethod() {
    CartItemDTO cartItemDTO1 = new CartItemDTO(1, 2, 10.0);
    CartItemDTO cartItemDTO2 = new CartItemDTO(1, 2, 10.0);
    CartItemDTO cartItemDTO3 = new CartItemDTO(2, 3, 20.0);

    assertEquals(cartItemDTO1, cartItemDTO2);
    assertNotEquals(cartItemDTO1, cartItemDTO3);
  }

  @Test
  public void testHashCodeMethod() {
    CartItemDTO cartItemDTO1 = new CartItemDTO(1, 2, 10.0);
    CartItemDTO cartItemDTO2 = new CartItemDTO(1, 2, 10.0);
    CartItemDTO cartItemDTO3 = new CartItemDTO(2, 3, 20.0);

    assertEquals(cartItemDTO1.hashCode(), cartItemDTO2.hashCode());
    assertNotEquals(cartItemDTO1.hashCode(), cartItemDTO3.hashCode());
  }

  @Test
  public void testToStringMethod() {
    CartItemDTO cartItemDTO = new CartItemDTO(1, 2, 10.0);
    String expected = "CartItemDTO{foodItemId=1, quantity=2, price=10.0}";

    assertEquals(expected, cartItemDTO.toString());
  }

  @Test
  public void testInvalidPriceFormat() {
    CartItemDTO cartItemDTO = new CartItemDTO(1, 2, 10.123);

    Set<ConstraintViolation<CartItemDTO>> violations = validator.validate(cartItemDTO);
    assertEquals(1, violations.size());
  }

  @Test
  public void testLargeQuantity() {
    CartItemDTO cartItemDTO = new CartItemDTO(1, 1000000000, 10.0);

    Set<ConstraintViolation<CartItemDTO>> violations = validator.validate(cartItemDTO);
    assertEquals(0, violations.size());
  }
}
