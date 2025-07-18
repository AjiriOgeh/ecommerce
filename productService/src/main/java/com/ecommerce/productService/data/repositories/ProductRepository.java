package com.ecommerce.productService.data.repositories;

import com.ecommerce.productService.data.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);
    Optional<Product> findById(Long id);
    List<Product> findAll();
    void delete(Long id);
    void deleteAll();
}
