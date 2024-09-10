package com.nt.user.microservice.service;

import com.nt.user.microservice.dto.AddressInDTO;
import com.nt.user.microservice.dto.AddressOutDTO;
import com.nt.user.microservice.dto.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing address-related operations.
 * Provides methods for adding, retrieving, and deleting addresses.
 */
@Service
public interface AddressService {

  /**
   * Adds a new address for a user.
   *
   * @param addressInDTO the data transfer object containing address details to be added
   * @return a response containing the status of the address addition operation
   */
  UserResponse addAddress(AddressInDTO addressInDTO);

  /**
   * Retrieves all addresses associated with a specific user.
   *
   * @param userId the ID of the user whose addresses are to be retrieved
   * @return a list of data transfer objects containing address details for the specified user
   */
  List<AddressOutDTO> getUserAddresses(Integer userId);

  /**
   * Deletes an address by its ID.
   *
   * @param id the ID of the address to be deleted
   */
  void deleteAddress(Integer id);
}
