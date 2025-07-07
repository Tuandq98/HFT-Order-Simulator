package com.equix.order_demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.equix.order_demo.controller.dto.req.CreateOrderRequest;
import com.equix.order_demo.exception.InvalidOrderStatusException;
import com.equix.order_demo.exception.OrderNotFoundException;
import com.equix.order_demo.model.Order;
import com.equix.order_demo.model.enums.OrderSide;
import com.equix.order_demo.model.enums.OrderStatus;
import com.equix.order_demo.repository.OrderRepository;
import com.equix.order_demo.service.OrderService;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderServiceTest {
  @Autowired
  private OrderService orderService;

  @Autowired
  private OrderRepository orderRepository;

  @BeforeEach
  void setUp() {
    orderService.clearOrdersForTest(); // Reset the order store before each test
  }

  @Test
  void testCreateOrderSuccess() {
    CreateOrderRequest request = new CreateOrderRequest("FPT", new BigDecimal("100.5"), 10, OrderSide.BUY);

    Order order = orderService.createOrder(request);

    assertNotNull(order.getId());
    assertEquals("FPT", order.getSymbol());
    assertEquals(new BigDecimal("100.5"), order.getPrice());
    assertEquals(10, order.getQuantity());
    assertEquals(OrderSide.BUY, order.getSide());
    assertEquals(OrderStatus.PENDING, order.getStatus());
  }

  @Test
  void testCancelOrderSuccess() {
    Order order = orderService.createOrder(new CreateOrderRequest("VIC", new BigDecimal("10"), 100, OrderSide.SELL));

    Order cancelled = orderService.cancelOrder(order.getId());

    assertEquals(order.getId(), cancelled.getId());
    assertEquals("VIC", cancelled.getSymbol());
    assertEquals(new BigDecimal("10"), cancelled.getPrice());
    assertEquals(100, cancelled.getQuantity());
    assertEquals(OrderSide.SELL, cancelled.getSide());
    assertEquals(OrderStatus.CANCELLED, cancelled.getStatus());
  }

  @Test
  void testCancelOrderInvalidStatus() {
    Order order = orderService.createOrder(new CreateOrderRequest("VIC", new BigDecimal("10"), 100, OrderSide.SELL));
    order.setStatus(OrderStatus.EXECUTED);
    orderRepository.update(order);

    assertThrows(InvalidOrderStatusException.class, () -> orderService.cancelOrder(order.getId()));
  }

  @Test
  void testCancelOrderNotFound() {
    assertThrows(OrderNotFoundException.class, () -> orderService.cancelOrder(999L));
  }

}
