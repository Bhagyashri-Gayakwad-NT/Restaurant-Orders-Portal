package com.nt.restaurant.microservice.entites;

import com.nt.restaurant.microservice.entities.Restaurant;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

  @Test
  void testRestaurantConstructorAndGetters() {
    Integer restaurantId = 1;
    Integer userId = 101;
    String restaurantName = "Test Restaurant";
    String restaurantAddress = "123 Test Street";
    String contactNumber = "1234567890";
    LocalDate registrationDate = LocalDate.now();
    String description = "A test restaurant";
    boolean isOpen = true;
    byte[] restaurantImage = new byte[]{1, 2, 3, 4};

    Restaurant restaurant = new Restaurant(restaurantId, userId, restaurantName, restaurantAddress, contactNumber,
      registrationDate, description, isOpen, restaurantImage);

    assertEquals(restaurantId, restaurant.getRestaurantId());
    assertEquals(userId, restaurant.getUserId());
    assertEquals(restaurantName, restaurant.getRestaurantName());
    assertEquals(restaurantAddress, restaurant.getRestaurantAddress());
    assertEquals(contactNumber, restaurant.getContactNumber());
    assertEquals(registrationDate, restaurant.getRegistrationDate());
    assertEquals(description, restaurant.getDescription());
    assertTrue(restaurant.isOpen());
    assertArrayEquals(restaurantImage, restaurant.getRestaurantImage());
  }

  @Test
  void testRestaurantSetters() {
    Restaurant restaurant = new Restaurant();

    Integer restaurantId = 2;
    Integer userId = 102;
    String restaurantName = "Updated Restaurant";
    String restaurantAddress = "456 Update Lane";
    String contactNumber = "0987654321";
    LocalDate registrationDate = LocalDate.now();
    String description = "An updated restaurant";
    boolean isOpen = false;
    byte[] restaurantImage = new byte[]{5, 6, 7, 8};

    restaurant.setRestaurantId(restaurantId);
    restaurant.setUserId(userId);
    restaurant.setRestaurantName(restaurantName);
    restaurant.setRestaurantAddress(restaurantAddress);
    restaurant.setContactNumber(contactNumber);
    restaurant.setRegistrationDate(registrationDate);
    restaurant.setDescription(description);
    restaurant.setOpen(isOpen);
    restaurant.setRestaurantImage(restaurantImage);

    assertEquals(restaurantId, restaurant.getRestaurantId());
    assertEquals(userId, restaurant.getUserId());
    assertEquals(restaurantName, restaurant.getRestaurantName());
    assertEquals(restaurantAddress, restaurant.getRestaurantAddress());
    assertEquals(contactNumber, restaurant.getContactNumber());
    assertEquals(registrationDate, restaurant.getRegistrationDate());
    assertEquals(description, restaurant.getDescription());
    assertFalse(restaurant.isOpen());
    assertArrayEquals(restaurantImage, restaurant.getRestaurantImage());
  }

  @Test
  void testRestaurantEqualsAndHashCode() {
    byte[] image1 = new byte[]{1, 2, 3};
    byte[] image2 = new byte[]{1, 2, 3};

    Restaurant restaurant1 = new Restaurant(1, 101, "Test Restaurant", "123 Test Street", "1234567890",
      LocalDate.now(), "A test restaurant", true, image1);

    Restaurant restaurant2 = new Restaurant(1, 101, "Test Restaurant", "123 Test Street", "1234567890",
      LocalDate.now(), "A test restaurant", true, image2);

    Restaurant restaurant3 = new Restaurant(2, 102, "Different Restaurant", "456 Different Avenue", "0987654321",
      LocalDate.now(), "A different restaurant", false, new byte[]{4, 5, 6});

    assertEquals(restaurant1, restaurant2);
    assertEquals(restaurant1.hashCode(), restaurant2.hashCode());

    assertNotEquals(restaurant1, restaurant3);
    assertNotEquals(restaurant1.hashCode(), restaurant3.hashCode());
  }

  @Test
  void testRestaurantToString() {
    byte[] image = new byte[]{1, 2, 3};
    Restaurant restaurant = new Restaurant(1, 101, "Test Restaurant", "123 Test Street", "1234567890",
      LocalDate.now(), "A test restaurant", true, image);

    String expectedString = "Restaurant{restaurantId=1, userId=101, restaurantName='Test Restaurant', " +
      "restaurantAddress='123 Test Street', contactNumber='1234567890', registrationDate=" + restaurant.getRegistrationDate() +
      ", description='A test restaurant', isOpen=true, restaurantImage=" + Arrays.toString(image) + "}";

    assertEquals(expectedString, restaurant.toString());
  }
}