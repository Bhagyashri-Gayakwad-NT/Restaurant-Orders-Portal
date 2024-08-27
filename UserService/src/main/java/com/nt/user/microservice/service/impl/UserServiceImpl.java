package com.nt.user.microservice.service.impl;

import com.nt.user.microservice.exceptions.InvalidCredentialsException;
import com.nt.user.microservice.exceptions.UserAlreadyExistsException;
import com.nt.user.microservice.exceptions.UserNotFoundException;
import com.nt.user.microservice.util.Constants;
import com.nt.user.microservice.util.Role;
import com.nt.user.microservice.entites.User;
import com.nt.user.microservice.entites.WalletBalance;
import com.nt.user.microservice.indto.UserInDTO;
import com.nt.user.microservice.outdto.UserOutDTO;
import com.nt.user.microservice.repository.UserRepository;
import com.nt.user.microservice.repository.WalletBalanceRepository;
import com.nt.user.microservice.service.UserService;
import com.nt.user.microservice.util.Base64Util;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;
  private final WalletBalanceRepository walletBalanceRepository;

  public UserServiceImpl(UserRepository userRepository, WalletBalanceRepository walletBalanceRepository) {
    this.userRepository = userRepository;
    this.walletBalanceRepository = walletBalanceRepository;
  }

  @Override
  public UserOutDTO registerUser(UserInDTO userInDTO) {
    // Check if a user with the same email already exists
    Optional<User> existingUser = userRepository.findByEmail(userInDTO.getEmail());
    if (existingUser.isPresent()) {
      throw new UserAlreadyExistsException("User already registered with this email");
    }

    // Proceed to create a new user
    User user = new User();
    user.setFirstName(userInDTO.getFirstName());
    user.setLastName(userInDTO.getLastName());
    user.setEmail(userInDTO.getEmail());
    user.setPhoneNo(userInDTO.getPhoneNo());
    user.setPassword(Base64Util.encode(userInDTO.getPassword()));
    user.setRole(Role.valueOf(userInDTO.getRole().toUpperCase()));

    User savedUser = userRepository.save(user);

    Double walletBalance;
    if (Role.USER.equals(user.getRole())) {  // Check if the role is 'USER'
      walletBalance = Constants.INITIAL_WALLET_BALANCE;  // Set balance to 1000
    } else {
      walletBalance = Constants.ZERO_WALLET_BALANCE;  // Set balance to 0
    }

    // Save the wallet balance
    WalletBalance walletBalanceEntity = new WalletBalance();
    walletBalanceEntity.setUserId(savedUser.getId());
    walletBalanceEntity.setBalance(walletBalance);
    walletBalanceRepository.save(walletBalanceEntity);

    // Prepare the response DTO
    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setId(savedUser.getId());
    userOutDTO.setFirstName(savedUser.getFirstName());
    userOutDTO.setLastName(savedUser.getLastName());
    userOutDTO.setEmail(savedUser.getEmail());
    userOutDTO.setPhoneNo(savedUser.getPhoneNo());
    userOutDTO.setRole(savedUser.getRole().toString());

    return userOutDTO;
  }


  @Override
  public UserOutDTO loginUser(String email, String password) {
    Optional<User> userOptional = userRepository.findByEmail(email);

    if (userOptional.isPresent()) {
      User user = userOptional.get();

      // Decode the stored password and compare it with the provided one
      if (Base64Util.decode(user.getPassword()).equals(password)) {
        WalletBalance walletBalance = walletBalanceRepository.findByUserId(user.getId());

        UserOutDTO userOutDTO = new UserOutDTO();
        userOutDTO.setId(user.getId());
        userOutDTO.setFirstName(user.getFirstName());
        userOutDTO.setLastName(user.getLastName());
        userOutDTO.setEmail(user.getEmail());
        userOutDTO.setPhoneNo(user.getPhoneNo());
        userOutDTO.setRole(user.getRole().name());
        // Include wallet balance only if the user has one
        if (user.getRole() == Role.USER) {
          userOutDTO.setWalletBalance(walletBalance != null ? walletBalance.getBalance() : 0.0);
        }


        return userOutDTO;
      } else {
        throw new UserNotFoundException("User with email " + email + " not found");
      }
    } else {
      throw new InvalidCredentialsException("Invalid email or password");
    }
  }
  @Override
  public UserOutDTO getUserProfile(Integer id) {
    Optional<User> userOptional = userRepository.findById(id);
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      WalletBalance walletBalance = walletBalanceRepository.findByUserId(user.getId());

      UserOutDTO userOutDTO = new UserOutDTO();
      userOutDTO.setId(user.getId());
      userOutDTO.setFirstName(user.getFirstName());
      userOutDTO.setLastName(user.getLastName());
      userOutDTO.setEmail(user.getEmail());
      userOutDTO.setPhoneNo(user.getPhoneNo());
      userOutDTO.setRole(user.getRole().name());
      userOutDTO.setWalletBalance(walletBalance != null ? walletBalance.getBalance() : 0.0);

      return userOutDTO;
    } else {
      throw new IllegalArgumentException("User not found");
    }
  }
  @Override
  public void updateUserProfile(Integer id, UserInDTO userInDTO) {
    // Find user by ID
    User user = userRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("User not found"));

    // Update user details
    user.setFirstName(userInDTO.getFirstName());
    user.setLastName(userInDTO.getLastName());
    user.setPhoneNo(userInDTO.getPhoneNo());
    if (userInDTO.getPassword() != null && !userInDTO.getPassword().isEmpty()) {
      user.setPassword(Base64Util.encode(userInDTO.getPassword()));
    }
    user.setRole(Role.valueOf(userInDTO.getRole().toUpperCase()));

    // Save updated user
    userRepository.save(user);
  }

  @Override
  public void deleteUser(Integer id) {
    // Find user by ID
    User user = userRepository.findById(id)
      .orElseThrow(() -> new IllegalArgumentException("User not found"));

    // Delete user and wallet balance
    userRepository.delete(user);
    walletBalanceRepository.deleteByUserId(id);
  }
}