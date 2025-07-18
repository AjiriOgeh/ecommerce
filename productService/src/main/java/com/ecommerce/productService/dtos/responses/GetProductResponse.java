package com.ecommerce.productService.dtos.responses;

import com.ecommerce.productService.data.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class GetProductResponse {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long quantity;
    private Category category;
}
