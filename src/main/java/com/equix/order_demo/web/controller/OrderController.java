package com.equix.order_demo.web.controller;

import com.equix.order_demo.application.service.OrderService;

import com.equix.order_demo.domain.model.Order;
import com.equix.order_demo.web.dto.req.CreateOrderRequest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<Order> create(@Valid @RequestBody CreateOrderRequest request) {
    return ResponseEntity.ok(orderService.createOrder(request));
  }

  @PostMapping("/{id}/cancel")
  public ResponseEntity<Order> cancel(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.cancelOrder(id));
  }

  @GetMapping
  public List<Order> getAll() {
    return orderService.getAllOrders();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Order> getById(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.getOrderById(id));
  }

  @PostMapping("/simulate-execution")
  public ResponseEntity<Void> simulateExecution() {
    orderService.simulateExecution();
    return ResponseEntity.ok().build();
  }
}
