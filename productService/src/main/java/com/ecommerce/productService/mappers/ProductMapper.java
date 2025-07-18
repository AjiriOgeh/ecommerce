package com.ecommerce.productService.mappers;

import com.ecommerce.productService.data.models.Product;
import com.ecommerce.productService.dtos.responses.AddProductResponse;
import com.ecommerce.productService.dtos.responses.GetProductResponse;
import com.ecommerce.productService.dtos.responses.UpdateProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    AddProductResponse mapProductToAddProductResponse(Product product);
    UpdateProductResponse mapProductToUpdateProductResponse(Product product);
    GetProductResponse mapProductToGetProductResponse(Product product);
}
