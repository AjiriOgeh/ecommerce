package com.ecommerce.productService.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class UpdateProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long quantity;
}
