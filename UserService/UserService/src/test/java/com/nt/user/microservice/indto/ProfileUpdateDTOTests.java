package com.nt.user.microservice.indto;

import static org.junit.jupiter.api.Assertions.*;

import com.nt.user.microservice.dto.ProfileUpdateDTO;
import org.junit.jupiter.api.Test;

public class ProfileUpdateDTOTests {

  @Test
  public void testGetterAndSetter() {
    ProfileUpdateDTO profileUpdateDTO = new ProfileUpdateDTO();

    assertNull(profileUpdateDTO.getFirstName());
    String firstName = "FirstNameTest";
    profileUpdateDTO.setFirstName(firstName);
    assertEquals(firstName, profileUpdateDTO.getFirstName());

    assertNull(profileUpdateDTO.getLastName());
    String lastName = "LastNameTest";
    profileUpdateDTO.setLastName(lastName);
    assertEquals(lastName, profileUpdateDTO.getLastName());

    assertNull(profileUpdateDTO.getEmail());
    String email = "test@company.com";
    profileUpdateDTO.setEmail(email);
    assertEquals(email, profileUpdateDTO.getEmail());

    assertNull(profileUpdateDTO.getPassword());
    String password = "TestPassword123!";
    profileUpdateDTO.setPassword(password);
    assertEquals(password, profileUpdateDTO.getPassword());

    assertNull(profileUpdateDTO.getPhoneNo());
    String phoneNo = "9876543210";
    profileUpdateDTO.setPhoneNo(phoneNo);
    assertEquals(phoneNo, profileUpdateDTO.getPhoneNo());
  }

  @Test
  public void testToString() {
    ProfileUpdateDTO profileUpdateDTO = new ProfileUpdateDTO();

    String firstName = "FirstNameTest";
    profileUpdateDTO.setFirstName(firstName);

    String lastName = "LastNameTest";
    profileUpdateDTO.setLastName(lastName);

    String email = "test@company.com";
    profileUpdateDTO.setEmail(email);

    String password = "TestPassword123!";
    profileUpdateDTO.setPassword(password);

    String phoneNo = "9876543210";
    profileUpdateDTO.setPhoneNo(phoneNo);

    assertEquals("ProfileUpdateDTO{firstName='FirstNameTest', lastName='LastNameTest', email='test@company.com', " +
      "password='TestPassword123!', phoneNo='9876543210'}", profileUpdateDTO.toString());
  }

  @Test
  public void testEqualsAndHashcode() {
    String firstName = "FirstNameTest";
    String lastName = "LastNameTest";
    String email = "test@company.com";
    String password = "TestPassword123!";
    String phoneNo = "9876543210";

    ProfileUpdateDTO dto1 = buildProfileUpdateDTO(firstName, lastName, email, password, phoneNo);

    assertEquals(dto1, dto1); // Equal to itself
    assertEquals(dto1.hashCode(), dto1.hashCode()); // Same hash code

    assertNotEquals(dto1, new Object()); // Should not equal a different object type

    ProfileUpdateDTO dto2 = buildProfileUpdateDTO(firstName, lastName, email, password, phoneNo);
    assertEquals(dto1, dto2); // Two DTOs with the same data should be equal
    assertEquals(dto1.hashCode(), dto2.hashCode()); // Hash codes should also be equal

    // Changing fields should cause inequality
    dto2 = buildProfileUpdateDTO(firstName + "1", lastName, email, password, phoneNo);
    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());

    dto2 = buildProfileUpdateDTO(firstName, lastName + "1", email, password, phoneNo);
    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());

    dto2 = buildProfileUpdateDTO(firstName, lastName, email + "1", password, phoneNo);
    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());

    dto2 = buildProfileUpdateDTO(firstName, lastName, email, password + "1", phoneNo);
    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());

    dto2 = buildProfileUpdateDTO(firstName, lastName, email, password, phoneNo + "1");
    assertNotEquals(dto1, dto2);
    assertNotEquals(dto1.hashCode(), dto2.hashCode());

    // Test equality with empty DTOs
    dto1 = new ProfileUpdateDTO();
    dto2 = new ProfileUpdateDTO();
    assertEquals(dto1, dto2);
    assertEquals(dto1.hashCode(), dto2.hashCode());
  }

  private ProfileUpdateDTO buildProfileUpdateDTO(String firstName, String lastName, String email, String password, String phoneNo) {
    ProfileUpdateDTO dto = new ProfileUpdateDTO();
    dto.setFirstName(firstName);
    dto.setLastName(lastName);
    dto.setEmail(email);
    dto.setPassword(password);
    dto.setPhoneNo(phoneNo);
    return dto;
  }
}
