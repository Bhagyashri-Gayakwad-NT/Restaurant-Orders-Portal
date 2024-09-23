package com.nt.order.microservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.order.microservice.dtos.CartItemDTO;
import com.nt.order.microservice.dtos.CommonResponse;
import com.nt.order.microservice.dtos.OrderInDTO;
import com.nt.order.microservice.dtos.OrderOutDTO;
import com.nt.order.microservice.service.OrderService;
import com.nt.order.microservice.util.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OrderControllerTest {

  private MockMvc mockMvc;

  @Mock
  private OrderService orderService;

  @InjectMocks
  private OrderController orderController;

  private ObjectMapper objectMapper = new ObjectMapper();

  private OrderInDTO orderInDTO;
  private OrderOutDTO orderOutDTO;
  private CommonResponse commonResponse;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();

    // Initialize DTOs with valid data
    orderInDTO = new OrderInDTO();
    orderInDTO.setUserId(1);
    orderInDTO.setRestaurantId(2);
    orderInDTO.setAddressId(3);

    CartItemDTO cartItem = new CartItemDTO();
    cartItem.setFoodItemId(100);  // Valid food item ID
    cartItem.setPrice(50.0);      // Valid price
    cartItem.setQuantity(2);      // Valid quantity
    orderInDTO.setCartItems(Collections.singletonList(cartItem));

    orderOutDTO = new OrderOutDTO();
    orderOutDTO.setOrderStatus(OrderStatus.PLACED);

    // Initialize commonResponse
    commonResponse = new CommonResponse();
    commonResponse.setMessage("Success");
  }

  @Test
  public void testPlaceOrder_Success() throws Exception {
    // Mock the service to return a successful response
    when(orderService.placeOrder(any(OrderInDTO.class))).thenReturn(commonResponse);

    // Perform the mock request with valid data
    mockMvc.perform(post("/orders/place")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(orderInDTO)))  // Convert DTO to JSON
      .andExpect(status().isOk())  // Expecting 200 OK status
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.message").value("Success"));  // Checking message instead of orderStatus
  }


  @Test
  public void testCancelOrder_Success() throws Exception {
    // Mocking the service to return commonResponse
    when(orderService.cancelOrder(anyInt())).thenReturn(commonResponse);

    // Adjust the test to only check for "message" field
    mockMvc.perform(delete("/orders/cancel/1")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.message").value("Success"));  // Check for the message field
  }

  @Test
  public void testMarkOrderAsCompleted_Success() throws Exception {
    // Mocking the service to return the initialized commonResponse
    when(orderService.markOrderAsCompleted(anyInt(), anyInt())).thenReturn(commonResponse);

    // Adjust the test to only check for "message" field
    mockMvc.perform(post("/orders/complete/1/user/1")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.message").value("Success"));  // Check for the message field
  }


  @Test
  public void testGetOrdersByUserId_Success() throws Exception {
    // Mocking the service
    List<OrderOutDTO> orders = Arrays.asList(orderOutDTO);
    when(orderService.getOrdersByUserId(anyInt())).thenReturn(orders);

    // Performing request and asserting results
    mockMvc.perform(get("/orders/user/1")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(1))
      .andExpect(jsonPath("$[0].orderStatus").value("PLACED"));
  }

  @Test
  public void testGetOrdersByRestaurantId_Success() throws Exception {
    // Mocking the service
    List<OrderOutDTO> orders = Arrays.asList(orderOutDTO);
    when(orderService.getOrdersByRestaurantId(anyInt())).thenReturn(orders);

    // Performing request and asserting results
    mockMvc.perform(get("/orders/restaurant/2")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(1))
      .andExpect(jsonPath("$[0].orderStatus").value("PLACED"));
  }
}
