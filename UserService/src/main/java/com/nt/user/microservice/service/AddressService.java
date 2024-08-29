package com.nt.user.microservice.service;

import com.nt.user.microservice.indto.AddressInDTO;
import com.nt.user.microservice.outdto.AddressOutDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AddressService {
  AddressOutDTO addAddress(AddressInDTO addressInDTO);
  List<AddressOutDTO> getUserAddresses(Integer userId);
  void deleteAddress(Integer id);
}



