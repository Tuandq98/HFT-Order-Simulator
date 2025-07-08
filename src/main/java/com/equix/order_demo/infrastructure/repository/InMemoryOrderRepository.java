package com.equix.order_demo.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.equix.order_demo.domain.model.Order;
import com.equix.order_demo.domain.repository.OrderRepository;

@Repository
public class InMemoryOrderRepository implements OrderRepository {
  private final Map<Long, Order> orders = new ConcurrentHashMap<>();
  private final AtomicLong counter = new AtomicLong(1);

  @Override
  public Order save(Order order) {
    Long id = counter.getAndIncrement();
    order.setId(id);
    orders.put(id, order);
    return order;
  }

  @Override
  public Optional<Order> findById(Long id) {
    return Optional.ofNullable(orders.get(id));
  }

  @Override
  public List<Order> findAll() {
    return new ArrayList<>(orders.values());
  }

  @Override
  public Order update(Order order) {
    orders.put(order.getId(), order);
    return order;
  }

  @Override
  public void clear() {
    orders.clear();
    counter.set(1); // Reset the ID counter
  }

}
