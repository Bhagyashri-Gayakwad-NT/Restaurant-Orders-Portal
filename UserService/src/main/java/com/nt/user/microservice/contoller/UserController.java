package com.nt.user.microservice.contoller;

import com.nt.user.microservice.indto.LogInDTO;
import com.nt.user.microservice.indto.UserInDTO;
import com.nt.user.microservice.outdto.UserOutDTO;
import com.nt.user.microservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<UserOutDTO> registerUser(@Valid @RequestBody UserInDTO userInDTO) {
    UserOutDTO userOutDTO = userService.registerUser(userInDTO);
    return new ResponseEntity<>(userOutDTO, HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<UserOutDTO> loginUser(@RequestBody LogInDTO loginDTO) {
    UserOutDTO userOutDTO = userService.loginUser(loginDTO.getEmail(), loginDTO.getPassword());
    return ResponseEntity.ok(userOutDTO);
  }
  @GetMapping("/profile/{id}")
  public ResponseEntity<UserOutDTO> getUserProfile(@PathVariable Integer id) {
    UserOutDTO userOutDTO = userService.getUserProfile(id);
    return new ResponseEntity<>(userOutDTO, HttpStatus.OK);
  }
  @PutMapping("/profile/{id}")
  public ResponseEntity<Void> updateUserProfile(@PathVariable Integer id, @RequestBody UserInDTO userInDTO) {
    userService.updateUserProfile(id, userInDTO);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
    userService.deleteUser(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}



