package com.nt.order.microservice.controller;

import com.nt.order.microservice.dtos.CommonResponse;
import com.nt.order.microservice.dtos.OrderInDTO;
import com.nt.order.microservice.dtos.OrderOutDTO;
import com.nt.order.microservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller for handling order-related operations.
 */
@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {


  /**
   * LOGGER for the OrderController class, used to log important information like incoming requests,
   * processed responses, and any potential issues encountered during order-related operations.
   * <p>
   * The {@link Logger} instance is used for debugging, tracking execution flow, and monitoring the
   * behavior of the application in various environments.
   * </p>
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);  // SLF4J logger

  /**
   * Service layer responsible for handling order-related operations such as placing, updating,
   * retrieving, and deleting orders. The {@link OrderService} provides the necessary business logic
   * to interact with the database and manage the order lifecycle for users.
   */
  @Autowired
  private OrderService orderService;

  /**
   * Places an order.
   *
   * @param orderInDTO The order input data.
   * @return A response indicating the status of the order placement.
   */
  @PostMapping("/place")
  public ResponseEntity<CommonResponse> placeOrder(@Valid @RequestBody final OrderInDTO orderInDTO) {
    LOGGER.info("Received request to place order: {}", orderInDTO);
    final CommonResponse response = orderService.placeOrder(orderInDTO);
    LOGGER.info("Order placed successfully: {}", response);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Cancels an order by order ID.
   *
   * @param orderId The ID of the order to be canceled.
   * @return A response indicating the status of the order cancellation.
   */
  @DeleteMapping("/cancel/{orderId}")
  public ResponseEntity<CommonResponse> cancelOrder(@Valid @PathVariable final Integer orderId) {
    LOGGER.info("Received request to cancel order with orderId: {}", orderId);
    final CommonResponse response = orderService.cancelOrder(orderId);
    LOGGER.info("Order cancelled for orderId: {}, response: {}", orderId, response);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Marks an order as completed.
   *
   * @param orderId The ID of the order to be marked as completed.
   * @param userId  The ID of the user completing the order.
   * @return A response indicating the status of marking the order as completed.
   */
  @PostMapping("/complete/{orderId}/user/{userId}")
  public ResponseEntity<CommonResponse> markOrderAsCompleted(@PathVariable final Integer orderId,
                                                             @PathVariable final Integer userId) {
    LOGGER.info("Received request to mark order as completed for orderId: {} and userId: {}", orderId, userId);
    final CommonResponse response = orderService.markOrderAsCompleted(orderId, userId);
    LOGGER.info("Order marked as completed for orderId: {}, userId: {}, response: {}", orderId, userId, response);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Retrieves all orders placed by a specific user.
   *
   * @param userId The ID of the user.
   * @return A list of orders placed by the user.
   */
  @GetMapping("/user/{userId}")
  public ResponseEntity<List<OrderOutDTO>> getOrdersByUserId(@PathVariable final Integer userId) {
    LOGGER.info("Fetching orders for userId: {}", userId);
    final List<OrderOutDTO> orders = orderService.getOrdersByUserId(userId);
    LOGGER.info("Fetched orders for userId: {}: {}", userId, orders);
    return new ResponseEntity<>(orders, HttpStatus.OK);
  }

  /**
   * Retrieves all orders placed at a specific restaurant.
   *
   * @param restaurantId The ID of the restaurant.
   * @return A list of orders placed at the restaurant.
   */
  @GetMapping("/restaurant/{restaurantId}")
  public ResponseEntity<List<OrderOutDTO>> getOrdersByRestaurantId(@PathVariable final Integer restaurantId) {
    LOGGER.info("Fetching orders for restaurantId: {}", restaurantId);
    final List<OrderOutDTO> orders = orderService.getOrdersByRestaurantId(restaurantId);
    LOGGER.info("Fetched orders for restaurantId: {}: {}", restaurantId, orders);
    return new ResponseEntity<>(orders, HttpStatus.OK);
  }
}

