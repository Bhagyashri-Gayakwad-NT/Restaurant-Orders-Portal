package com.nt.user.microservice.contoller;

import com.nt.user.microservice.indto.AddressInDTO;
import com.nt.user.microservice.outdto.AddressOutDTO;
import com.nt.user.microservice.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {
  private final AddressService addressService;

  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  @PostMapping
  public ResponseEntity<AddressOutDTO> addAddress(@RequestBody AddressInDTO addressInDTO) {
    AddressOutDTO addressOutDTO = addressService.addAddress(addressInDTO);
    return new ResponseEntity<>(addressOutDTO, HttpStatus.CREATED);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<AddressOutDTO>> getUserAddresses(@PathVariable Integer userId) {
    List<AddressOutDTO> addresses = addressService.getUserAddresses(userId);
    return new ResponseEntity<>(addresses, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAddress(@PathVariable Integer id) {
    addressService.deleteAddress(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
