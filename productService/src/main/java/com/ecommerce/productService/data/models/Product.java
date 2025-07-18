package com.ecommerce.productService.data.models;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long quantity;
    private Category category;
    private LocalDateTime dateCreated = LocalDateTime.now();

}
