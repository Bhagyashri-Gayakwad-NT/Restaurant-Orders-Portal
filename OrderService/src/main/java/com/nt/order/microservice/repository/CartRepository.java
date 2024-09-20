package com.nt.order.microservice.repository;

import com.nt.order.microservice.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
  List<Cart> findByUserId(Integer userId);
  List<Cart> findByUserIdAndRestaurantId(Integer userId, Integer restaurantId);
  void deleteByUserId(Integer userId);

}
