package com.ecommerce.orderService.services;

import com.ecommerce.orderService.data.models.Order;
import com.ecommerce.orderService.data.repositories.OrderRepository;
import com.ecommerce.orderService.dtos.requests.CreateOrderRequest;
import com.ecommerce.orderService.dtos.responses.CreateOrderResponse;
import com.ecommerce.orderService.dtos.responses.GetOrderResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static com.ecommerce.orderService.data.models.OrderStatus.SHIPPED;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        Order order = Order.builder()
                .productId(5L)
                .quantity(5L)
                .totalAmount(new BigDecimal("25000"))
                .status(SHIPPED)
                .build();
        orderRepository.save(order);
    }

    @AfterEach
    public void tearDown() {
        orderRepository.deleteAll();
    }

    @Test
    public void createOrder() {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setProductId(1L);
        createOrderRequest.setQuantity(2L);

        CreateOrderResponse createOrderResponse = orderService.createOrder(createOrderRequest);

        assertNotNull(createOrderResponse);
    }


    @Test
    public void fetchAllOrdersSuccessfully() {
        List<GetOrderResponse> getAllOrdersResponses = orderService.getAllOrders();

        assertNotNull(getAllOrdersResponses);
        assertEquals(1, getAllOrdersResponses.size());
        assertEquals(SHIPPED, getAllOrdersResponses.getFirst().getStatus());
    }

}