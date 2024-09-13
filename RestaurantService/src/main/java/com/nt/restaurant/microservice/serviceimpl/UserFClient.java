package com.nt.restaurant.microservice.serviceimpl;


import com.nt.restaurant.microservice.outdto.UserOutDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Feign client interface for interacting with the User Service.
 * This interface defines methods for making HTTP requests to the user service.
 */
@FeignClient(name = "user-service", url = "http://localhost:100")
public interface UserFClient {

  /**
   * Retrieves the profile of a user by their ID.
   *
   * @param id The ID of the user whose profile is to be retrieved.
   * @return A {@link UserOutDTO} object containing the details of the user profile.
   */
  @GetMapping("/users/profile/{id}")
  UserOutDTO getUserProfile(@PathVariable Integer id);
}
