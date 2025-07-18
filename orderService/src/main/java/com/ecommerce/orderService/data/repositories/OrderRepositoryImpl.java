package com.ecommerce.orderService.data.repositories;

import com.ecommerce.orderService.data.models.Order;
import com.ecommerce.orderService.exceptions.OrderNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepositoryImpl implements OrderRepository{

    private final Map<Long, Order> orders = new HashMap<>();
    private Long id = 1L;

    @Override
    public Order save(Order order) {
        if (order.getId() != null) {
            update(order.getId(), order);
        } else {
            order.setId(id++);
        }
        orders.put(order.getId(), order);
        return order;
    }

    private Order update(Long id, Order order) {
        if (!orders.containsKey(id)) {
            throw new OrderNotFoundException(String.format("Order with id %d not found", id));
        }
        order.setId(id);
        orders.put(id, order);
        return order;
    }

    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orders.get(id));
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public void delete(Long id) {
        if (!orders.containsKey(id)) {
            throw new OrderNotFoundException(String.format("Order with id %d not found", id));
        }
        orders.remove(id);
    }

    @Override
    public void deleteAll() {
        orders.clear();
        resetId();
    }

    public void resetId() {
        id = 1L;
    }
}
