package com.ecommerce.orderService.services;

import com.ecommerce.orderService.data.models.Order;
import com.ecommerce.orderService.data.models.Product;
import com.ecommerce.orderService.data.repositories.OrderRepository;
import com.ecommerce.orderService.dtos.requests.CreateOrderRequest;
import com.ecommerce.orderService.dtos.responses.ApiResponse;
import com.ecommerce.orderService.dtos.responses.CreateOrderResponse;
import com.ecommerce.orderService.dtos.responses.GetOrderResponse;
import com.ecommerce.orderService.exceptions.InsufficientProductQuantityException;
import com.ecommerce.orderService.exceptions.ProductNotFoundException;
import com.ecommerce.orderService.mappers.OrderMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

import static com.ecommerce.orderService.data.models.OrderStatus.SHIPPED;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final WebClient.Builder webClientBuilder;

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        ApiResponse apiResponse = webClientBuilder.build().get()
                .uri("http://PRODUCTSERVICE/api/v1/products/{id}", createOrderRequest.getProductId())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, response ->
                        Mono.error(new ProductNotFoundException("Product not found"))
                )
                .bodyToMono(ApiResponse.class)
                .block();

        Product product = new ObjectMapper()
                .convertValue(apiResponse.getResponse(), Product.class);

        if (createOrderRequest.getQuantity() > product.getQuantity()) {
            throw new InsufficientProductQuantityException("Insufficient number of products");
        }

        Order order = Order.builder()
                .productId(product.getId())
                .quantity(createOrderRequest.getQuantity())
                .totalAmount(product.getPrice().multiply(BigDecimal.valueOf(createOrderRequest.getQuantity())))
                .status(SHIPPED)
                .build();

        orderRepository.save(order);
        return orderMapper.mapOrderToCreateOrderResponse(order);
    }

    @Override
    public List<GetOrderResponse> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        log.info("all orders {}", orders);
        return orders.stream()
                .map(orderMapper::mapOrderToGetOrderResponse).toList();
    }
}
