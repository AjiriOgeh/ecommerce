package com.ecommerce.orderService.services;


import com.ecommerce.orderService.dtos.requests.CreateOrderRequest;
import com.ecommerce.orderService.dtos.responses.CreateOrderResponse;
import com.ecommerce.orderService.dtos.responses.GetOrderResponse;

import java.util.List;

public interface OrderService {

    CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest);

    List<GetOrderResponse> getAllOrders();
}
