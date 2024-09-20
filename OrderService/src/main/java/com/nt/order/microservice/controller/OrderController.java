package com.nt.order.microservice.controller;

import com.nt.order.microservice.dtos.CommonResponse;
import com.nt.order.microservice.dtos.OrderInDTO;
import com.nt.order.microservice.dtos.OrderOutDTO;
import com.nt.order.microservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {

  private static final Logger logger = LoggerFactory.getLogger(OrderController.class);  // SLF4J logger

  @Autowired
  private OrderService orderService;

  @PostMapping("/place")
  public ResponseEntity<CommonResponse> placeOrder(@Valid @RequestBody OrderInDTO orderInDTO) {
    logger.info("Received request to place order: {}", orderInDTO);
    CommonResponse response = orderService.placeOrder(orderInDTO);
    logger.info("Order placed successfully: {}", response);
    return new ResponseEntity<CommonResponse>(response, HttpStatus.OK);
  }

  @DeleteMapping("/cancel/{orderId}")
  public ResponseEntity<CommonResponse> cancelOrder(@Valid @PathVariable Integer orderId) {
    logger.info("Received request to cancel order with orderId: {}", orderId);
    CommonResponse response = orderService.cancelOrder(orderId);
    logger.info("Order cancelled for orderId: {}, response: {}", orderId, response);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/complete/{orderId}/user/{userId}")
  public ResponseEntity<CommonResponse> markOrderAsCompleted(@PathVariable Integer orderId, @PathVariable Integer userId) {
    logger.info("Received request to mark order as completed for orderId: {} and userId: {}", orderId, userId);
    CommonResponse response = orderService.markOrderAsCompleted(orderId, userId);
    logger.info("Order marked as completed for orderId: {}, userId: {}, response: {}", orderId, userId, response);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/user/{userId}")
  public ResponseEntity<List<OrderOutDTO>> getOrdersByUserId(@PathVariable Integer userId) {
    logger.info("Fetching orders for userId: {}", userId);
    List<OrderOutDTO> orders = orderService.getOrdersByUserId(userId);
    logger.info("Fetched orders for userId: {}: {}", userId, orders);
    return new ResponseEntity<>(orders, HttpStatus.OK);
  }

  @GetMapping("/restaurant/{restaurantId}")
  public ResponseEntity<List<OrderOutDTO>> getOrdersByRestaurantId(@PathVariable Integer restaurantId) {
    logger.info("Fetching orders for restaurantId: {}", restaurantId);
    List<OrderOutDTO> orders = orderService.getOrdersByRestaurantId(restaurantId);
    logger.info("Fetched orders for restaurantId: {}: {}", restaurantId, orders);
    return new ResponseEntity<>(orders, HttpStatus.OK);
  }
}
