package com.nt.user.microservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.user.microservice.contoller.UserController;
import com.nt.user.microservice.indto.UserInDTO;
import com.nt.user.microservice.outdto.UserOutDTO;
import com.nt.user.microservice.outdto.UserResponse;
import com.nt.user.microservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

class UserControllerTests {

  private MockMvc mockMvc;

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test
  void testGetUserProfile() throws Exception {
    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setId(1);
    userOutDTO.setFirstName("John");
    userOutDTO.setLastName("Doe");
    userOutDTO.setEmail("john.doe@example.com");
    userOutDTO.setPhoneNo("1234567890");
    userOutDTO.setRole("USER");
    userOutDTO.setWalletBalance(100.0);

    when(userService.getUserProfile(anyInt())).thenReturn(userOutDTO);

    mockMvc.perform(get("/api/users/profile/1")
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.firstName").value("John"))
      .andExpect(jsonPath("$.walletBalance").value(100.0))
      .andDo(print());
  }

  @Test
  void testUpdateUserProfile() throws Exception {
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setFirstName("John");
    userInDTO.setLastName("Doe");
    userInDTO.setPhoneNo("1234567890");
    userInDTO.setPassword("newpassword");
    userInDTO.setRole("USER");

    UserResponse userResponse = new UserResponse();
    userResponse.setSuccessMessage("User updated successfully");

    when(userService.updateUserProfile(anyInt(), any(UserInDTO.class))).thenReturn(userResponse);

    mockMvc.perform(put("/api/users/update/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(asJsonString(userInDTO)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.successMessage").value("User updated successfully"))
      .andDo(print());
  }

  @Test
  void testDeleteUser() throws Exception {
    doNothing().when(userService).deleteUser(anyInt());

    mockMvc.perform(delete("/api/users/delete/1"))
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
