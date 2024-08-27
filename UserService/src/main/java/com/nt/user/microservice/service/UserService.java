package com.nt.user.microservice.service;

import com.nt.user.microservice.indto.UserInDTO;
import com.nt.user.microservice.outdto.UserOutDTO;

public interface UserService {
  UserOutDTO registerUser(UserInDTO userInDTO);
  UserOutDTO loginUser(String email, String password);
  UserOutDTO getUserProfile(Integer id);
  void updateUserProfile(Integer id, UserInDTO userInDTO);
  void deleteUser(Integer id);
}

