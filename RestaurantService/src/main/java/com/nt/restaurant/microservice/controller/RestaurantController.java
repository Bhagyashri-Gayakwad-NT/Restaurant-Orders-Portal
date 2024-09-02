package com.nt.restaurant.microservice.controller;

import com.nt.restaurant.microservice.indto.RestaurantInDTO;
import com.nt.restaurant.microservice.outdto.RestaurantOutDTO;
import com.nt.restaurant.microservice.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/restaurant")
public class RestaurantController {
  @Autowired
  private RestaurantService restaurantService;

  @PostMapping(value = "/addRestaurant", consumes = "multipart/form-data")
  public ResponseEntity<RestaurantOutDTO> addRestaurant(@Valid @ModelAttribute RestaurantInDTO restaurantInDTO){
    System.err.println(restaurantInDTO);
    RestaurantOutDTO restaurantOutDTO = restaurantService.addRestaurant(restaurantInDTO);
    return new ResponseEntity<>(restaurantOutDTO, HttpStatus.CREATED);
  }
@GetMapping("/getRestaurant/{restaurantId}")
public ResponseEntity<RestaurantOutDTO> getRestaurantById(@PathVariable Integer restaurantId) {
  RestaurantOutDTO restaurantOutDTO = restaurantService.getRestaurantById(restaurantId);
  if (restaurantOutDTO != null) {
    return ResponseEntity.ok(restaurantOutDTO);
  } else {
    return ResponseEntity.notFound().build();
  }
}
}
