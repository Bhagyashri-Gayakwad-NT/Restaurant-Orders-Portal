package com.nt.user.microservice.serviceimpl;

import com.nt.user.microservice.entites.Address;
import com.nt.user.microservice.entites.User;
import com.nt.user.microservice.exceptions.ResourceNotFoundException;
import com.nt.user.microservice.dto.AddressInDTO;
import com.nt.user.microservice.dto.AddressOutDTO;
import com.nt.user.microservice.dto.UserResponse;
import com.nt.user.microservice.repository.AddressRepository;
import com.nt.user.microservice.repository.UserRepository;
import com.nt.user.microservice.service.AddressService;
import com.nt.user.microservice.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of the AddressService interface that provides functionalities
 * related to managing user addresses such as adding, retrieving, and deleting addresses.
 */
@Service
public class AddressServiceImpl implements AddressService {
  /**
   * Logger for the AddressServiceImpl class, used for logging important information
   * such as operations performed, success messages, and any issues encountered during address management.
   * <p>
   * The {@link Logger} instance helps in tracking the execution flow, capturing detailed logs
   * for debugging, and auditing purposes, including logging operations like adding, retrieving,
   * and deleting addresses.
   * </p>
   */
  private static final Logger logger = LogManager.getLogger(AddressServiceImpl.class);

  /**
   * Repository for performing CRUD operations on {@link Address} entities.
   * <p>
   * The {@link AddressRepository} is used to interact with the database for address-related data operations.
   * It provides methods for saving, retrieving, updating, and deleting address records.
   * </p>
   */
  @Autowired
  private AddressRepository addressRepository;

  /**
   * Repository for performing CRUD operations on {@link User} entities.
   * <p>
   * The {@link UserRepository} is used to interact with the database for user-related data operations.
   * It provides methods for finding users by their IDs, among other user-related queries.
   * </p>
   */
  @Autowired
  private UserRepository userRepository;


  /**
   * Adds a new address for a user.
   *
   * @param addressInDTO the address details to be added.
   * @return a response indicating the success or failure of the operation.
   * @throws ResourceNotFoundException if the user with the specified ID is not found.
   */
  @Override
  public UserResponse addAddress(AddressInDTO addressInDTO) {
    logger.info("Adding a new address for userId: {}", addressInDTO.getUserId());

    // Check if the user exists
    Optional<User> userOptional = userRepository.findById(addressInDTO.getUserId());
    if (!userOptional.isPresent()) {
      logger.error("User with ID {} not found", addressInDTO.getUserId());
      throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);
    }

    // Create and populate the Address entity
    Address address = new Address();
    address.setStreet(addressInDTO.getStreet().trim());
    address.setCity(addressInDTO.getCity().trim());
    address.setCountry(addressInDTO.getCountry().trim());
    address.setState(addressInDTO.getState().trim());
    address.setPinCode(addressInDTO.getPinCode().trim());
    address.setUserId(addressInDTO.getUserId());

    addressRepository.save(address);
    logger.info("Address saved successfully for UserID: {}", addressInDTO.getUserId());

    UserResponse response = new UserResponse();
    response.setSuccessMessage(Constants.ADDRESS_ADDED_SUCCESSFULLY);
    return response;
  }

  /**
   * Retrieves all addresses associated with a given user.
   *
   * @param userId the ID of the user whose addresses are to be retrieved.
   * @return a list of AddressOutDTO objects representing the user's addresses.
   * @throws ResourceNotFoundException if the user with the specified ID is not found.
   * @throws ResourceNotFoundException if no addresses are found for the specified user.
   */
  @Override
  public List<AddressOutDTO> getUserAddresses(Integer userId) {
    logger.info("Fetching addresses for userId: {}", userId);
    Optional<User> optionalUser = userRepository.findById(userId);
    if (!optionalUser.isPresent()) {
      logger.error("User with ID {} not found", userId);
      throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);
    }
    List<Address> addresses = addressRepository.findAllByUserId(userId);
    if (addresses.isEmpty()) {
      logger.error("No addresses found for userId: {}", userId);
      throw new ResourceNotFoundException(Constants.ADDRESS_NOT_FOUND);
    }
    logger.info("Found {} addresses for userId: {}", addresses.size(), userId);
    List<AddressOutDTO> addressOutDTOs = new ArrayList<>();
    for (Address address : addresses) {
      AddressOutDTO addressOutDTO = new AddressOutDTO();
      addressOutDTO.setId(address.getId());
      addressOutDTO.setStreet(address.getStreet());
      addressOutDTO.setCity(address.getCity());
      addressOutDTO.setCountry(address.getCountry());
      addressOutDTO.setState(address.getState());
      addressOutDTO.setPinCode(address.getPinCode());
      addressOutDTOs.add(addressOutDTO);
    }
    return addressOutDTOs;
  }

  /**
   * Deletes an address by its ID.
   *
   * @param id the ID of the address to be deleted.
   * @throws ResourceNotFoundException if the address with the specified ID is not found.
   */
  @Override
  public void deleteAddress(Integer id) {
    logger.info("Attempting to delete address with ID: {}", id);
    if (addressRepository.existsById(id)) {
      addressRepository.deleteById(id);
      logger.info("Address deleted successfully with ID: {}", id);
    } else {
      logger.warn("Address with ID: {} not found, cannot delete", id);
      throw new ResourceNotFoundException(Constants.USER_NOT_FOUND);
    }
  }
}
