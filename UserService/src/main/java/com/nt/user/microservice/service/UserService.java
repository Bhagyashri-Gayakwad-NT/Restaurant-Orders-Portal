package com.nt.user.microservice.service;

import com.nt.user.microservice.indto.UserInDTO;
import com.nt.user.microservice.outdto.UserOutDTO;
import com.nt.user.microservice.outdto.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
  String registerUser(UserInDTO userInDTO);
  UserOutDTO loginUser(String email, String password);
  UserOutDTO getUserProfile(Integer id);
  UserResponse updateUserProfile(Integer id, UserInDTO userInDTO);
  void deleteUser(Integer id);
}

