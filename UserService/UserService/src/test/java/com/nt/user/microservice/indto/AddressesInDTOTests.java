package com.nt.user.microservice.indto;

import com.nt.user.microservice.dto.AddressInDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AddressesInDTOTests {

  private AddressInDTO addressInDTO;
  private Validator validator;

  @BeforeEach
  void setUp() {
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
    addressInDTO.setStreet("TestStreet 123");
    addressInDTO.setCity("SampleCity");
    addressInDTO.setState("TestState");
    addressInDTO.setCountry("Testland");
    addressInDTO.setPinCode("654321");
    addressInDTO.setUserId(999);

    Set<javax.validation.ConstraintViolation<AddressInDTO>> violations = validator.validate(addressInDTO);
    assertTrue(violations.isEmpty(), "There should be no validation violations for a valid AddressInDTO");
  }
  @Test
  void testEqualsAndHashCode() {
    AddressInDTO address1 = new AddressInDTO();
    address1.setStreet("TestStreet 1");
    address1.setCity("SampleCity");
    address1.setState("TestState");
    address1.setCountry("Testland");
    address1.setPinCode("654321");
    address1.setUserId(111);

    AddressInDTO address2 = new AddressInDTO();
    address2.setStreet("TestStreet 1");
    address2.setCity("SampleCity");
    address2.setState("TestState");
    address2.setCountry("Testland");
    address2.setPinCode("654321");
    address2.setUserId(111);

    assertEquals(address1, address2, "Expected both addresses to be equal");
    assertEquals(address1.hashCode(), address2.hashCode(), "Expected hash codes to be equal");
  }


  @Test
  void testToString() {
    AddressInDTO address = new AddressInDTO();
    address.setStreet("TestStreet 1");
    address.setCity("TestCity");
    address.setState("TestState");
    address.setCountry("Testland");
    address.setPinCode("654321");
    address.setUserId(111);

    String expectedString = "AddressInDTO{street='TestStreet 1', city='TestCity', state='TestState'," +
      " country='Testland', pinCode='654321', userId=111}";
    assertEquals(expectedString, address.toString(), "Expected toString() to match the expected format");
  }
}
