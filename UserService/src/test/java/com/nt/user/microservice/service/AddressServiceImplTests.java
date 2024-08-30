//package com.nt.user.microservice.service;
//
//import com.nt.user.microservice.entites.Address;
//import com.nt.user.microservice.indto.AddressInDTO;
//import com.nt.user.microservice.outdto.AddressOutDTO;
//import com.nt.user.microservice.repository.AddressRepository;
//import com.nt.user.microservice.service.impl.AddressServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class AddressServiceImplTests {
//
//  @InjectMocks
//  private AddressServiceImpl addressService;
//
//  @Mock
//  private AddressRepository addressRepository;
//
//  @BeforeEach
//  void setUp() {
//    MockitoAnnotations.openMocks(this);
//  }
//
//  @Test
//  void testAddAddress() {
//    // Setup mock data
//    AddressInDTO addressInDTO = new AddressInDTO();
//    addressInDTO.setStreet("123 Main St");
//    addressInDTO.setCity("Springfield");
//    addressInDTO.setState("IL");
//    addressInDTO.setCountry("USA");
//    addressInDTO.setPinCode("62701");
//    addressInDTO.setUserId(1);
//
//    Address address = new Address();
//    address.setStreet("123 Main St");
//    address.setCity("Springfield");
//    address.setState("IL");
//    address.setCountry("USA");
//    address.setPinCode("62701");
//    address.setUserId(1);
//
//    when(addressRepository.save(any(Address.class))).thenReturn(address);
//
//    // Call the method under test
//    AddressOutDTO result = addressService.addAddress(addressInDTO);
//
//    // Verify the result
//    assertNotNull(result);
//    assertEquals("123 Main St", result.getStreet());
//    assertEquals("Springfield", result.getCity());
//    assertEquals("IL", result.getState());
//    assertEquals("USA", result.getCountry());
//    assertEquals("62701", result.getPinCode());
//    verify(addressRepository, times(1)).save(any(Address.class));
//  }
//
//  @Test
//  void testGetUserAddresses() {
//    // Setup mock data
//    Address address1 = new Address();
//    address1.setId(1);
//    address1.setStreet("123 Main St");
//    address1.setCity("Springfield");
//    address1.setState("IL");
//    address1.setCountry("USA");
//    address1.setPinCode("62701");
//    address1.setUserId(1);
//
//    Address address2 = new Address();
//    address2.setId(2);
//    address2.setStreet("456 Elm St");
//    address2.setCity("Springfield");
//    address2.setState("IL");
//    address2.setCountry("USA");
//    address2.setPinCode("62702");
//    address2.setUserId(1);
//
//    List<Address> addressList = new ArrayList<>();
//    addressList.add(address1);
//    addressList.add(address2);
//
//    when(addressRepository.findByUserId(1)).thenReturn(addressList);
//
//    // Call the method under test
//    List<AddressOutDTO> result = addressService.getUserAddresses(1);
//
//    // Verify the result
//    assertNotNull(result);
//    assertEquals(2, result.size());
//    assertEquals("123 Main St", result.get(0).getStreet());
//    assertEquals("456 Elm St", result.get(1).getStreet());
//    verify(addressRepository, times(1)).findByUserId(1);
//  }
//
//  @Test
//  void testDeleteAddress_Success() {
//    // Setup mock data
//    Integer addressId = 1;
//
//    when(addressRepository.existsById(addressId)).thenReturn(true);
//    doNothing().when(addressRepository).deleteById(addressId);
//
//    // Call the method under test
//    addressService.deleteAddress(addressId);
//
//    // Verify that the address was deleted
//    verify(addressRepository, times(1)).deleteById(addressId);
//  }
//
//
//}
