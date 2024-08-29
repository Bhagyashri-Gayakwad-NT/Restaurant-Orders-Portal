package com.nt.user.microservice.controller;

import com.nt.user.microservice.contoller.UserController;
import com.nt.user.microservice.indto.LogInDTO;
import com.nt.user.microservice.indto.UserInDTO;
import com.nt.user.microservice.outdto.UserOutDTO;
import com.nt.user.microservice.outdto.UserResponse;
import com.nt.user.microservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTests {

  private MockMvc mockMvc;

  @Mock
  private UserService userService;

  @InjectMocks
  private UserController userController;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test
  public void testRegisterUser() throws Exception {
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setEmail("user@nucleusteq.com");
    userInDTO.setPassword("Password1");

    String successMessage = "User registered successfully";
    when(userService.registerUser(any(UserInDTO.class))).thenReturn(successMessage);

    mockMvc.perform(post("/users/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\":\"user@nucleusteq.com\",\"password\":\"Password1\"}"))
      .andExpect(status().isCreated())
      .andExpect(content().json("{\"successMessage\":\"User registered successfully\"}"));

    verify(userService, times(1)).registerUser(any(UserInDTO.class));
  }

  @Test
  public void testLoginUser() throws Exception {
    LogInDTO loginDTO = new LogInDTO();
    loginDTO.setEmail("user@nucleusteq.com");
    loginDTO.setPassword("Password1");

    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setEmail("user@nucleusteq.com");

    when(userService.loginUser(anyString(), anyString())).thenReturn(userOutDTO);

    mockMvc.perform(post("/users/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\":\"user@nucleusteq.com\",\"password\":\"Password1\"}"))
      .andExpect(status().isOk())
      .andExpect(content().json("{\"email\":\"user@nucleusteq.com\"}"));

    verify(userService, times(1)).loginUser(anyString(), anyString());
  }

  @Test
  public void testGetUserProfile() throws Exception {
    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setEmail("user@nucleusteq.com");

    when(userService.getUserProfile(anyInt())).thenReturn(userOutDTO);

    mockMvc.perform(get("/users/profile/1"))
      .andExpect(status().isOk())
      .andExpect(content().json("{\"email\":\"user@nucleusteq.com\"}"));

    verify(userService, times(1)).getUserProfile(anyInt());
  }

  @Test
  public void testUpdateUserProfile() throws Exception {
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setEmail("user@nucleusteq.com");
    userInDTO.setPassword("Password1");

    mockMvc.perform(put("/users/profile/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"email\":\"user@nucleusteq.com\",\"password\":\"Password1\"}"))
      .andExpect(status().isNoContent());

    verify(userService, times(1)).updateUserProfile(anyInt(), any(UserInDTO.class));
  }

  @Test
  public void testDeleteUser() throws Exception {
    mockMvc.perform(delete("/users/1"))
      .andExpect(status().isNoContent());

    verify(userService, times(1)).deleteUser(anyInt());
  }
}
