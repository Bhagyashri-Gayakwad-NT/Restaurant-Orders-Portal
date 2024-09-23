package com.nt.restaurant.microservice.outdto;

import com.nt.restaurant.microservice.dto.UserOutDTO;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class UserOutDTOTest {

  @Test
  void testGettersAndSetters() {
    UserOutDTO userOutDTO = new UserOutDTO();

    // Set values
    userOutDTO.setId(1);
    userOutDTO.setFirstName("First");
    userOutDTO.setLastName("Last");
    userOutDTO.setEmail("test@example.com");
    userOutDTO.setPhoneNo("1234567890");
    userOutDTO.setRole("user");
    userOutDTO.setWalletBalance(500.0);

    // Assert values
    assertThat(userOutDTO.getId()).isEqualTo(1);
    assertThat(userOutDTO.getFirstName()).isEqualTo("First");
    assertThat(userOutDTO.getLastName()).isEqualTo("Last");
    assertThat(userOutDTO.getEmail()).isEqualTo("test@example.com");
    assertThat(userOutDTO.getPhoneNo()).isEqualTo("1234567890");
    assertThat(userOutDTO.getRole()).isEqualTo("user");
    assertThat(userOutDTO.getWalletBalance()).isEqualTo(500.0);
  }

  @Test
  void testEqualsAndHashCode() {
    UserOutDTO user1 = new UserOutDTO(1, "First", "Last", "test@example.com", "1234567890", "user", 500.0);
    UserOutDTO user2 = new UserOutDTO(1, "First", "Last", "test@example.com", "1234567890", "user", 500.0);

    // Assert that two identical objects are equal and have the same hash code
    assertThat(user1).isEqualTo(user2);
    assertThat(user1.hashCode()).isEqualTo(user2.hashCode());
  }

  @Test
  void testNotEquals() {
    UserOutDTO user1 = new UserOutDTO(1, "First", "Last", "test@example.com", "1234567890", "user", 500.0);
    UserOutDTO user2 = new UserOutDTO(2, "FirstName", "Last", "testname@example.com", "0987654321", "restaurant_owner", 1000.0);

    // Assert that two different objects are not equal
    assertThat(user1).isNotEqualTo(user2);
  }

  @Test
  void testToString() {
    UserOutDTO userOutDTO = new UserOutDTO(1, "First", "Last", "test@example.com", "1234567890", "user", 500.0);

    // Assert that the toString output matches the expected format
    assertThat(userOutDTO.toString()).isEqualTo(
      "UserOutDTO{id=1, firstName='First', lastName='Last', email='test@example.com', " +
        "phoneNo='1234567890', role='user', walletBalance=500.0}"
    );
  }
}
