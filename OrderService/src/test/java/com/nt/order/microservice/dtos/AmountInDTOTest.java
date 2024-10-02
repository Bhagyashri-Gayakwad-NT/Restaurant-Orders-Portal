package com.nt.order.microservice.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AmountInDTOTest {

  private Validator validator;

  @BeforeEach
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void testBalanceNotNullConstraintViolation() {
    AmountInDTO amountInDTO = new AmountInDTO();
    amountInDTO.setBalance(null);
    Set<ConstraintViolation<AmountInDTO>> violations = validator.validate(amountInDTO);

    assertFalse(violations.isEmpty());
    assertEquals(1, violations.size());
    assertEquals("Balance cannot be null", violations.iterator().next().getMessage());
  }

  @Test
  public void testValidBalance() {
    AmountInDTO amountInDTO = new AmountInDTO();
    amountInDTO.setBalance(100.0);

    Set<ConstraintViolation<AmountInDTO>> violations = validator.validate(amountInDTO);

    assertTrue(violations.isEmpty());
  }

  @Test
  public void testSetAndGetBalance() {
    AmountInDTO amountInDTO = new AmountInDTO();
    Double testBalance = 150.50;

    amountInDTO.setBalance(testBalance);
    Double balance = amountInDTO.getBalance();

    assertEquals(testBalance, balance);
  }
}
