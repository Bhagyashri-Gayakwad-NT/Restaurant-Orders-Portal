package com.nt.user.microservice.outdto;

import com.nt.user.microservice.dto.UserOutDTO;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class UserOutDTOTests {

  @Test
  void testGettersAndSetters() {
    UserOutDTO userOutDTO = new UserOutDTO();

    userOutDTO.setId(123);
    assertThat(userOutDTO.getId()).isEqualTo(123);

    userOutDTO.setFirstName("TestFirstName");
    assertThat(userOutDTO.getFirstName()).isEqualTo("TestFirstName");

    userOutDTO.setLastName("TestLastName");
    assertThat(userOutDTO.getLastName()).isEqualTo("TestLastName");

    userOutDTO.setEmail("test@test.com");
    assertThat(userOutDTO.getEmail()).isEqualTo("test@test.com");

    userOutDTO.setPhoneNo("1234567890");
    assertThat(userOutDTO.getPhoneNo()).isEqualTo("1234567890");

    userOutDTO.setRole("USER");
    assertThat(userOutDTO.getRole()).isEqualTo("USER");

    userOutDTO.setWalletBalance(1000.0);
    assertThat(userOutDTO.getWalletBalance()).isEqualTo(1000.0);
  }

  @Test
  void testEqualsAndHashCode() {
    UserOutDTO user1 = new UserOutDTO();
    UserOutDTO user2 = new UserOutDTO();

    user1.setId(123);
    user1.setFirstName("TestFirstName");
    user1.setLastName("TestLastName");
    user1.setEmail("test@test.com");
    user1.setPhoneNo("1234567890");
    user1.setRole("USER");
    user1.setWalletBalance(1000.0);

    user2.setId(123);
    user2.setFirstName("TestFirstName");
    user2.setLastName("TestLastName");
    user2.setEmail("test@test.com");
    user2.setPhoneNo("1234567890");
    user2.setRole("USER");
    user2.setWalletBalance(1000.0);

    assertThat(user1).isEqualTo(user2);

    assertThat(user1.hashCode()).isEqualTo(user2.hashCode());
  }

  @Test
  void testNotEquals() {
    UserOutDTO user1 = new UserOutDTO();
    UserOutDTO user2 = new UserOutDTO();

    user1.setId(123);
    user1.setFirstName("TestFirstName");
    user1.setLastName("TestLastName");
    user1.setEmail("test@test.com");
    user1.setPhoneNo("1234567890");
    user1.setRole("USER");
    user1.setWalletBalance(1000.0);

    user2.setId(124);
    user2.setFirstName("TestFirstName");
    user2.setLastName("TestLastName");
    user2.setEmail("test@test.com");
    user2.setPhoneNo("1234567890");
    user2.setRole("USER");
    user2.setWalletBalance(1000.0);

    assertThat(user1).isNotEqualTo(user2);
  }

  @Test
  void testToString() {
    UserOutDTO userOutDTO = new UserOutDTO();
    userOutDTO.setId(123);
    userOutDTO.setFirstName("TestFirstName");
    userOutDTO.setLastName("TestLastName");
    userOutDTO.setEmail("test@test.com");
    userOutDTO.setPhoneNo("1234567890");
    userOutDTO.setRole("USER");
    userOutDTO.setWalletBalance(1000.0);

    assertThat(userOutDTO.toString()).isEqualTo(
      "UserOutDTO{id=123, firstName='TestFirstName', lastName='TestLastName', " +
        "email='test@test.com', phoneNo='1234567890', role='USER', walletBalance=1000.0}");
  }
}
