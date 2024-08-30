package com.nt.user.microservice.service.impl;

import com.nt.user.microservice.entites.Address;
import com.nt.user.microservice.indto.AddressInDTO;
import com.nt.user.microservice.outdto.AddressOutDTO;
import com.nt.user.microservice.repository.AddressRepository;
import com.nt.user.microservice.service.AddressService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class AddressServiceImpl implements AddressService {
  private static final Logger logger = LogManager.getLogger(AddressServiceImpl.class);

  private final AddressRepository addressRepository;

  public AddressServiceImpl(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  @Override
  public AddressOutDTO addAddress(AddressInDTO addressInDTO) {
    logger.info("Adding a new address for userId: {}", addressInDTO.getUserId());

    Address address = new Address();
    address.setStreet(addressInDTO.getStreet());
    address.setCity(addressInDTO.getCity());
    address.setCountry(addressInDTO.getCountry());
    address.setState(addressInDTO.getState());
    address.setPinCode(addressInDTO.getPinCode());
    address.setUserId(addressInDTO.getUserId());

    Address savedAddress = addressRepository.save(address);
    logger.info("Address saved with UserID: {}", addressInDTO.getUserId());

    AddressOutDTO addressOutDTO = new AddressOutDTO();
    addressOutDTO.setId(savedAddress.getId());
    addressOutDTO.setStreet(savedAddress.getStreet());
    addressOutDTO.setCity(savedAddress.getCity());
    addressOutDTO.setCountry(savedAddress.getCountry());
    addressOutDTO.setState(savedAddress.getState());
    addressOutDTO.setPinCode(savedAddress.getPinCode());

    return addressOutDTO;
  }

  @Override
  public List<AddressOutDTO> getUserAddresses(Integer userId) {
    logger.info("Fetching addresses for userId: {}", userId);

    List<Address> addresses = addressRepository.findByUserId(userId);
    logger.info("Found {} addresses for userId: {}", addresses.size(), userId);

    return addresses.stream().map(address -> {
      AddressOutDTO addressOutDTO = new AddressOutDTO();
      addressOutDTO.setId(address.getId());
      addressOutDTO.setStreet(address.getStreet());
      addressOutDTO.setCity(address.getCity());
      addressOutDTO.setCountry(address.getCountry());
      addressOutDTO.setState(address.getState());
      addressOutDTO.setPinCode(address.getPinCode());
      return addressOutDTO;
    }).collect(Collectors.toList());
  }

  @Override
  public void deleteAddress(Integer id) {
    logger.info("Attempting to delete address with ID: {}", id);

    if (addressRepository.existsById(id)) {
      addressRepository.deleteById(id);
      logger.info("Address with ID: {} successfully deleted", id);
    } else {
      logger.warn("Address with ID: {} not found, cannot delete", id);
      throw new IllegalArgumentException("Address not found");
    }
  }
}