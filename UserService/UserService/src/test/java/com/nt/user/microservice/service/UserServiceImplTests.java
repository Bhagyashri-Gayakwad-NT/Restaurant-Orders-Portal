package com.nt.user.microservice.service;

import com.nt.user.microservice.dto.EmailRequestDTO;
import com.nt.user.microservice.dto.LoginOutDTO;
import com.nt.user.microservice.dto.UserInDTO;
import com.nt.user.microservice.dto.UserOutDTO;
import com.nt.user.microservice.dto.UserResponse;
import com.nt.user.microservice.entites.User;
import com.nt.user.microservice.entites.WalletBalance;
import com.nt.user.microservice.exceptions.InvalidCredentialsException;
import com.nt.user.microservice.exceptions.ResourceAlreadyExistException;
import com.nt.user.microservice.exceptions.ResourceNotFoundException;
import com.nt.user.microservice.repository.UserRepository;
import com.nt.user.microservice.repository.WalletBalanceRepository;
import com.nt.user.microservice.serviceimpl.UserServiceImpl;
import com.nt.user.microservice.util.Base64Util;
import com.nt.user.microservice.util.Constants;
import com.nt.user.microservice.util.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTests {

  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private EmailService emailService;

  @Mock
  private WalletBalanceRepository walletBalanceRepository;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testRegisterUser_Success() {
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setFirstName("First");
    userInDTO.setLastName("Last");
    userInDTO.setEmail("test@example.com");
    userInDTO.setPhoneNo("1234567890");
    userInDTO.setPassword("password");
    userInDTO.setRole("USER");

    User userToSave = new User();
    userToSave.setFirstName(userInDTO.getFirstName());
    userToSave.setLastName(userInDTO.getLastName());
    userToSave.setEmail(userInDTO.getEmail().toLowerCase());
    userToSave.setPhoneNo(userInDTO.getPhoneNo());
    userToSave.setPassword(Base64Util.encode(userInDTO.getPassword()));
    userToSave.setRole(Role.USER);

    when(userRepository.findByEmail(userInDTO.getEmail().toLowerCase())).thenReturn(Optional.empty());
    when(userRepository.save(userToSave)).thenReturn(userToSave);

    UserResponse response = userService.registerUser(userInDTO);

    assertEquals(Constants.USER_REGISTERED_SUCCESSFULLY, response.getSuccessMessage());
  }

  @Test
  public void testRegisterUser_UserAlreadyExists() {
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setEmail("test@example.com");

    User existingUser = new User();
    existingUser.setEmail("test@example.com");

    when(userRepository.findByEmail(userInDTO.getEmail().toLowerCase())).thenReturn(Optional.of(existingUser));

    ResourceAlreadyExistException exception = assertThrows(ResourceAlreadyExistException.class, () -> {
      userService.registerUser(userInDTO);
    });

    assertEquals(Constants.USER_ALREADY_REGISTERED, exception.getMessage());
  }

  @Test
  public void testLoginUser_Success() {
    String email = "test@example.com";
    String password = "password";

    User user = new User();
    user.setEmail(email);
    user.setPassword(Base64Util.encode(password));
    user.setId(1);
    user.setRole(Role.USER);

    when(userRepository.findByEmail(email.toLowerCase())).thenReturn(Optional.of(user));

    LoginOutDTO loginOutDTO = userService.loginUser(email, password);

    assertEquals(user.getId(), loginOutDTO.getId());
    assertEquals(user.getRole().name(), loginOutDTO.getRole());
  }

  @Test
  public void testLoginUser_UserNotFound() {
    String email = "test@example.com";

    when(userRepository.findByEmail(email.toLowerCase())).thenReturn(Optional.empty());

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
      userService.loginUser(email, "password");
    });

    assertEquals(Constants.USER_NOT_FOUND, exception.getMessage());
  }

  @Test
  public void testLoginUser_InvalidCredentials() {
    String email = "test@example.com";
    String password = "wrongPassword";

    User user = new User();
    user.setEmail(email);
    user.setPassword(Base64Util.encode("correctPassword"));

    when(userRepository.findByEmail(email.toLowerCase())).thenReturn(Optional.of(user));

    InvalidCredentialsException exception = assertThrows(InvalidCredentialsException.class, () -> {
      userService.loginUser(email, password);
    });

    assertEquals(Constants.INVALID_CREDENTIALS, exception.getMessage());
  }

  @Test
  public void testGetUserProfile_Success() {
    Integer userId = 1;
    User user = new User();
    user.setId(userId);
    user.setFirstName("First");
    user.setLastName("Last");
    user.setEmail("test@example.com");
    user.setPhoneNo("1234567890");
    user.setRole(Role.USER);

    WalletBalance walletBalance = new WalletBalance();
    walletBalance.setUserId(userId);
    walletBalance.setBalance(1000.0);

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    when(walletBalanceRepository.findByUserId(userId)).thenReturn(walletBalance);

    UserOutDTO userOutDTO = userService.getUserProfile(userId);

    assertEquals(user.getId(), userOutDTO.getId());
    assertEquals(user.getFirstName(), userOutDTO.getFirstName());
    assertEquals(walletBalance.getBalance(), userOutDTO.getWalletBalance());
  }

  @Test
  public void testGetUserProfile_UserNotFound() {
    Integer userId = 1;

    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
      userService.getUserProfile(userId);
    });

    assertEquals(Constants.USER_NOT_FOUND, exception.getMessage());
  }

  @Test
  public void testUpdateUserProfile_Success() {
    Integer userId = 1;
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setFirstName("FirstUpdate");
    userInDTO.setLastName("Last");
    userInDTO.setPhoneNo("0987654321");
    userInDTO.setRole("USER");

    User user = new User();
    user.setId(userId);
    user.setFirstName("First");
    user.setLastName("Last");
    user.setPhoneNo("1234567890");

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    when(userRepository.save(user)).thenReturn(user);

    UserResponse response = userService.updateUserProfile(userId, userInDTO);

    assertEquals(Constants.USER_PROFILE_UPDATED_SUCCESSFULLY, response.getSuccessMessage());
    assertEquals("FirstUpdate", user.getFirstName());
  }

  @Test
  public void testUpdateUserProfile_UserNotFound() {
    Integer userId = 1;
    UserInDTO userInDTO = new UserInDTO();

    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
      userService.updateUserProfile(userId, userInDTO);
    });

    assertEquals(Constants.USER_NOT_FOUND, exception.getMessage());
  }

  @Test
  public void testDeleteUser_Success() {
    Integer userId = 1;
    User user = new User();
    user.setId(userId);

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));

    UserResponse response = userService.deleteUser(userId);

    assertEquals(Constants.USER_DELETED_SUCCESSFULLY, response.getSuccessMessage());
    verify(walletBalanceRepository, times(1)).deleteByUserId(userId);
  }

  @Test
  public void testDeleteUser_UserNotFound() {
    Integer userId = 1;

    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
      userService.deleteUser(userId);
    });

    assertEquals(Constants.USER_NOT_FOUND, exception.getMessage());
  }

  @Test
  public void testSendMail_Success() {
    EmailRequestDTO emailRequestDTO = new EmailRequestDTO();
    emailRequestDTO.setSubject("Test Email");
    emailRequestDTO.setText("This is a test email.");

    UserResponse response = userService.sendMail(emailRequestDTO);

    assertEquals(Constants.EMAIL_SENT_SUCCESSFULLY, response.getSuccessMessage());
    verify(emailService, times(1)).sendMail(eq(Constants.SENDER), eq(emailRequestDTO), anyList());
  }
}
