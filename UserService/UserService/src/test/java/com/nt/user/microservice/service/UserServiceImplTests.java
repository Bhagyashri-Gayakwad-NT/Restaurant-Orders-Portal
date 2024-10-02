package com.nt.user.microservice.service;

import com.nt.user.microservice.dto.LoginOutDTO;
import com.nt.user.microservice.dto.UserInDTO;
import com.nt.user.microservice.dto.UserOutDTO;
import com.nt.user.microservice.dto.UserResponse;
import com.nt.user.microservice.entites.User;
import com.nt.user.microservice.entites.WalletBalance;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private UserRepository userRepository;

  @Mock
  private EmailService emailService;


  @Mock
  private WalletBalanceRepository walletBalanceRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testRegisterUser_Success() {
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setEmail("test@nucleusteq.com");
    userInDTO.setPassword("password1");
    userInDTO.setFirstName("First");
    userInDTO.setLastName("Last");
    userInDTO.setPhoneNo("9876543210");
    userInDTO.setRole(String.valueOf(Role.USER));

    when(userRepository.findByEmail(userInDTO.getEmail())).thenReturn(Optional.empty());

    User user = new User();
    user.setId(1);
    user.setEmail(userInDTO.getEmail());
    user.setPassword(Base64Util.encode(userInDTO.getPassword()));
    user.setRole(Role.USER);

    when(userRepository.save(any(User.class))).thenReturn(user);

    WalletBalance walletBalance = new WalletBalance();
    walletBalance.setUserId(user.getId());
    walletBalance.setBalance(Constants.INITIAL_WALLET_BALANCE);
    when(walletBalanceRepository.save(any(WalletBalance.class))).thenReturn(walletBalance);

    UserResponse response = userService.registerUser(userInDTO);

    assertEquals(Constants.USER_REGISTERED_SUCCESSFULLY, response.getSuccessMessage());
    verify(userRepository).save(any(User.class));
    verify(walletBalanceRepository).save(any(WalletBalance.class));
  }

  @Test
  void testRegisterUser_UserAlreadyExists() {
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setEmail("existing@nucleusteq.com");

    when(userRepository.findByEmail(userInDTO.getEmail())).thenReturn(Optional.of(new User()));

    assertThrows(ResourceAlreadyExistException.class, () -> userService.registerUser(userInDTO));
    verify(userRepository, never()).save(any(User.class));
  }


  @Test
  void testLoginUser_Success() {
    String email = "test@nucleusteq.com";
    String password = "password1";

    User user = new User();
    user.setEmail(email);
    user.setPassword(Base64Util.encode(password));
    user.setId(1);
    user.setRole(Role.USER);

    when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

    LoginOutDTO loginOutDTO = userService.loginUser(email, password);

    assertNotNull(loginOutDTO);
    assertEquals(user.getRole().name(), loginOutDTO.getRole());
  }

  @Test
  void testGetUserProfile_Success() {
    Integer userId = 1;

    User user = new User();
    user.setId(userId);
    user.setFirstName("First");
    user.setLastName("Last");
    user.setEmail("john.doe@nucleusteq.com");
    user.setPhoneNo("9876543210");
    user.setRole(Role.USER);

    WalletBalance walletBalance = new WalletBalance();
    walletBalance.setUserId(userId);
    walletBalance.setBalance(100.0);

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    when(walletBalanceRepository.findByUserId(userId)).thenReturn(walletBalance);

    UserOutDTO userOutDTO = userService.getUserProfile(userId);

    assertNotNull(userOutDTO);
    assertEquals(userId, userOutDTO.getId());
    assertEquals(user.getFirstName(), userOutDTO.getFirstName());
    assertEquals(user.getLastName(), userOutDTO.getLastName());
    assertEquals(user.getEmail(), userOutDTO.getEmail());
    assertEquals(user.getPhoneNo(), userOutDTO.getPhoneNo());
    assertEquals(user.getRole().name(), userOutDTO.getRole());
    assertEquals(walletBalance.getBalance(), userOutDTO.getWalletBalance());
  }

  @Test
  void testGetUserProfile_UserNotFound() {
    Integer userId = 1;

    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> userService.getUserProfile(userId));
  }

  @Test
  void testUpdateUserProfile_Success() {
    Integer userId = 1;
    UserInDTO userInDTO = new UserInDTO();
    userInDTO.setFirstName("First");
    userInDTO.setLastName("Last");
    userInDTO.setPhoneNo("9876543211");
    userInDTO.setPassword("newpassword1");
    userInDTO.setRole(String.valueOf(Role.USER));

    User existingUser = new User();
    existingUser.setId(userId);
    existingUser.setFirstName("First");
    existingUser.setLastName("Last");
    existingUser.setPhoneNo("9876543210");
    existingUser.setPassword(Base64Util.encode("password1"));

    when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
    when(userRepository.save(any(User.class))).thenReturn(existingUser);

    UserResponse userResponse = userService.updateUserProfile(userId, userInDTO);

    assertNotNull(userResponse);
    assertEquals(Constants.USER_PROFILE_UPDATED_SUCCESSFULLY, userResponse.getSuccessMessage());
    assertEquals(userInDTO.getFirstName(), existingUser.getFirstName());
    assertEquals(userInDTO.getLastName(), existingUser.getLastName());
    assertEquals(userInDTO.getPhoneNo(), existingUser.getPhoneNo());
    assertEquals(Base64Util.encode(userInDTO.getPassword()), existingUser.getPassword());

    assertEquals(Role.valueOf(userInDTO.getRole()
      .toUpperCase()), existingUser.getRole());
  }

  @Test
  void testUpdateUserProfile_UserNotFound() {
    Integer userId = 1;
    UserInDTO userInDTO = new UserInDTO();

    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> userService.updateUserProfile(userId, userInDTO));
  }

  @Test
  void testDeleteUser_Success() {
    Integer userId = 1;

    User user = new User();
    user.setId(userId);

    when(userRepository.findById(userId)).thenReturn(Optional.of(user));

    userService.deleteUser(userId);

    verify(userRepository).delete(user);
    verify(walletBalanceRepository).deleteByUserId(userId);
  }

  @Test
  void testDeleteUser_UserNotFound() {
    Integer userId = 1;

    when(userRepository.findById(userId)).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(userId));
  }
}