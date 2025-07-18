package com.ecommerce.productService.dtos.requests;

import com.ecommerce.productService.data.models.Category;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter

public class AddProductRequest {
    @NotNull(message = "name cannot be null")
    @Size(min = 2, message = "name must be at least 2 characters")
    private String name;

    @NotNull(message = "description cannot be null")
    @Size(min = 2, message = "description must be at least 2 characters")
    private String description;

    @NotNull(message = "Amount cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be a positive number")
    @Digits(integer = 12, fraction = 6)
    private BigDecimal price;

    @NotNull(message = "Quantity must not be null")
    @Min(value = 1, message = "Quantity must be at least 1")
    private Long quantity;

    @NotNull(message = "Category cannot be null")
    private Category category;
}
