package com.nt.order.microservice.service;

import com.nt.order.microservice.dtos.CommonResponse;
import com.nt.order.microservice.dtos.OrderInDTO;
import com.nt.order.microservice.dtos.OrderOutDTO;

import java.util.List;

public interface OrderService {
  CommonResponse placeOrder(OrderInDTO orderInDTO);
  CommonResponse cancelOrder(Integer orderId);
  CommonResponse markOrderAsCompleted(Integer orderId, Integer userId);
  List<OrderOutDTO> getOrdersByUserId(Integer userId);
  List<OrderOutDTO> getOrdersByRestaurantId(Integer restaurantId);

}
