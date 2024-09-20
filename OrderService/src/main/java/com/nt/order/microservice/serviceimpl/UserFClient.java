package com.nt.order.microservice.serviceimpl;

import com.nt.order.microservice.dtos.AmountInDTO;
import com.nt.order.microservice.dtos.UserOutDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "user-service", url = "http://localhost:100")
public interface UserFClient {
  @GetMapping("/users/profile/{id}")
  UserOutDTO getUserProfile(@PathVariable Integer id);

//  @GetMapping("/user/{id}")
//  UserOutDTO getUserById(@PathVariable("id") Integer id);

  @PutMapping("/users/walletBalance/{id}")
  UserOutDTO updateWalletBalance(@PathVariable("id") Integer id, AmountInDTO amount);
}
