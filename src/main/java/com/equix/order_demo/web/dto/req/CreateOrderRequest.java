package com.equix.order_demo.web.dto.req;

import java.math.BigDecimal;

import com.equix.order_demo.domain.model.enums.OrderSide;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateOrderRequest {

  @NotBlank(message = "Symbol must not be blank")
  private String symbol;

  @NotNull(message = "Price must not be null")
  @DecimalMin(value = "0.01", inclusive = true, message = "Price must be greater than 0")
  private BigDecimal price;

  @NotNull(message = "Quantity must not be null")
  @Min(value = 1, message = "Quantity must be at least 1")
  private Integer quantity;

  @NotNull(message = "Side must not be null")
  private OrderSide side;
}
