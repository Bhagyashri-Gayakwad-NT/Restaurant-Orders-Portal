package com.nt.order.microservice.serviceimpl;

import com.nt.order.microservice.dtos.AddressOutDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Feign client interface for interacting with the Address microservice.
 */
@FeignClient(name = "address-service", url = "http://localhost:100")
public interface AddressFClient {

  /**
   * Retrieves all addresses associated with a specific user.
   *
   * @param userId the ID of the user whose addresses are to be retrieved
   * @return a list of AddressOutDTO containing the user's addresses
   */
  @GetMapping("addresses/user/{userId}")
  List<AddressOutDTO> getUserAddresses(@PathVariable("userId") Integer userId);

}
