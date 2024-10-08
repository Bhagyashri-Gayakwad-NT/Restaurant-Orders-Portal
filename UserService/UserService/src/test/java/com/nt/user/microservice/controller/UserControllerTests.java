package com.nt.user.microservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.user.microservice.contoller.UserController;
import com.nt.user.microservice.dto.*;
import com.nt.user.microservice.service.UserService;
import com.nt.user.microservice.service.WalletBalanceService;
import com.nt.user.microservice.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTests {

  private MockMvc mockMvc;

  @Mock
  private UserService userService;

  @Mock
  private WalletBalanceService walletBalanceService;

  @InjectMocks
  private UserController userController;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    objectMapper = new ObjectMapper();
  }

  @Test
  void registerUserTest() throws Exception {
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setEmail("test@nucleusteq.com");
    userInDTO.setFirstName("TestFirst");
    userInDTO.setLastName("TestLast");
    userInDTO.setPassword("password123");
    userInDTO.setPhoneNo("9876543210");
    userInDTO.setRole("USER");

    UserResponse userResponse = new UserResponse();
    userResponse.setSuccessMessage(Constants.USER_REGISTERED_SUCCESSFULLY);

    when(userService.registerUser(userInDTO)).thenReturn(userResponse);

    mockMvc.perform(post("/users/register")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(userInDTO)))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.successMessage").value(Constants.USER_REGISTERED_SUCCESSFULLY));
  }

  @Test
  void loginUserTest() throws Exception {
    LogInDTO logInDTO = new LogInDTO();
    logInDTO.setEmail("test@nucleusteq.com");
    logInDTO.setPassword("password123");

    LoginOutDTO loginOutDTO = new LoginOutDTO();

    when(userService.loginUser(logInDTO.getEmail(), logInDTO.getPassword())).thenReturn(loginOutDTO);

    mockMvc.perform(post("/users/login")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(logInDTO)))
      .andExpect(status().isOk());
  }

  @Test
  void getUserProfileTest() throws Exception {
    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setId(1);
    userOutDTO.setEmail("test@nucleusteq.com");

    when(userService.getUserProfile(1)).thenReturn(userOutDTO);

    mockMvc.perform(get("/users/profile/1")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.email").value("test@nucleusteq.com"));
  }

  @Test
  void updateUserProfileTest() throws Exception {
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setFirstName("TestFirst");
    userInDTO.setLastName("TestLast");
    userInDTO.setEmail("test@nucleusteq.com");

    UserResponse userResponse = new UserResponse();
    userResponse.setSuccessMessage(Constants.USER_PROFILE_UPDATED_SUCCESSFULLY);

    when(userService.updateUserProfile(1, userInDTO)).thenReturn(userResponse);

    mockMvc.perform(put("/users/update/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(userInDTO)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.successMessage").value(Constants.USER_PROFILE_UPDATED_SUCCESSFULLY));
  }

  @Test
  void deleteUserTest() throws Exception {
    UserResponse userResponse = new UserResponse();
    userResponse.setSuccessMessage(Constants.USER_DELETED_SUCCESSFULLY);

    when(userService.deleteUser(1)).thenReturn(userResponse);

    mockMvc.perform(delete("/users/delete/1")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.successMessage").value(Constants.USER_DELETED_SUCCESSFULLY));
  }

  @Test
  void addMoneyTest() throws Exception {
    AmountInDTO amountInDTO = new AmountInDTO();
    amountInDTO.setBalance(500.0);

    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setId(1);
    userOutDTO.setWalletBalance(1500.0);

    when(walletBalanceService.addMoney(1, 500.0)).thenReturn(userOutDTO);

    mockMvc.perform(put("/users/addMoney/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(amountInDTO)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.walletBalance").value(1500.0));
  }

  @Test
  void updateWalletBalanceTest() throws Exception {
    AmountInDTO amountInDTO = new AmountInDTO();
    amountInDTO.setBalance(100.0);

    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setId(1);
    userOutDTO.setWalletBalance(900.0);

    when(walletBalanceService.updateWalletBalance(1, 100.0)).thenReturn(userOutDTO);

    mockMvc.perform(put("/users/walletBalance/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(amountInDTO)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.walletBalance").value(900.0));
  }

  @Test
  void sendEmailTest() throws Exception {
    EmailRequestDTO emailRequestDTO = new EmailRequestDTO();
    emailRequestDTO.setSubject("Test Email");
    emailRequestDTO.setText("This is a test email.");

    UserResponse userResponse = new UserResponse();
    userResponse.setSuccessMessage(Constants.EMAIL_SENT_SUCCESSFULLY);

    when(userService.sendMail(emailRequestDTO)).thenReturn(userResponse);

    mockMvc.perform(post("/users/send")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(emailRequestDTO)))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.successMessage").value(Constants.EMAIL_SENT_SUCCESSFULLY));
  }
}
