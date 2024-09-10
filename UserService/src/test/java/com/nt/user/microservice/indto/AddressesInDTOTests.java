package com.nt.user.microservice.indto;

import com.nt.user.microservice.dto.AddressInDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AddressesInDTOTest {

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
  @Test
  void testEqualsAndHashCode() {
    AddressInDTO address1 = new AddressInDTO();
    address1.setStreet("123 Main Street");
    address1.setCity("Springfield");
    address1.setState("California");
    address1.setCountry("USA");
    address1.setPinCode("123456");
    address1.setUserId(1);

    AddressInDTO address2 = new AddressInDTO();
    address2.setStreet("123 Main Street");
    address2.setCity("Springfield");
    address2.setState("California");
    address2.setCountry("USA");
    address2.setPinCode("123456");
    address2.setUserId(1);

    assertEquals(address1, address2, "Expected both addresses to be equal");
    assertEquals(address1.hashCode(), address2.hashCode(), "Expected hash codes to be equal");
  }

  @Test
  void testToString() {
    AddressInDTO address = new AddressInDTO();
    address.setStreet("123 Main Street");
    address.setCity("Springfield");
    address.setState("California");
    address.setCountry("USA");
    address.setPinCode("123456");
    address.setUserId(1);

    String expectedString = "AddressInDTO{street='123 Main Street', city='Springfield', state='California'," +
      " country='USA', pinCode='123456', userId=1}";
    assertEquals(expectedString, address.toString(), "Expected toString() to match the expected format");
  }
}
