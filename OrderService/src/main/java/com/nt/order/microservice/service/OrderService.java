package com.nt.order.microservice.service;

import com.nt.order.microservice.dtos.CommonResponse;
import com.nt.order.microservice.dtos.OrderInDTO;
import com.nt.order.microservice.dtos.OrderOutDTO;

import java.util.List;

/**
 * Service interface for handling order-related operations.
 * <p>
 * This service defines methods for placing, cancelling, and completing orders,
 * as well as retrieving orders based on user or restaurant.
 * </p>
 */
public interface OrderService {

  /**
   * Places a new order.
   *
   * @param orderInDTO the order data transfer object containing the order details
   * @return {@link CommonResponse} indicating the result of the order placement
   */
  CommonResponse placeOrder(OrderInDTO orderInDTO);

  /**
   * Cancels an existing order by its ID.
   *
   * @param orderId the ID of the order to be cancelled
   * @return {@link CommonResponse} indicating the result of the order cancellation
   */
  CommonResponse cancelOrder(Integer orderId);

  /**
   * Marks an order as completed based on the order ID and the user ID.
   *
   * @param orderId the ID of the order to be marked as completed
   * @param userId  the ID of the user completing the order
   * @return {@link CommonResponse} indicating the result of the operation
   */
  CommonResponse markOrderAsCompleted(Integer orderId, Integer userId);

  /**
   * Retrieves a list of orders placed by a specific user.
   *
   * @param userId the ID of the user whose orders are being retrieved
   * @return a list of {@link OrderOutDTO} representing the orders of the user
   */
  List<OrderOutDTO> getOrdersByUserId(Integer userId);

  /**
   * Retrieves a list of orders placed at a specific restaurant.
   *
   * @param restaurantId the ID of the restaurant whose orders are being retrieved
   * @return a list of {@link OrderOutDTO} representing the orders for the restaurant
   */
  List<OrderOutDTO> getOrdersByRestaurantId(Integer restaurantId);
}
