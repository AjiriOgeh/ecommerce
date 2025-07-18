package com.ecommerce.orderService.dtos.responses;

import com.ecommerce.orderService.data.models.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class CreateOrderResponse {
    private Long id;
    private Long quantity;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private LocalDateTime dateCreated = LocalDateTime.now();
}
