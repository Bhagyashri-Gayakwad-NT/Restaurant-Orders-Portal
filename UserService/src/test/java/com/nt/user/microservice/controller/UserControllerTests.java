package com.nt.user.microservice.controller;

import com.nt.user.microservice.contoller.UserController;
import com.nt.user.microservice.dto.LogInDTO;
import com.nt.user.microservice.dto.UserInDTO;
import com.nt.user.microservice.dto.UserOutDTO;
import com.nt.user.microservice.dto.UserResponse;
import com.nt.user.microservice.service.UserService;
import com.nt.user.microservice.util.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTests {

  @InjectMocks
  private UserController userController;

  @Mock
  private UserService userService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testRegisterUser_Success() {
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setEmail("test@nucleusteq.com");
    userInDTO.setPassword("password1");
    userInDTO.setFirstName("John");
    userInDTO.setLastName("Doe");
    userInDTO.setPhoneNo("9876543210");
    userInDTO.setRole("USER");

    UserResponse userResponse = new UserResponse();
    userResponse.setSuccessMessage(Constants.USER_REGISTERED_SUCCESSFULLY);

    when(userService.registerUser(userInDTO)).thenReturn(userResponse);

    ResponseEntity<UserResponse> responseEntity = userController.registerUser(userInDTO);

    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertEquals(userResponse, responseEntity.getBody());
  }

  @Test
  void testRegisterUser_Exception() {
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setEmail("test@nucleusteq.com");

    when(userService.registerUser(userInDTO)).thenThrow(new RuntimeException("Error"));

    RuntimeException exception = assertThrows(RuntimeException.class, () -> userController.registerUser(userInDTO));
    assertEquals("Error", exception.getMessage());
  }

  @Test
  void testLoginUser_Success() {
    LogInDTO loginDTO = new LogInDTO();
    loginDTO.setEmail("test@nucleusteq.com");
    loginDTO.setPassword("password1");

    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setEmail("test@nucleusteq.com");
    userOutDTO.setRole("USER");
    userOutDTO.setWalletBalance(100.0);

    when(userService.loginUser(loginDTO.getEmail(), loginDTO.getPassword())).thenReturn(userOutDTO);

    ResponseEntity<UserOutDTO> responseEntity = userController.loginUser(loginDTO);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(userOutDTO, responseEntity.getBody());
  }

  @Test
  void testLoginUser_Exception() {
    LogInDTO loginDTO = new LogInDTO();
    loginDTO.setEmail("test@nucleusteq.com");
    loginDTO.setPassword("password1");

    when(userService.loginUser(loginDTO.getEmail(), loginDTO.getPassword())).thenThrow(new RuntimeException("Error"));

    RuntimeException exception = assertThrows(RuntimeException.class, () -> userController.loginUser(loginDTO));
    assertEquals("Error", exception.getMessage());
  }

  @Test
  void testGetUserProfile_Success() {
    Integer userId = 1;

    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setId(userId);
    userOutDTO.setFirstName("John");
    userOutDTO.setLastName("Doe");
    userOutDTO.setEmail("john.doe@nucleusteq.com");
    userOutDTO.setPhoneNo("9876543210");
    userOutDTO.setRole("USER");
    userOutDTO.setWalletBalance(100.0);

    when(userService.getUserProfile(userId)).thenReturn(userOutDTO);

    ResponseEntity<UserOutDTO> responseEntity = userController.getUserProfile(userId);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(userOutDTO, responseEntity.getBody());
  }

  @Test
  void testGetUserProfile_UserNotFound() {
    Integer userId = 1;

    when(userService.getUserProfile(userId)).thenThrow(new RuntimeException("User not found"));

    RuntimeException exception = assertThrows(RuntimeException.class, () -> userController.getUserProfile(userId));
    assertEquals("User not found", exception.getMessage());
  }

  @Test
  void testUpdateUserProfile_UserNotFound() {
    Integer userId = 1;
    UserInDTO userInDTO = new UserInDTO();

    when(userService.updateUserProfile(userId, userInDTO)).thenThrow(new RuntimeException("User not found"));

    RuntimeException exception = assertThrows(RuntimeException.class, () -> userController.updateUserProfile(userId, userInDTO));
    assertEquals("User not found", exception.getMessage());
  }

  @Test
  void testDeleteUser_Success() {
    Integer userId = 1;

    UserResponse userResponse = new UserResponse();
    userResponse.setSuccessMessage(Constants.USER_DELETED_SUCCESSFULLY);

    when(userService.deleteUser(userId)).thenReturn(userResponse);

    ResponseEntity<UserResponse> responseEntity = userController.deleteUser(userId);

    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertEquals(userResponse, responseEntity.getBody());
  }

  @Test
  void testDeleteUser_UserNotFound() {
    Integer userId = 1;

    when(userService.deleteUser(userId)).thenThrow(new RuntimeException("User not found"));

    RuntimeException exception = assertThrows(RuntimeException.class, () -> userController.deleteUser(userId));
    assertEquals("User not found", exception.getMessage());
  }
}
