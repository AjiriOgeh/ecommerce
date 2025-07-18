package com.ecommerce.productService.data.repositories;

import com.ecommerce.productService.data.models.Product;
import com.ecommerce.productService.exceptions.ProductNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

    private final Map<Long, Product> products = new HashMap<>();
    private Long id = 1L;

    @Override
    public Product save(Product product) {
        if (product.getId() != null) {
            update(product.getId(), product);
        } else {
            product.setId(id++);
        }
        products.put(product.getId(), product);
        return product;
    }

    private Product update(Long id, Product product) {
        if (!products.containsKey(id)) {
            throw new ProductNotFoundException(String.format("Product with id %d not found", id));
        }
        product.setId(id);
        products.put(id, product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void delete(Long id) {
        if (!products.containsKey(id)) {
            throw new ProductNotFoundException(String.format("Product with id %d not found", id));
        }
        products.remove(id);
    }

    @Override
    public void deleteAll() {
        products.clear();
        resetId();
    }

    public void resetId() {
        id = 1L;
    }
}
