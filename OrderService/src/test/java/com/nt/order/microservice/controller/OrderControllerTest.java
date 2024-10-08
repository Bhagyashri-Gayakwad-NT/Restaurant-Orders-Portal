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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    orderInDTO = new OrderInDTO();
    orderInDTO.setUserId(1);
    orderInDTO.setRestaurantId(2);
    orderInDTO.setAddressId(3);

    CartItemDTO cartItem = new CartItemDTO();
    cartItem.setFoodItemId(100);
    cartItem.setPrice(50.0);
    cartItem.setQuantity(2);
    orderInDTO.setCartItems(Collections.singletonList(cartItem));

    orderOutDTO = new OrderOutDTO();
    orderOutDTO.setOrderStatus(OrderStatus.PLACED);

    commonResponse = new CommonResponse();
    commonResponse.setMessage("Success");
  }

  @Test
  public void testPlaceOrder_Success() throws Exception {

    when(orderService.placeOrder(orderInDTO)).thenReturn(commonResponse);

    mockMvc.perform(post("/orders/place")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(orderInDTO)))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.message").value("Success"));
  }


  @Test
  public void testCancelOrder_Success() throws Exception {

    when(orderService.cancelOrder(anyInt())).thenReturn(commonResponse);

    mockMvc.perform(delete("/orders/cancel/1")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.message").value("Success"));
  }

  @Test
  public void testMarkOrderAsCompleted_Success() throws Exception {

    when(orderService.markOrderAsCompleted(anyInt(), anyInt())).thenReturn(commonResponse);

    mockMvc.perform(post("/orders/complete/1/user/1")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.message").value("Success"));
  }

  @Test
  public void testGetOrdersByUserId_Success() throws Exception {
    List<OrderOutDTO> orders = Arrays.asList(orderOutDTO);
    when(orderService.getOrdersByUserId(anyInt())).thenReturn(orders);

    mockMvc.perform(get("/orders/user/1")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(1))
      .andExpect(jsonPath("$[0].orderStatus").value("PLACED"));
  }

  @Test
  public void testGetOrdersByRestaurantId_Success() throws Exception {
    List<OrderOutDTO> orders = Arrays.asList(orderOutDTO);
    when(orderService.getOrdersByRestaurantId(anyInt())).thenReturn(orders);

    mockMvc.perform(get("/orders/restaurant/2")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(1))
      .andExpect(jsonPath("$[0].orderStatus").value("PLACED"));
  }
}
