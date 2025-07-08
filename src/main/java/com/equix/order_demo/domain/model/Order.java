package com.equix.order_demo.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.equix.order_demo.domain.model.enums.OrderSide;
import com.equix.order_demo.domain.model.enums.OrderStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
  private Long id;
  private String symbol;
  private BigDecimal price;
  private Integer quantity;
  private OrderSide side;
  private OrderStatus status;
  private LocalDateTime createdTime;
}
