package com.ecommerce.orderService.controllers;

import com.ecommerce.orderService.dtos.requests.CreateOrderRequest;
import com.ecommerce.orderService.dtos.responses.ApiResponse;
import com.ecommerce.orderService.dtos.responses.CreateOrderResponse;
import com.ecommerce.orderService.dtos.responses.GetOrderResponse;
import com.ecommerce.orderService.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<ApiResponse> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest) {
        CreateOrderResponse createOrderResponse = orderService.createOrder(createOrderRequest);
        return ResponseEntity.status(CREATED)
                .body(new ApiResponse(createOrderResponse, true));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllOrders() {
        List<GetOrderResponse> getAllOrderResponses = orderService.getAllOrders();
        return ResponseEntity.status(OK)
                .body(new ApiResponse(getAllOrderResponses, true));
    }

}
