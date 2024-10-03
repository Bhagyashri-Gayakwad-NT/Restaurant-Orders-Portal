package com.nt.order.microservice.serviceimpl;

import com.nt.order.microservice.dtos.AmountInDTO;
import com.nt.order.microservice.dtos.UserOutDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign client interface for communicating with the User microservice.
 */
@FeignClient(name = "user-service", url = "http://localhost:100")
public interface UserFClient {

  /**
   * Retrieves the profile of a user by their ID.
   *
   * @param id the ID of the user to retrieve
   * @return UserOutDTO containing the user's profile information
   */
  @GetMapping("/users/profile/{id}")
  UserOutDTO getUserProfile(@PathVariable Integer id);

  /**
   * Updates the wallet balance of a user.
   *
   * @param id     the ID of the user whose wallet balance to update
   * @param amount the AmountInDTO object containing the amount to update
   * @return UserOutDTO containing the updated wallet balance
   */
  @PutMapping("/users/walletBalance/{id}")
  UserOutDTO updateWalletBalance(@PathVariable("id") Integer id, AmountInDTO amount);

  /**
   * Adds money to the user's wallet.
   *
   * @param userId      the ID of the user to whom the money is to be added
   * @param amountInDto the AmountInDTO object containing the amount to add
   * @return UserOutDTO containing the updated wallet balance after adding money
   */
  @PutMapping("/users/addMoney/{userId}")
  UserOutDTO addMoney(@PathVariable("userId") Integer userId, @RequestBody AmountInDTO amountInDto);

}
