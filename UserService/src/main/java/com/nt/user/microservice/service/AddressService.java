package com.nt.user.microservice.service;

import com.nt.user.microservice.indto.AddressInDTO;
import com.nt.user.microservice.outdto.AddressOutDTO;

import java.util.List;

public interface AddressService {
  AddressOutDTO addAddress(AddressInDTO addressInDTO);
  List<AddressOutDTO> getUserAddresses(Integer userId);
  void deleteAddress(Integer id);
}



