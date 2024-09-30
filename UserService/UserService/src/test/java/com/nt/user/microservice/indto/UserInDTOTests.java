package com.nt.user.microservice.indto;

import static org.junit.jupiter.api.Assertions.*;

import com.nt.user.microservice.dto.UserInDTO;
import org.junit.jupiter.api.Test;

public class UserInDTOTests {

  @Test
  public void testGetterAndSetter() {
    UserInDTO userInDTO = new UserInDTO();

    assertNull(userInDTO.getFirstName());
    String firstName = "John";
    userInDTO.setFirstName(firstName);
    assertEquals(firstName, userInDTO.getFirstName());

    assertNull(userInDTO.getLastName());
    String lastName = "Doe";
    userInDTO.setLastName(lastName);
    assertEquals(lastName, userInDTO.getLastName());

    assertNull(userInDTO.getEmail());
    String email = "john.doe@nucleusteq.com";
    userInDTO.setEmail(email);
    assertEquals(email, userInDTO.getEmail());

    assertNull(userInDTO.getPassword());
    String password = "Password123!";
    userInDTO.setPassword(password);
    assertEquals(password, userInDTO.getPassword());

    assertNull(userInDTO.getPhoneNo());
    String phoneNo = "9876543210";
    userInDTO.setPhoneNo(phoneNo);
    assertEquals(phoneNo, userInDTO.getPhoneNo());

    assertNull(userInDTO.getRole());
    String role = "USER";
    userInDTO.setRole(role);
    assertEquals(role, userInDTO.getRole());
  }

  @Test
  public void testToString() {
    UserInDTO userInDTO = new UserInDTO();

    String firstName = "John";
    userInDTO.setFirstName(firstName);

    String lastName = "Doe";
    userInDTO.setLastName(lastName);

    String email = "john.doe@nucleusteq.com";
    userInDTO.setEmail(email);

    String password = "Password123!";
    userInDTO.setPassword(password);

    String phoneNo = "9876543210";
    userInDTO.setPhoneNo(phoneNo);

    String role = "USER";
    userInDTO.setRole(role);

    assertEquals("UserInDTO{firstName='John', lastName='Doe', email='john.doe@nucleusteq.com', " +
      "password='Password123!', phoneNo='9876543210', role='USER'}", userInDTO.toString());
  }

  @Test
  public void testEqualsAndHashcode() {
    String firstName = "John";
    String lastName = "Doe";
    String email = "john.doe@nucleusteq.com";
    String password = "Password123!";
    String phoneNo = "9876543210";
    String role = "USER";

    UserInDTO userInDTO1 = buildUserInDTO(firstName, lastName, email, password, phoneNo, role);

    assertEquals(userInDTO1, userInDTO1);
    assertEquals(userInDTO1.hashCode(), userInDTO1.hashCode());

    assertNotEquals(userInDTO1, new Object());

    UserInDTO userInDTO2 = buildUserInDTO(firstName, lastName, email, password, phoneNo, role);
    assertEquals(userInDTO1, userInDTO2);
    assertEquals(userInDTO1.hashCode(), userInDTO2.hashCode());

    userInDTO2 = buildUserInDTO(firstName + " ", lastName, email, password, phoneNo, role);
    assertNotEquals(userInDTO1, userInDTO2);
    assertNotEquals(userInDTO1.hashCode(), userInDTO2.hashCode());

    userInDTO2 = buildUserInDTO(firstName, lastName + " ", email, password, phoneNo, role);
    assertNotEquals(userInDTO1, userInDTO2);
    assertNotEquals(userInDTO1.hashCode(), userInDTO2.hashCode());

    userInDTO2 = buildUserInDTO(firstName, lastName, email + " ", password, phoneNo, role);
    assertNotEquals(userInDTO1, userInDTO2);
    assertNotEquals(userInDTO1.hashCode(), userInDTO2.hashCode());

    userInDTO2 = buildUserInDTO(firstName, lastName, email, password + " ", phoneNo, role);
    assertNotEquals(userInDTO1, userInDTO2);
    assertNotEquals(userInDTO1.hashCode(), userInDTO2.hashCode());

    userInDTO2 = buildUserInDTO(firstName, lastName, email, password, phoneNo + " ", role);
    assertNotEquals(userInDTO1, userInDTO2);
    assertNotEquals(userInDTO1.hashCode(), userInDTO2.hashCode());

    userInDTO2 = buildUserInDTO(firstName, lastName, email, password, phoneNo, role + " ");
    assertNotEquals(userInDTO1, userInDTO2);
    assertNotEquals(userInDTO1.hashCode(), userInDTO2.hashCode());

    userInDTO1 = new UserInDTO();
    userInDTO2 = new UserInDTO();
    assertEquals(userInDTO1, userInDTO2);
    assertEquals(userInDTO1.hashCode(), userInDTO2.hashCode());
  }

  private UserInDTO buildUserInDTO(String firstName, String lastName, String email, String password, String phoneNo, String role) {
    UserInDTO userInDTO = new UserInDTO();

    userInDTO.setFirstName(firstName);
    userInDTO.setLastName(lastName);
    userInDTO.setEmail(email);
    userInDTO.setPassword(password);
    userInDTO.setPhoneNo(phoneNo);
    userInDTO.setRole(role);

    return userInDTO;
  }
}
