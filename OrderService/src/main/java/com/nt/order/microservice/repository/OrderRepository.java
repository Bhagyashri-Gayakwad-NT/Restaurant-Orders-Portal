package com.nt.order.microservice.repository;

import com.nt.order.microservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
  List<Order> findByUserId(Integer userId);
  List<Order> findByRestaurantId(Integer restaurantId);
}
