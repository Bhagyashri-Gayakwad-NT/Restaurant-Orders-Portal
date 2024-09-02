package com.nt.user.microservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.user.microservice.contoller.AddressController;
import com.nt.user.microservice.outdto.AddressOutDTO;
import com.nt.user.microservice.service.AddressService;
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

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class AddressControllerTests {

  private MockMvc mockMvc;

  @Mock
  private AddressService addressService;

  @InjectMocks
  private AddressController addressController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();
  }

  @Test
  void testGetUserAddresses() throws Exception {
    Integer userId = 1;

    List<AddressOutDTO> addresses = new ArrayList<>();
    AddressOutDTO addressOutDTO = new AddressOutDTO();
    addressOutDTO.setId(1);
    addressOutDTO.setStreet("123 Main St");
    addressOutDTO.setCity("Springfield");
    addressOutDTO.setState("IL");
    addressOutDTO.setCountry("USA");
    addressOutDTO.setPinCode("62704");

    addresses.add(addressOutDTO);

    when(addressService.getUserAddresses(anyInt())).thenReturn(addresses);

    mockMvc.perform(get("/addresses/user/" + userId)
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].id").value(1))
      .andExpect(jsonPath("$[0].street").value("123 Main St"))
      .andExpect(jsonPath("$[0].city").value("Springfield"))
      .andExpect(jsonPath("$[0].state").value("IL"))
      .andExpect(jsonPath("$[0].country").value("USA"))
      .andExpect(jsonPath("$[0].pinCode").value("62704"))
      .andDo(print());
  }

  @Test
  void testDeleteAddress() throws Exception {
    Integer addressId = 1;

    doNothing().when(addressService).deleteAddress(anyInt());

    mockMvc.perform(delete("/addresses/" + addressId))
      .andExpect(status().isNoContent())
      .andDo(print());
  }

  private String asJsonString(Object obj) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
