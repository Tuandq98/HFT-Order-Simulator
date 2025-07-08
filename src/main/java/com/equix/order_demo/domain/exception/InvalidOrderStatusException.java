package com.equix.order_demo.domain.exception;

import com.equix.order_demo.domain.model.enums.OrderStatus;

public class InvalidOrderStatusException extends RuntimeException {
    public InvalidOrderStatusException(Long id, OrderStatus status) {
        super("Cannot cancel order with ID " + id + " because its status is " + status);
    }
}
