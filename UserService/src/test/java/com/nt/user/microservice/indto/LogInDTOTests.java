package com.nt.user.microservice.indto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogInDTOTests {

  private Validator validator;

  @BeforeEach
  void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void testValidLogInDTO() {
    LogInDTO logInDTO = new LogInDTO();
    logInDTO.setEmail("user@nucleusteq.com");
    logInDTO.setPassword("password123");

    Set<ConstraintViolation<LogInDTO>> violations = validator.validate(logInDTO);

    assertTrue(violations.isEmpty(), "Expected no validation errors");
  }

  @Test
  void testEmailIsBlank() {
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setFirstName("John");
    userInDTO.setLastName("Doe");
    userInDTO.setEmail("");
    userInDTO.setPassword("password123");
    userInDTO.setPhoneNo("9876543210");
    userInDTO.setRole("USER");

    Set<ConstraintViolation<UserInDTO>> violations = validator.validate(userInDTO);
    assertEquals(1, violations.size(), "Expected one validation error");
    assertEquals("Email is required and Email must end with @nucleusteq.com", violations.iterator().next().getMessage());
  }

  @Test
  void testEmailDoesNotEndWithNucleusTeqDomain() {
    LogInDTO logInDTO = new LogInDTO();
    logInDTO.setEmail("user@gmail.com");
    logInDTO.setPassword("password123");

    Set<ConstraintViolation<LogInDTO>> violations = validator.validate(logInDTO);

    assertEquals(1, violations.size(), "Expected one validation error");
    assertEquals("Email must end with @nucleusteq.com", violations.iterator().next().getMessage());
  }

  @Test
  void testPasswordIsBlank() {
    LogInDTO logInDTO = new LogInDTO();
    logInDTO.setEmail("user@nucleusteq.com");
    logInDTO.setPassword("");

    Set<ConstraintViolation<LogInDTO>> violations = validator.validate(logInDTO);

    assertEquals(1, violations.size(), "Expected one validation error");
    assertEquals("Password is required", violations.iterator().next().getMessage());
  }
}