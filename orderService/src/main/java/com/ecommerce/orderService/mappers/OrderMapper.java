package com.ecommerce.orderService.mappers;

import com.ecommerce.orderService.data.models.Order;
import com.ecommerce.orderService.dtos.responses.CreateOrderResponse;
import com.ecommerce.orderService.dtos.responses.GetOrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderMapper {

    CreateOrderResponse mapOrderToCreateOrderResponse(Order order);
    GetOrderResponse mapOrderToGetOrderResponse(Order order);
}
