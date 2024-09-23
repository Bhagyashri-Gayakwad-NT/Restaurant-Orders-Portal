package com.nt.user.microservice.contoller;

import com.nt.user.microservice.dto.AddressInDTO;
import com.nt.user.microservice.dto.AddressOutDTO;
import com.nt.user.microservice.dto.UserResponse;
import com.nt.user.microservice.service.AddressService;
import com.nt.user.microservice.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
/**
 * Controller for managing address-related operations such as adding, fetching, and deleting addresses.
 */
@RestController
@RequestMapping("/addresses")
@CrossOrigin
public class AddressController {
  /**
   * Logger for this class, used to log important information, request details, and any issues encountered.
   * <p>
   * The {@link Logger} instance allows tracking of incoming requests, responses, and events such as
   * adding, fetching, or deleting addresses. It helps monitor the application flow and provides
   * detailed logs for debugging and auditing purposes.
   */
  private static final Logger logger = LogManager.getLogger(AddressController.class);
  /**
   * Service layer to handle address-related business logic.
   * <p>
   * The {@link AddressService} is injected automatically by Spring using {@code @Autowired},
   * and it is responsible for performing various operations such as adding, fetching,
   * and deleting addresses. It communicates with the data layer (repository) and ensures
   * proper validation and processing of address data.
   * </p>
   */
  @Autowired
  private AddressService addressService;

  /**
   * Adds a new address for a user.
   *
   * @param addressInDTO the address information to add.
   * @return a response entity with a success message if the address is added successfully.
   */
  @PostMapping("/add")
  public ResponseEntity<UserResponse> addAddress(@Valid @RequestBody AddressInDTO addressInDTO) {
    logger.info("Request received to add address for user ID: {}", addressInDTO.getUserId());
    UserResponse userResponse = addressService.addAddress(addressInDTO);
    logger.info("Address added successfully for user ID: {}", addressInDTO.getUserId());
    return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
  }

  /**
   * Fetches all addresses for a user by their ID.
   *
   * @param userId the ID of the user whose addresses are to be fetched.
   * @return a response entity with the list of addresses for the user.
   */
  @GetMapping("/user/{userId}")
  public ResponseEntity<List<AddressOutDTO>> getUserAddresses(@PathVariable Integer userId) {
    logger.info("Request received to fetch addresses for user ID: {}", userId);
    List<AddressOutDTO> addresses = addressService.getUserAddresses(userId);
    logger.info("Addresses fetched successfully for user ID: {}", userId);
    return new ResponseEntity<List<AddressOutDTO>>(addresses, HttpStatus.OK);
  }

  /**
   * Deletes an address by its ID.
   *
   * @param id the ID of the address to be deleted.
   * @return a response entity with a success message if the address is deleted successfully.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<UserResponse> deleteAddress(@PathVariable Integer id) {
    logger.info("Request received to delete address with ID: {}", id);
    addressService.deleteAddress(id);
    logger.info("Address deleted successfully with ID: {}", id);
    UserResponse response = new UserResponse();
    response.setSuccessMessage(Constants.ADDRESS_ADDED_SUCCESSFULLY); // Assuming a constant for successful deletion
    return new ResponseEntity<UserResponse>(response, HttpStatus.OK);
  }
}