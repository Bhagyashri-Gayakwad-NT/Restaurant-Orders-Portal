package com.nt.order.microservice.dtos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the AddressOutDTO class.
 */
public class AddressOutDTOTest {

  private AddressOutDTO addressOutDTO;

  /**
   * Set up the AddressOutDTO instance before each test.
   */
  @BeforeEach
  public void setUp() {
    addressOutDTO = new AddressOutDTO();
  }

  /**
   * Test getter and setter for the id field.
   */
  @Test
  public void testGetSetId() {
    Integer testId = 123; // Dummy value for testing
    addressOutDTO.setId(testId);
    assertEquals(testId, addressOutDTO.getId(), "The ID should match the set value.");
  }

  /**
   * Test getter and setter for the street field.
   */
  @Test
  public void testGetSetStreet() {
    String testStreet = "Test Street"; // Dummy value for testing
    addressOutDTO.setStreet(testStreet);
    assertEquals(testStreet, addressOutDTO.getStreet(), "The street should match the set value.");
  }

  /**
   * Test getter and setter for the city field.
   */
  @Test
  public void testGetSetCity() {
    String testCity = "Test City"; // Dummy value for testing
    addressOutDTO.setCity(testCity);
    assertEquals(testCity, addressOutDTO.getCity(), "The city should match the set value.");
  }

  /**
   * Test getter and setter for the state field.
   */
  @Test
  public void testGetSetState() {
    String testState = "Test State"; // Dummy value for testing
    addressOutDTO.setState(testState);
    assertEquals(testState, addressOutDTO.getState(), "The state should match the set value.");
  }

  /**
   * Test getter and setter for the country field.
   */
  @Test
  public void testGetSetCountry() {
    String testCountry = "Test Country"; // Dummy value for testing
    addressOutDTO.setCountry(testCountry);
    assertEquals(testCountry, addressOutDTO.getCountry(), "The country should match the set value.");
  }

  /**
   * Test getter and setter for the pinCode field.
   */
  @Test
  public void testGetSetPinCode() {
    String testPinCode = "123456"; // Dummy value for testing
    addressOutDTO.setPinCode(testPinCode);
    assertEquals(testPinCode, addressOutDTO.getPinCode(), "The pin code should match the set value.");
  }
}
