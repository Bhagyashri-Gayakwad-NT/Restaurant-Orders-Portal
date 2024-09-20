package com.nt.order.microservice.service;

import com.nt.order.microservice.dtos.*;
import com.nt.order.microservice.entities.Order;
import com.nt.order.microservice.exception.*;
import com.nt.order.microservice.repository.CartRepository;
import com.nt.order.microservice.repository.OrderRepository;
import com.nt.order.microservice.service.CartService;
import com.nt.order.microservice.serviceimpl.AddressFClient;
import com.nt.order.microservice.serviceimpl.OrderServiceImpl;
import com.nt.order.microservice.serviceimpl.RestaurantFClient;
import com.nt.order.microservice.serviceimpl.UserFClient;
import com.nt.order.microservice.util.Constants;
import com.nt.order.microservice.util.OrderStatus;
import com.nt.order.microservice.util.Role;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {

  @InjectMocks
  private OrderServiceImpl orderService;

  @Mock
  private OrderRepository orderRepository;

  @Mock
  private CartService cartService;

  @Mock
  private UserFClient userFClient;

  @Mock
  private RestaurantFClient restaurantFClient;

  @Mock
  private AddressFClient addressFClient;

  @Mock
  private CartRepository cartRepository;

  private OrderInDTO orderInDTO;
  private Order order;
  private UserOutDTO userOutDTO;
  private RestaurantOutDTO restaurantOutDTO;
  private AddressOutDTO addressOutDTO;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);

    orderInDTO = new OrderInDTO();
    orderInDTO.setUserId(1);
    orderInDTO.setRestaurantId(2);
    orderInDTO.setAddressId(3);

    CartItemDTO cartItem = new CartItemDTO();
    cartItem.setPrice(50.0);  // Set a valid price
    cartItem.setQuantity(2);  // Set a valid quantity

    orderInDTO.setCartItems(Collections.singletonList(cartItem));

    order = new Order();
    order.setUserId(1);
    order.setRestaurantId(2);
    order.setTotalPrice(100.0);
    order.setPlacedTiming(LocalDateTime.now());
    order.setOrderStatus(OrderStatus.PLACED);

    userOutDTO = new UserOutDTO();
    userOutDTO.setId(1);
    userOutDTO.setRole(Role.USER.name());

    restaurantOutDTO = new RestaurantOutDTO();
    restaurantOutDTO.setRestaurantId(2);

    addressOutDTO = new AddressOutDTO();
    addressOutDTO.setId(3);
  }

//
//  @Test
//  public void testPlaceOrder_Success() {
//    // Mock user profile
//    when(userFClient.getUserProfile(anyInt())).thenReturn(userOutDTO);
//    when(restaurantFClient.getRestaurantById(anyInt())).thenReturn(restaurantOutDTO);
//    when(addressFClient.getUserAddresses(anyInt())).thenReturn(Collections.singletonList(addressOutDTO));
//
//    // Mock save order
//    when(orderRepository.save(any(Order.class))).thenReturn(order);
//
//    // Call placeOrder method
//    CommonResponse result = orderService.placeOrder(orderInDTO);
//
//    // Verify result and interactions
//    assertNotNull(result);
//    assertEquals(OrderStatus.PLACED.name(), result.getMessage());
//    verify(cartRepository, times(1)).deleteByUserId(anyInt());
//    verify(userFClient, times(1)).updateWalletBalance(anyInt(), any(AmountInDTO.class));
//  }



  @Test
  public void testPlaceOrder_UserNotFound() {
    when(userFClient.getUserProfile(anyInt())).thenThrow(FeignException.NotFound.class);

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
      orderService.placeOrder(orderInDTO);
    });

    assertEquals(Constants.USER_NOT_FOUND, exception.getMessage());
    verify(orderRepository, never()).save(any(Order.class));
  }

  @Test
  public void testPlaceOrder_RestaurantNotFound() {
    when(userFClient.getUserProfile(anyInt())).thenReturn(userOutDTO);
    when(restaurantFClient.getRestaurantById(anyInt())).thenThrow(FeignException.NotFound.class);

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
      orderService.placeOrder(orderInDTO);
    });

    assertEquals(Constants.INVALID_RESTAURANT_ID, exception.getMessage());
  }

  @Test
  public void testCancelOrder_Success() {
    when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));

    CommonResponse response = orderService.cancelOrder(1);

    assertEquals(Constants.ORDER_CANCELLED_SUCCESSFULLY, response.getMessage());
    verify(userFClient, times(1)).updateWalletBalance(anyInt(), any(AmountInDTO.class));
    verify(orderRepository, times(1)).save(any(Order.class));
  }

  @Test
  public void testCancelOrder_OrderNotFound() {
    when(orderRepository.findById(anyInt())).thenReturn(Optional.empty());

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
      orderService.cancelOrder(1);
    });

    assertEquals(Constants.ORDER_NOT_FOUND, exception.getMessage());
    verify(orderRepository, never()).save(any(Order.class));
  }

  @Test
  public void testMarkOrderAsCompleted_Success() {
    when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
    userOutDTO.setRole(Role.RESTAURANT_OWNER.name());
    when(userFClient.getUserProfile(anyInt())).thenReturn(userOutDTO);

    CommonResponse response = orderService.markOrderAsCompleted(1, 1);

    assertEquals(Constants.ORDER_COMPLETED_SUCCESSFULLY, response.getMessage());
    verify(orderRepository, times(1)).save(any(Order.class));
  }

  @Test
  public void testMarkOrderAsCompleted_UnauthorizedUser() {
    when(orderRepository.findById(anyInt())).thenReturn(Optional.of(order));
    userOutDTO.setRole(Role.USER.name());
    when(userFClient.getUserProfile(anyInt())).thenReturn(userOutDTO);

    UnauthorizedException exception = assertThrows(UnauthorizedException.class, () -> {
      orderService.markOrderAsCompleted(1, 1);
    });

    assertEquals(Constants.UNAUTHORIZED_USER, exception.getMessage());
    verify(orderRepository, never()).save(any(Order.class));
  }

  @Test
  public void testGetOrdersByUserId_Success() {
    when(userFClient.getUserProfile(anyInt())).thenReturn(userOutDTO);
    when(orderRepository.findByUserId(anyInt())).thenReturn(Arrays.asList(order));

    List<OrderOutDTO> orders = orderService.getOrdersByUserId(1);

    assertNotNull(orders);
    assertEquals(1, orders.size());
    verify(orderRepository, times(1)).findByUserId(anyInt());
  }

  @Test
  public void testGetOrdersByRestaurantId_Success() {
    when(restaurantFClient.getRestaurantById(anyInt())).thenReturn(restaurantOutDTO);
    when(orderRepository.findByRestaurantId(anyInt())).thenReturn(Arrays.asList(order));

    List<OrderOutDTO> orders = orderService.getOrdersByRestaurantId(2);

    assertNotNull(orders);
    assertEquals(1, orders.size());
    verify(orderRepository, times(1)).findByRestaurantId(anyInt());
  }
}
