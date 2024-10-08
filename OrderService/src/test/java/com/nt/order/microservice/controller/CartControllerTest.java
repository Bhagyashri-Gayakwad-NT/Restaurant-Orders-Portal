package com.nt.order.microservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.order.microservice.dtos.CartInDTO;
import com.nt.order.microservice.dtos.CartOutDTO;
import com.nt.order.microservice.dtos.CommonResponse;
import com.nt.order.microservice.entities.Cart;
import com.nt.order.microservice.service.CartService;
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

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CartControllerTest {

  private MockMvc mockMvc;

  @Mock
  private CartService cartService;

  @InjectMocks
  private CartController cartController;

  private ObjectMapper objectMapper = new ObjectMapper();

  private CartInDTO cartInDTO;
  private CartOutDTO cartOutDTO;
  private CommonResponse commonResponse;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();

    cartInDTO = new CartInDTO();
    cartInDTO.setUserId(1);
    cartInDTO.setFoodItemId(100);
    cartInDTO.setQuantity(2);
    cartInDTO.setRestaurantId(5);
    cartInDTO.setPrice(20.2);

    cartOutDTO = new CartOutDTO();
    cartOutDTO.setCartId(1);
    cartOutDTO.setUserId(1);
    cartOutDTO.setFoodItemId(100);
    cartOutDTO.setQuantity(2);

    commonResponse = new CommonResponse();
    commonResponse.setMessage("Success");
  }

  @Test
  public void testAddItemToCart_Success() throws Exception {
    when(cartService.addItemToCart(cartInDTO)).thenReturn(commonResponse);

    mockMvc.perform(post("/cart/add")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(cartInDTO)))
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.message").value("Success"));
  }

  @Test
  public void testUpdateCartQuantity_Success() throws Exception {
    when(cartService.updateQuantity(anyInt(), anyInt())).thenReturn(commonResponse);

    mockMvc.perform(put("/cart/update/1")
        .param("quantityChange", "3")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.message").value("Success"));
  }

  @Test
  public void testGetCartById_Success() throws Exception {
    when(cartService.getCartById(anyInt())).thenReturn(cartOutDTO);

    mockMvc.perform(get("/cart/1")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.cartId").value(1))
      .andExpect(jsonPath("$.userId").value(1))
      .andExpect(jsonPath("$.quantity").value(2));
  }

  @Test
  public void testGetCartsByUserId_Success() throws Exception {
    List<CartOutDTO> cartList = Arrays.asList(cartOutDTO);
    when(cartService.getCartsByUserId(anyInt())).thenReturn(cartList);

    mockMvc.perform(get("/cart/user/1")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(1))
      .andExpect(jsonPath("$[0].cartId").value(1));
  }

  @Test
  public void testGetCartItemsByUserIdAndRestaurantId_Success() throws Exception {
    List<Cart> carts = Collections.singletonList(new Cart());
    when(cartService.getCartItemsByUserIdAndRestaurantId(anyInt(), anyInt())).thenReturn(carts);

    mockMvc.perform(get("/cart/user/1/restaurant/5")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.length()").value(1));
  }

  @Test
  public void testRemoveItemFromCart_Success() throws Exception {
    when(cartService.removeItemFromCart(anyInt())).thenReturn(commonResponse);

    mockMvc.perform(delete("/cart/1")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.message").value("Success"));
  }

  @Test
  public void testClearCartAfterPlaceAnOrder_Success() throws Exception {
    when(cartService.clearCartAfterPlaceAnOrder(anyInt())).thenReturn(commonResponse);

    mockMvc.perform(delete("/cart/clear/1")
        .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.message").value("Success"));
  }
}
