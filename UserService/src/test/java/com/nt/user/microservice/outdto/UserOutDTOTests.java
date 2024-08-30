package com.nt.user.microservice.outdto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserOutDTOTests {

  @Test
  public void testGettersAndSetters() {
    UserOutDTO userOutDTO = new UserOutDTO();
    Integer expectedId = 1;
    String expectedFirstName = "John";
    String expectedLastName = "Doe";
    String expectedEmail = "john.doe@example.com";
    String expectedPhoneNo = "1234567890";
    String expectedRole = "USER";
    Double expectedWalletBalance = 1000.0;

    userOutDTO.setId(expectedId);
    userOutDTO.setFirstName(expectedFirstName);
    userOutDTO.setLastName(expectedLastName);
    userOutDTO.setEmail(expectedEmail);
    userOutDTO.setPhoneNo(expectedPhoneNo);
    userOutDTO.setRole(expectedRole);
    userOutDTO.setWalletBalance(expectedWalletBalance);

    assertEquals(expectedId, userOutDTO.getId());
    assertEquals(expectedFirstName, userOutDTO.getFirstName());
    assertEquals(expectedLastName, userOutDTO.getLastName());
    assertEquals(expectedEmail, userOutDTO.getEmail());
    assertEquals(expectedPhoneNo, userOutDTO.getPhoneNo());
    assertEquals(expectedRole, userOutDTO.getRole());
    assertEquals(expectedWalletBalance, userOutDTO.getWalletBalance());
  }

  @Test
  public void testSettersAndGettersWithDifferentValues() {
    // Arrange
    UserOutDTO userOutDTO = new UserOutDTO();
    Integer id = 2;
    String firstName = "Jane";
    String lastName = "Smith";
    String email = "jane.smith@example.com";
    String phoneNo = "0987654321";
    String role = "RESTAURANT_OWNER";
    Double walletBalance = 500.0;

    userOutDTO.setId(id);
    userOutDTO.setFirstName(firstName);
    userOutDTO.setLastName(lastName);
    userOutDTO.setEmail(email);
    userOutDTO.setPhoneNo(phoneNo);
    userOutDTO.setRole(role);
    userOutDTO.setWalletBalance(walletBalance);

    assertEquals(id, userOutDTO.getId());
    assertEquals(firstName, userOutDTO.getFirstName());
    assertEquals(lastName, userOutDTO.getLastName());
    assertEquals(email, userOutDTO.getEmail());
    assertEquals(phoneNo, userOutDTO.getPhoneNo());
    assertEquals(role, userOutDTO.getRole());
    assertEquals(walletBalance, userOutDTO.getWalletBalance());
  }

  public static class UserOutDTOTest {

    private UserOutDTO userOutDTO;

    @BeforeEach
    public void setUp() {
      userOutDTO = new UserOutDTO();
    }

    @Test
    public void testSetAndGetId() {
      Integer id = 1;
      userOutDTO.setId(id);
      assertEquals(id, userOutDTO.getId(), "The ID should be correctly set and retrieved");
    }

    @Test
    public void testSetAndGetFirstName() {
      String firstName = "John";
      userOutDTO.setFirstName(firstName);
      assertEquals(firstName, userOutDTO.getFirstName(), "The first name should be correctly set and retrieved");
    }

    @Test
    public void testSetAndGetLastName() {
      String lastName = "Doe";
      userOutDTO.setLastName(lastName);
      assertEquals(lastName, userOutDTO.getLastName(), "The last name should be correctly set and retrieved");
    }

    @Test
    public void testSetAndGetEmail() {
      String email = "john.doe@nucleusteq.com";
      userOutDTO.setEmail(email);
      assertEquals(email, userOutDTO.getEmail(), "The email should be correctly set and retrieved");
    }

    @Test
    public void testSetAndGetPhoneNo() {
      String phoneNo = "9876543210";
      userOutDTO.setPhoneNo(phoneNo);
      assertEquals(phoneNo, userOutDTO.getPhoneNo(), "The phone number should be correctly set and retrieved");
    }

    @Test
    public void testSetAndGetRole() {
      String role = "USER";
      userOutDTO.setRole(role);
      assertEquals(role, userOutDTO.getRole(), "The role should be correctly set and retrieved");
    }

    @Test
    public void testSetAndGetWalletBalance() {
      Double walletBalance = 1000.0;
      userOutDTO.setWalletBalance(walletBalance);
      assertEquals(walletBalance, userOutDTO.getWalletBalance(), "The wallet balance should be correctly set and retrieved");
    }
  }
}