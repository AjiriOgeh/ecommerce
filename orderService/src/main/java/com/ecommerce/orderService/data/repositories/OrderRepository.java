package com.ecommerce.orderService.data.repositories;

import com.ecommerce.orderService.data.models.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    Order save(Order order);
    Optional<Order> findById(Long id);
    List<Order> findAll();
    void delete(Long id);
    void deleteAll();
}
