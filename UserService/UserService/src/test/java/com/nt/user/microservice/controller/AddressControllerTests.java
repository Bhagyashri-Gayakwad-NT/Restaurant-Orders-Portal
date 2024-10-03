package com.nt.user.microservice.controller;

import com.nt.user.microservice.contoller.AddressController;
import com.nt.user.microservice.dto.AddressInDTO;
import com.nt.user.microservice.dto.AddressOutDTO;
import com.nt.user.microservice.dto.UserResponse;
import com.nt.user.microservice.service.AddressService;
import com.nt.user.microservice.service.UserService;
import com.nt.user.microservice.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressControllerTests {

  @InjectMocks
  private AddressController addressController;

  @Mock
  private AddressService addressService;

  @Mock
  private UserService userService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testAddAddress_Success() {
    AddressInDTO addressInDTO = new AddressInDTO();
    addressInDTO.setStreet("Test Street");
    addressInDTO.setCity("TestCity");
    addressInDTO.setState("Test State");
    addressInDTO.setCountry("TestCountry");
    addressInDTO.setPinCode("62704");
    addressInDTO.setUserId(1);

    UserResponse userResponse = new UserResponse();
    userResponse.setSuccessMessage(Constants.ADDRESS_ADDED_SUCCESSFULLY);
    when(addressService.addAddress(addressInDTO)).thenReturn(userResponse);
    ResponseEntity<UserResponse> response = addressController.addAddress(addressInDTO);

    assertNotNull(response);
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(Constants.ADDRESS_ADDED_SUCCESSFULLY, response.getBody().getSuccessMessage());

    verify(addressService).addAddress(addressInDTO);
  }

  @Test
  void testAddAddress_Exception() {
    AddressInDTO addressInDTO = new AddressInDTO();
    addressInDTO.setUserId(1);

    when(addressService.addAddress(addressInDTO)).thenThrow(new RuntimeException("Some error"));

    RuntimeException exception = assertThrows(RuntimeException.class, () -> addressController.addAddress(addressInDTO));
    assertEquals("Some error", exception.getMessage());

    verify(addressService).addAddress(addressInDTO);
  }

  @Test
  void testGetUserAddresses_Success() {
    Integer userId = 1;

    List<AddressOutDTO> addressOutDTOList = new ArrayList<>();
    AddressOutDTO addressOutDTO = new AddressOutDTO();
    addressOutDTO.setId(1);
    addressOutDTO.setStreet("Test Street");
    addressOutDTO.setCity("TestCity");
    addressOutDTO.setState("TestState");
    addressOutDTO.setCountry("TestCountry");
    addressOutDTO.setPinCode("62704");

    addressOutDTOList.add(addressOutDTO);

    when(addressService.getUserAddresses(userId)).thenReturn(addressOutDTOList);

    ResponseEntity<List<AddressOutDTO>> response = addressController.getUserAddresses(userId);

    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(1, response.getBody().size());
    assertEquals(addressOutDTO.getId(), response.getBody().get(0).getId());

    verify(addressService).getUserAddresses(userId);
  }

  @Test
  void testGetUserAddresses_Exception() {
    Integer userId = 1;

    when(addressService.getUserAddresses(userId)).thenThrow(new RuntimeException("Some error"));

    RuntimeException exception = assertThrows(RuntimeException.class, () -> addressController.getUserAddresses(userId));
    assertEquals("Some error", exception.getMessage());

    verify(addressService).getUserAddresses(userId);
  }

  @Test
  void testDeleteAddress_Success() {
    Integer addressId = 1;

    doNothing().when(addressService).deleteAddress(addressId);

    ResponseEntity<UserResponse> response = addressController.deleteAddress(addressId);

    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(Constants.ADDRESS_ADDED_SUCCESSFULLY, response.getBody().getSuccessMessage());

    verify(addressService).deleteAddress(addressId);
  }

  @Test
  void testDeleteAddress_Exception() {
    Integer addressId = 1;

    doThrow(new RuntimeException("Some error")).when(addressService).deleteAddress(addressId);

    RuntimeException exception = assertThrows(RuntimeException.class, () -> addressController.deleteAddress(addressId));
    assertEquals("Some error", exception.getMessage());

    verify(addressService).deleteAddress(addressId);
  }
}
