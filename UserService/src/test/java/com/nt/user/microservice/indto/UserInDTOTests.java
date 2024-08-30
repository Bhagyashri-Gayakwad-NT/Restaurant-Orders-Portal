package com.nt.user.microservice.indto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserInDTOTests {

  private Validator validator;

  @BeforeEach
  public void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  public void testValidUserInDTO() {
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setFirstName("John");
    userInDTO.setLastName("Doe");
    userInDTO.setEmail("john.doe@nucleusteq.com");
    userInDTO.setPassword("Password1");
    userInDTO.setPhoneNo("9876543210");
    userInDTO.setRole("USER");

    Set<ConstraintViolation<UserInDTO>> violations = validator.validate(userInDTO);
    assertEquals(0, violations.size(), "UserInDTO should be valid");
  }

  @Test
  public void testInvalidFirstName() {
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setFirstName("jo"); // Invalid first name
    userInDTO.setLastName("Doe");
    userInDTO.setEmail("john.doe@nucleusteq.com");
    userInDTO.setPassword("Password1");
    userInDTO.setPhoneNo("9876543210");
    userInDTO.setRole("USER");

    Set<ConstraintViolation<UserInDTO>> violations = validator.validate(userInDTO);
    assertEquals(1, violations.size());
    assertEquals("First name must start with a capital letter," +
        " have no spaces, digits, or special characters, and be at least 3 characters long",
      violations.iterator().next().getMessage());
  }

  @Test
  public void testInvalidLastName() {
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setFirstName("John");
    userInDTO.setLastName("do"); // Invalid last name
    userInDTO.setEmail("john.doe@nucleusteq.com");
    userInDTO.setPassword("Password1");
    userInDTO.setPhoneNo("9876543210");
    userInDTO.setRole("USER");

    Set<ConstraintViolation<UserInDTO>> violations = validator.validate(userInDTO);
    assertEquals(1, violations.size());
    assertEquals("Last name must start with a capital letter," +
        " have no spaces, digits, or special characters, and be at least 3 characters long",
      violations.iterator().next().getMessage());
  }

  @Test
  public void testInvalidEmail() {
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setFirstName("John");
    userInDTO.setLastName("Doe");
    userInDTO.setEmail("john.doe@gmail.com"); // Invalid email
    userInDTO.setPassword("Password1");
    userInDTO.setPhoneNo("9876543210");
    userInDTO.setRole("USER");

    Set<ConstraintViolation<UserInDTO>> violations = validator.validate(userInDTO);
    assertEquals(1, violations.size());
    assertEquals("Email is required and Email must end with @nucleusteq.com",
      violations.iterator().next().getMessage());
  }

  @Test
  public void testInvalidPassword() {
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setFirstName("John");
    userInDTO.setLastName("Doe");
    userInDTO.setEmail("john.doe@nucleusteq.com");
    userInDTO.setPassword("pass"); // Invalid password (no digits)
    userInDTO.setPhoneNo("9876543210");
    userInDTO.setRole("USER");

    Set<ConstraintViolation<UserInDTO>> violations = validator.validate(userInDTO);
    assertEquals(0, violations.size());
  }

  @Test
  public void testInvalidPhoneNo() {
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setFirstName("John");
    userInDTO.setLastName("Doe");
    userInDTO.setEmail("john.doe@nucleusteq.com");
    userInDTO.setPassword("Password1");
    userInDTO.setPhoneNo("1234567890"); // Invalid phone number
    userInDTO.setRole("USER");

    Set<ConstraintViolation<UserInDTO>> violations = validator.validate(userInDTO);
    assertEquals(1, violations.size());
    assertEquals("Phone number must start with 9, 8, 7, or 6 and contain 10 digits",
      violations.iterator().next().getMessage());
  }

  @Test
  public void testInvalidRole() {
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setFirstName("John");
    userInDTO.setLastName("Doe");
    userInDTO.setEmail("john.doe@nucleusteq.com");
    userInDTO.setPassword("Password1");
    userInDTO.setPhoneNo("9876543210");
    userInDTO.setRole("ADMIN"); // Invalid role

    Set<ConstraintViolation<UserInDTO>> violations = validator.validate(userInDTO);
    assertEquals(1, violations.size());
    assertEquals("Role must be either 'USER' or 'RESTAURANT_OWNER'",
      violations.iterator().next().getMessage());
  }

}
