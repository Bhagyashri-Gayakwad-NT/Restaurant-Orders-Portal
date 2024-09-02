package com.nt.restaurant.microservice.repository;

import com.nt.restaurant.microservice.entities.Restaurant;
import com.nt.restaurant.microservice.outdto.RestaurantOutDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
  //List<Restaurant> findByRestaurantId(Integer restaurantId);
  List<Restaurant> findByUserId(Integer userId);
  Optional<Restaurant> findById(Integer restaurantId);
 // RestaurantOutDTO getRestaurantById(Integer restaurantId);
}
