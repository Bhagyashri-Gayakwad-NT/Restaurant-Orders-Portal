package com.nt.user.microservice.indto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AddressInDTOTest {

  private AddressInDTO addressInDTO;
  private Validator validator;

  @BeforeEach
  void setUp() {
    // Set up the AddressInDTO object and validator before each test
    addressInDTO = new AddressInDTO();

    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void testSetAndGetStreet() {
    String street = "123 Elm Street";
    addressInDTO.setStreet(street);
    assertEquals(street, addressInDTO.getStreet(), "The street should be correctly set and retrieved");
  }

  @Test
  void testSetAndGetCity() {
    String city = "Metropolis";
    addressInDTO.setCity(city);
    assertEquals(city, addressInDTO.getCity(), "The city should be correctly set and retrieved");
  }

  @Test
  void testSetAndGetState() {
    String state = "NewYork";
    addressInDTO.setState(state);
    assertEquals(state, addressInDTO.getState(), "The state should be correctly set and retrieved");
  }

  @Test
  void testSetAndGetCountry() {
    String country = "USA";
    addressInDTO.setCountry(country);
    assertEquals(country, addressInDTO.getCountry(), "The country should be correctly set and retrieved");
  }

  @Test
  void testSetAndGetPinCode() {
    String pinCode = "123456";
    addressInDTO.setPinCode(pinCode);
    assertEquals(pinCode, addressInDTO.getPinCode(), "The pin code should be correctly set and retrieved");
  }

  @Test
  void testSetAndGetUserId() {
    Integer userId = 101;
    addressInDTO.setUserId(userId);
    assertEquals(userId, addressInDTO.getUserId(), "The user ID should be correctly set and retrieved");
  }

  @Test
  void testValidAddressInDTO() {
    addressInDTO.setStreet("123 Elm Street");
    addressInDTO.setCity("Metropolis");
    addressInDTO.setState("NewYork");
    addressInDTO.setCountry("USA");
    addressInDTO.setPinCode("123456");
    addressInDTO.setUserId(101);

    Set<javax.validation.ConstraintViolation<AddressInDTO>> violations = validator.validate(addressInDTO);
    assertTrue(violations.isEmpty(), "There should be no validation violations for a valid AddressInDTO");
  }

//  @Test
//  void testInvalidAddressInDTO() {
//    addressInDTO.setStreet("123");
//    addressInDTO.setCity("NY");
//    addressInDTO.setState("N@wY0rk");
//    addressInDTO.setCountry("United States of America"); // exceeds max length
//    addressInDTO.setPinCode("1234"); // not 6 digits
//    addressInDTO.setUserId(null); // null user ID
//
//    Set<javax.validation.ConstraintViolation<AddressInDTO>> violations = validator.validate(addressInDTO);
//
//    assertFalse(violations.isEmpty(), "There should be validation violations for an invalid AddressInDTO");
//
//    // Check specific violations
//    assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Street must be between 4 and 100 characters")));
//    assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("City must be between 3 and 50 characters")));
//    assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("State must contain only alphabetic characters")));
//    assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Country cannot be longer than 50 characters")));
//    assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("Pin code must be exactly 6 digits and cannot contain spaces or characters")));
//    assertTrue(violations.stream().anyMatch(v -> v.getMessage().equals("User ID is required")));
//  }
}
