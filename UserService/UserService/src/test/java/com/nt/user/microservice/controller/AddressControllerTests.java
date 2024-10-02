package com.nt.user.microservice.controller;

import com.nt.user.microservice.contoller.AddressController;
import com.nt.user.microservice.dto.AddressInDTO;
import com.nt.user.microservice.dto.AddressOutDTO;
import com.nt.user.microservice.dto.UserResponse;
import com.nt.user.microservice.service.AddressService;
import com.nt.user.microservice.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AddressControllerTests {

  private MockMvc mockMvc;

  @InjectMocks
  private AddressController addressController;

  @Mock
  private AddressService addressService;

  @Mock
  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();
  }

  @Test
  void addAddressTest() throws Exception {
    AddressInDTO addressInDTO = new AddressInDTO();
    addressInDTO.setUserId(1);
    addressInDTO.setStreet("Test Street");
    addressInDTO.setCity("City");
    addressInDTO.setState("State");
    addressInDTO.setCountry("County");
    addressInDTO.setPinCode("627043");

    UserResponse userResponse = new UserResponse();
    userResponse.setSuccessMessage("Address added successfully");

    when(addressService.addAddress(addressInDTO)).thenReturn(userResponse);

    mockMvc.perform(post("/addresses/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(addressInDTO)))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.successMessage").value("Address added successfully"));

  }

  @Test
  void getUserAddressesTest() throws Exception {
    Integer userId = 1;
    List<AddressOutDTO> addressList = new ArrayList<>();
    AddressOutDTO addressOutDTO = new AddressOutDTO();
    addressOutDTO.setStreet("Test Street");
    addressOutDTO.setCity("City");
    addressOutDTO.setState("State");
    addressOutDTO.setCountry("County");
    addressOutDTO.setPinCode("62704");
    addressList.add(addressOutDTO);

    when(addressService.getUserAddresses(userId)).thenReturn(addressList);

    mockMvc.perform(get("/addresses/user/{userId}", userId)
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].street").value("Test Street"))
      .andExpect(jsonPath("$[0].city").value("City"))
      .andExpect(jsonPath("$[0].state").value("State"))
      .andExpect(jsonPath("$[0].country").value("County"))
      .andExpect(jsonPath("$[0].pinCode").value("62704"));

  }

  @Test
  void deleteAddressTest() throws Exception {
    Integer addressId = 1;
    UserResponse response = new UserResponse();
    response.setSuccessMessage(Constants.ADDRESS_ADDED_SUCCESSFULLY);

    doNothing().when(addressService).deleteAddress(addressId);

    mockMvc.perform(delete("/addresses/{id}", addressId)
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.successMessage").value(Constants.ADDRESS_ADDED_SUCCESSFULLY));

  }
}
