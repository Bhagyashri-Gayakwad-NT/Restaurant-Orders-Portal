package com.nt.order.microservice.serviceimpl;

import com.nt.order.microservice.dtos.AddressOutDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "address-service", url = "http://localhost:100")
public interface AddressFClient {

  @GetMapping("addresses/user/{userId}")
  public List<AddressOutDTO> getUserAddresses(@PathVariable("userId") Integer userId);

}
