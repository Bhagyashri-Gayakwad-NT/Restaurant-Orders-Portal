package com.nt.restaurant.microservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestTwoController {
  @GetMapping("/checktwo")
  public String checkTwo() {
    return "testing two here";
  }
}



