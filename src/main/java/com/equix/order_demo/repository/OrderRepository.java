package com.equix.order_demo.repository;

import com.equix.order_demo.model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
  Order save(Order order);

  Optional<Order> findById(Long id);

  List<Order> findAll();

  Order update(Order order);

  // For testing purposes, to reset the order store
  void clear();
}