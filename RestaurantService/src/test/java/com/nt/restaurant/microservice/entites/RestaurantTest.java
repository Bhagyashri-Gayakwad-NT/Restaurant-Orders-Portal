package com.nt.restaurant.microservice.entites;

import com.nt.restaurant.microservice.entities.Restaurant;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RestaurantTest {

  @Test
  void testConstructorAndGetters() {
    byte[] image = {1, 2, 3};
    LocalDate registrationDate = LocalDate.of(2023, 9, 10);
    Restaurant restaurant = new Restaurant(1, 101, "Test Restaurant", "123 Test St", "1234567890",
      registrationDate, "Test description", true, image);

    assertEquals(1, restaurant.getRestaurantId());
    assertEquals(101, restaurant.getUserId());
    assertEquals("Test Restaurant", restaurant.getRestaurantName());
    assertEquals("123 Test St", restaurant.getRestaurantAddress());
    assertEquals("1234567890", restaurant.getContactNumber());
    assertEquals(registrationDate, restaurant.getRegistrationDate());
    assertEquals("Test description", restaurant.getDescription());
    assertTrue(restaurant.isOpen());
    assertArrayEquals(image, restaurant.getRestaurantImage());
  }

  @Test
  void testSetters() {
    Restaurant restaurant = new Restaurant();
    byte[] image = {4, 5, 6};
    LocalDate registrationDate = LocalDate.of(2023, 8, 15);

    restaurant.setRestaurantId(2);
    restaurant.setUserId(102);
    restaurant.setRestaurantName("New Restaurant");
    restaurant.setRestaurantAddress("456 New St");
    restaurant.setContactNumber("0987654321");
    restaurant.setRegistrationDate(registrationDate);
    restaurant.setDescription("New description");
    restaurant.setOpen(false);
    restaurant.setRestaurantImage(image);

    assertEquals(2, restaurant.getRestaurantId());
    assertEquals(102, restaurant.getUserId());
    assertEquals("New Restaurant", restaurant.getRestaurantName());
    assertEquals("456 New St", restaurant.getRestaurantAddress());
    assertEquals("0987654321", restaurant.getContactNumber());
    assertEquals(registrationDate, restaurant.getRegistrationDate());
    assertEquals("New description", restaurant.getDescription());
    assertFalse(restaurant.isOpen());
    assertArrayEquals(image, restaurant.getRestaurantImage());
  }

//  @Test
//  void testEquals() {
//    byte[] image = {1, 2, 3};
//    LocalDate registrationDate = LocalDate.of(2023, 9, 10);
//    Restaurant restaurant1 = new Restaurant(1, 101, "Test Restaurant", "123 Test St", "1234567890",
//      registrationDate, "Test description", true, image);
//    Restaurant restaurant2 = new Restaurant(1, 101, "Test Restaurant", "123 Test St", "1234567890",
//      registrationDate, "Test description", true, image);
//
//    // Assert that both restaurants are considered equal
//    assertEquals(restaurant1, restaurant2, "The restaurants should be equal");
//    // Assert that their hash codes are also equal
//    assertEquals(restaurant1.hashCode(), restaurant2.hashCode(), "The hash codes should be equal");
//  }

  @Test
  void testNotEquals() {
    byte[] image1 = {1, 2, 3};
    byte[] image2 = {4, 5, 6};
    LocalDate registrationDate = LocalDate.of(2023, 9, 10);
    Restaurant restaurant1 = new Restaurant(1, 101, "Test Restaurant", "123 Test St", "1234567890",
      registrationDate, "Test description", true, image1);
    Restaurant restaurant2 = new Restaurant(2, 102, "Different Restaurant", "456 Different St", "0987654321",
      registrationDate, "Different description", false, image2);

    assertNotEquals(restaurant1, restaurant2);
    assertNotEquals(restaurant1.hashCode(), restaurant2.hashCode());
  }

  @Test
  void testToString() {
    byte[] image = {1, 2, 3};
    LocalDate registrationDate = LocalDate.of(2023, 9, 10);
    Restaurant restaurant = new Restaurant(1, 101, "Test Restaurant", "123 Test St", "1234567890",
      registrationDate, "Test description", true, image);

    String expected = "Restaurant{" +
      "restaurantId=1, userId=101, restaurantName='Test Restaurant', " +
      "restaurantAddress='123 Test St', contactNumber='1234567890', " +
      "registrationDate=2023-09-10, description='Test description', " +
      "isOpen=true, restaurantImage=" + Arrays.toString(image) + "}";

    assertEquals(expected, restaurant.toString());
  }
}
