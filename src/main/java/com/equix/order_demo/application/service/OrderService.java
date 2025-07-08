package com.equix.order_demo.application.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.equix.order_demo.domain.exception.InvalidOrderStatusException;
import com.equix.order_demo.domain.exception.OrderNotFoundException;
import com.equix.order_demo.domain.model.Order;
import com.equix.order_demo.domain.model.enums.OrderStatus;
import com.equix.order_demo.domain.repository.OrderRepository;
import com.equix.order_demo.web.dto.req.CreateOrderRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final Logger logger = LoggerFactory.getLogger(OrderService.class);

  @Transactional
  public Order createOrder(CreateOrderRequest request) {
    Order order = Order.builder()
        .symbol(request.getSymbol())
        .price(request.getPrice())
        .quantity(request.getQuantity())
        .side(request.getSide())
        .status(OrderStatus.PENDING)
        .createdTime(LocalDateTime.now())
        .build();
    Order saved = orderRepository.save(order);
    logger.info("Created order: {}", saved);
    return saved;
  }

  @Transactional
  public Order cancelOrder(Long id) {
    Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
    if (order.getStatus() != OrderStatus.PENDING)
      throw new InvalidOrderStatusException(id, order.getStatus());
    order.setStatus(OrderStatus.CANCELLED);
    Order updated = orderRepository.update(order);
    logger.info("Cancelled order: {}", id);
    return updated;
  }

  @Transactional
  public void simulateExecution() {
    List<Order> orders = orderRepository.findAll();
    for (Order order : orders) {
      if (order.getStatus() == OrderStatus.PENDING && Math.random() > 0.5) {
        order.setStatus(OrderStatus.EXECUTED);
        orderRepository.update(order);
        logger.info("Executed order: {}", order.getId());
      }
    }
  }

  @Transactional(readOnly = true)
  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  @Transactional(readOnly = true)
  public Order getOrderById(Long id) {
    return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException(id));
  }

  // For testing purposes, to reset the order store
  public void clearOrdersForTest() {
    orderRepository.clear();
  }
}
