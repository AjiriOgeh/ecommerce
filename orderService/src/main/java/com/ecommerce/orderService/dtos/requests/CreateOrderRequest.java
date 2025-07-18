package com.ecommerce.orderService.dtos.requests;

import com.ecommerce.orderService.data.models.OrderStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
public class CreateOrderRequest {
    @NotNull(message = "Product Id  must not be null")
    @Positive(message = "Product Id must be greater than zero")
    private Long productId;

    @NotNull(message = "Quantity must not be null")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Positive(message = "Quantity must be greater than zero")
    private Long quantity;

}
