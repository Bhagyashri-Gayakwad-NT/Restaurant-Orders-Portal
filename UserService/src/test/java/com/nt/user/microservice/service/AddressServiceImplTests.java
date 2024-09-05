package com.nt.user.microservice.service;

import com.nt.user.microservice.entites.Address;
import com.nt.user.microservice.entites.User;
import com.nt.user.microservice.exceptions.AddressNotFoundException;
import com.nt.user.microservice.exceptions.UserNotFoundException;
import com.nt.user.microservice.indto.AddressInDTO;
import com.nt.user.microservice.outdto.AddressOutDTO;
import com.nt.user.microservice.outdto.UserResponse;
import com.nt.user.microservice.repository.AddressRepository;
import com.nt.user.microservice.repository.UserRepository;
import com.nt.user.microservice.service.impl.AddressServiceImpl;
import com.nt.user.microservice.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressServiceImplTests {

  @InjectMocks
  private AddressServiceImpl addressService;

  @Mock
  private AddressRepository addressRepository;

  @Mock
  private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testAddAddress_Success() {
    AddressInDTO addressInDTO = new AddressInDTO();
    addressInDTO.setStreet("123 Main St");
    addressInDTO.setCity("Springfield");
    addressInDTO.setState("IL");
    addressInDTO.setCountry("USA");
    addressInDTO.setPinCode("62704");
    addressInDTO.setUserId(1);

    User user = new User(); // Assume User entity is initialized appropriately
    user.setId(addressInDTO.getUserId());

    Address address = new Address();
    address.setId(1);
    address.setStreet(addressInDTO.getStreet());
    address.setCity(addressInDTO.getCity());
    address.setState(addressInDTO.getState());
    address.setCountry(addressInDTO.getCountry());
    address.setPinCode(addressInDTO.getPinCode());
    address.setUserId(addressInDTO.getUserId());

    when(userRepository.findById(addressInDTO.getUserId())).thenReturn(Optional.of(user));
    when(addressRepository.save(any(Address.class))).thenReturn(address);

    UserResponse response = addressService.addAddress(addressInDTO);

    assertNotNull(response);
    assertEquals(Constants.ADDRESS_ADDED_SUCCESSFULLY, response.getSuccessMessage());

    verify(userRepository).findById(addressInDTO.getUserId());
    verify(addressRepository).save(any(Address.class));
  }

  @Test
  void testAddAddress_UserNotFound() {
    AddressInDTO addressInDTO = new AddressInDTO();
    addressInDTO.setUserId(1);

    when(userRepository.findById(addressInDTO.getUserId())).thenReturn(Optional.empty());

    UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> addressService.addAddress(addressInDTO));
    assertEquals(Constants.USER_NOT_FOUND, exception.getMessage());

    verify(userRepository).findById(addressInDTO.getUserId());
    verify(addressRepository, never()).save(any(Address.class));
  }

  @Test
  void testGetUserAddresses_Success() {
    Integer userId = 1;

    List<Address> addresses = new ArrayList<>();
    Address address1 = new Address();
    address1.setId(1);
    address1.setStreet("123 Main St");
    address1.setCity("Springfield");
    address1.setState("IL");
    address1.setCountry("USA");
    address1.setPinCode("62704");
    address1.setUserId(userId);

    addresses.add(address1);

    when(userRepository.findById(userId)).thenReturn(Optional.of(new User())); // Mock user retrieval
    when(addressRepository.findAllByUserId(userId)).thenReturn(addresses);

    List<AddressOutDTO> addressOutDTOList = addressService.getUserAddresses(userId);

    assertNotNull(addressOutDTOList);
    assertEquals(1, addressOutDTOList.size());
    assertEquals(address1.getId(), addressOutDTOList.get(0).getId());

    verify(userRepository).findById(userId);
    verify(addressRepository).findAllByUserId(userId);
  }

  @Test
  void testGetUserAddresses_UserNotFound() {
    Integer userId = 1;

    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> addressService.getUserAddresses(userId));
    assertEquals(Constants.USER_NOT_FOUND, exception.getMessage());

    verify(userRepository).findById(userId);
    verify(addressRepository, never()).findAllByUserId(userId);
  }

  @Test
  void testDeleteAddress_Success() {
    Integer addressId = 1;

    when(addressRepository.existsById(addressId)).thenReturn(true);

    addressService.deleteAddress(addressId);

    verify(addressRepository).deleteById(addressId);
  }

  @Test
  void testDeleteAddress_AddressNotFound() {
    Integer addressId = 1;

    when(addressRepository.existsById(addressId)).thenReturn(false);

    AddressNotFoundException exception = assertThrows(AddressNotFoundException.class, () -> addressService.deleteAddress(addressId));
    assertEquals("Address not found", exception.getMessage());

    verify(addressRepository, never()).deleteById(addressId);
  }
}
