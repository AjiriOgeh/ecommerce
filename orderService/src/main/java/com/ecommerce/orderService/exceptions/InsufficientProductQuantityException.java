package com.ecommerce.orderService.exceptions;

public class InsufficientProductQuantityException extends RuntimeException {
    public InsufficientProductQuantityException(String message) {
        super(message);
    }
}
