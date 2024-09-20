package com.nt.restaurant.microservice.entites;

import com.nt.restaurant.microservice.entities.Restaurant;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantTest {

  @Test
  public void testDefaultConstructor() {
    Restaurant restaurant = new Restaurant();
    assertNull(restaurant.getRestaurantId());
    assertNull(restaurant.getUserId());
    assertNull(restaurant.getRestaurantName());
    assertNull(restaurant.getRestaurantAddress());
    assertNull(restaurant.getContactNumber());
    assertNull(restaurant.getRegistrationDate());
    assertNull(restaurant.getDescription());
    assertFalse(restaurant.isOpen());
    assertNull(restaurant.getRestaurantImage());
  }

  @Test
  public void testParameterizedConstructor() {
    LocalDate registrationDate = LocalDate.of(2024, 1, 1);
    byte[] image = new byte[]{1, 2, 3};

    Restaurant restaurant = new Restaurant(1, 1, "Test Restaurant", "123 Test St", "1234567890",
      registrationDate, "Test Description", true, image);

    assertEquals(1, restaurant.getRestaurantId());
    assertEquals(1, restaurant.getUserId());
    assertEquals("Test Restaurant", restaurant.getRestaurantName());
    assertEquals("123 Test St", restaurant.getRestaurantAddress());
    assertEquals("1234567890", restaurant.getContactNumber());
    assertEquals(registrationDate, restaurant.getRegistrationDate());
    assertEquals("Test Description", restaurant.getDescription());
    assertTrue(restaurant.isOpen());
    assertArrayEquals(image, restaurant.getRestaurantImage());
  }

  @Test
  public void testSettersAndGetters() {
    Restaurant restaurant = new Restaurant();
    restaurant.setRestaurantId(2);
    restaurant.setUserId(3);
    restaurant.setRestaurantName("Another Restaurant");
    restaurant.setRestaurantAddress("456 Another St");
    restaurant.setContactNumber("0987654321");
    restaurant.setRegistrationDate(LocalDate.of(2024, 1, 2));
    restaurant.setDescription("Another Description");
    restaurant.setOpen(false);
    restaurant.setRestaurantImage(new byte[]{4, 5, 6});

    assertEquals(2, restaurant.getRestaurantId());
    assertEquals(3, restaurant.getUserId());
    assertEquals("Another Restaurant", restaurant.getRestaurantName());
    assertEquals("456 Another St", restaurant.getRestaurantAddress());
    assertEquals("0987654321", restaurant.getContactNumber());
    assertEquals(LocalDate.of(2024, 1, 2), restaurant.getRegistrationDate());
    assertEquals("Another Description", restaurant.getDescription());
    assertFalse(restaurant.isOpen());
    assertArrayEquals(new byte[]{4, 5, 6}, restaurant.getRestaurantImage());
  }

  @Test
  public void testEquals_SameObject() {
    Restaurant restaurant = new Restaurant(1, 1, "Test", "Address", "1234567890", LocalDate.now(), "Desc", true, null);
    assertEquals(restaurant, restaurant);
  }

  @Test
  public void testEquals_DifferentObjects_SameValues() {
    LocalDate registrationDate = LocalDate.of(2024, 1, 1);
    Restaurant restaurant1 = new Restaurant(1, 1, "Test Restaurant", "123 Test St", "1234567890",
      registrationDate, "Test Description", true, new byte[]{1});
    Restaurant restaurant2 = new Restaurant(1, 1, "Test Restaurant", "123 Test St", "1234567890",
      registrationDate, "Test Description", true, new byte[]{1});
    assertEquals(restaurant1, restaurant2);
  }

  @Test
  public void testEquals_DifferentObjects_DifferentValues() {
    LocalDate registrationDate1 = LocalDate.of(2024, 1, 1);
    LocalDate registrationDate2 = LocalDate.of(2024, 1, 2);
    Restaurant restaurant1 = new Restaurant(1, 1, "Test Restaurant", "123 Test St", "1234567890",
      registrationDate1, "Test Description", true, new byte[]{1});
    Restaurant restaurant2 = new Restaurant(2, 2, "Another Restaurant", "456 Another St", "0987654321",
      registrationDate2, "Another Description", false, new byte[]{2});
    assertNotEquals(restaurant1, restaurant2);
  }

  @Test
  public void testHashCode() {
    LocalDate registrationDate = LocalDate.of(2024, 1, 1);
    Restaurant restaurant = new Restaurant(1, 1, "Test", "Address", "1234567890", registrationDate, "Desc", true, new byte[]{1});
    int expectedHashCode = restaurant.hashCode();
    assertEquals(expectedHashCode, restaurant.hashCode());
  }

  @Test
  public void testToString() {
    LocalDate registrationDate = LocalDate.of(2024, 1, 1);
    Restaurant restaurant = new Restaurant(1, 1, "Test Restaurant", "123 Test St", "1234567890",
      registrationDate, "Test Description", true, new byte[]{1});

    String expected = "Restaurant{" +
      "restaurantId=1, userId=1, restaurantName='Test Restaurant', " +
      "restaurantAddress='123 Test St', contactNumber='1234567890', " +
      "registrationDate=" + registrationDate + ", description='Test Description', " +
      "isOpen=true, restaurantImage=" + Arrays.toString(new byte[]{1}) +
      '}';
    assertEquals(expected, restaurant.toString());
  }
}
