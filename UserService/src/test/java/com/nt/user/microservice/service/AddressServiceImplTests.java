package com.nt.user.microservice.service;

import com.nt.user.microservice.entites.Address;
import com.nt.user.microservice.indto.AddressInDTO;
import com.nt.user.microservice.outdto.AddressOutDTO;
import com.nt.user.microservice.repository.AddressRepository;
import com.nt.user.microservice.service.impl.AddressServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AddressServiceImplTests {

  @InjectMocks
  private AddressServiceImpl addressService;

  @Mock
  private AddressRepository addressRepository;

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

    Address address = new Address();
    address.setId(1);
    address.setStreet(addressInDTO.getStreet());
    address.setCity(addressInDTO.getCity());
    address.setState(addressInDTO.getState());
    address.setCountry(addressInDTO.getCountry());
    address.setPinCode(addressInDTO.getPinCode());
    address.setUserId(addressInDTO.getUserId());

    when(addressRepository.save(any(Address.class))).thenReturn(address);

    AddressOutDTO addressOutDTO = addressService.addAddress(addressInDTO);

    assertNotNull(addressOutDTO);
    assertEquals(address.getId(), addressOutDTO.getId());
    assertEquals(address.getStreet(), addressOutDTO.getStreet());
    assertEquals(address.getCity(), addressOutDTO.getCity());
    assertEquals(address.getState(), addressOutDTO.getState());
    assertEquals(address.getCountry(), addressOutDTO.getCountry());
    assertEquals(address.getPinCode(), addressOutDTO.getPinCode());

    verify(addressRepository).save(any(Address.class));
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

    when(addressRepository.findByUserId(userId)).thenReturn(addresses);

    List<AddressOutDTO> addressOutDTOList = addressService.getUserAddresses(userId);

    assertNotNull(addressOutDTOList);
    assertEquals(1, addressOutDTOList.size());
    assertEquals(address1.getId(), addressOutDTOList.get(0).getId());

    verify(addressRepository).findByUserId(userId);
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

    IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> addressService.deleteAddress(addressId));
    assertEquals("Address not found", exception.getMessage());

    verify(addressRepository, never()).deleteById(addressId);
  }
}
