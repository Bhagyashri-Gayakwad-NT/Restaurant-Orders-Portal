package com.nt.restaurant.microservice.serviceimpl;


import com.nt.restaurant.microservice.outdto.UserOutDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:100")
public interface UserFClient {
  @GetMapping("/users/profile/{id}")
  public UserOutDTO getUserProfile(@PathVariable Integer id);

}