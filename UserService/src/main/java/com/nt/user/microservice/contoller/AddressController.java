package com.nt.user.microservice.contoller;

import com.nt.user.microservice.indto.AddressInDTO;
import com.nt.user.microservice.outdto.AddressOutDTO;
import com.nt.user.microservice.service.AddressService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {

  private static final Logger logger = LogManager.getLogger(AddressController.class);

  private final AddressService addressService;

  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  @PostMapping("/add")
  public ResponseEntity<AddressOutDTO> addAddress(@Valid @RequestBody AddressInDTO addressInDTO) {
    logger.info("Adding address for user ID: {}", addressInDTO.getUserId());
    AddressOutDTO addressOutDTO = addressService.addAddress(addressInDTO);
    logger.info("Address added successfully for user ID: {}", addressInDTO.getUserId());
    return new ResponseEntity<>(addressOutDTO, HttpStatus.CREATED);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<AddressOutDTO>> getUserAddresses(@PathVariable Integer userId) {
    logger.info("Fetching addresses for user ID: {}", userId);
    List<AddressOutDTO> addresses = addressService.getUserAddresses(userId);
    logger.info("Addresses fetched successfully for user ID: {}", userId);
    return new ResponseEntity<>(addresses, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAddress(@PathVariable Integer id) {
    logger.info("Deleting address with ID: {}", id);
    addressService.deleteAddress(id);
    logger.info("Address deleted successfully with ID: {}", id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
