package com.ecommerce.orderService.data.models;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private Long productId;
    private Long quantity;
    private BigDecimal totalAmount;
    private OrderStatus status;
    private LocalDateTime dateCreated;
}
