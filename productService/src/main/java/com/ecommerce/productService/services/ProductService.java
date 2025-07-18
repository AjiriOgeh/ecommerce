package com.ecommerce.productService.services;

import com.ecommerce.productService.dtos.requests.AddProductRequest;
import com.ecommerce.productService.dtos.requests.UpdateProductRequest;
import com.ecommerce.productService.dtos.responses.AddProductResponse;
import com.ecommerce.productService.dtos.responses.GetProductResponse;
import com.ecommerce.productService.dtos.responses.UpdateProductResponse;

import java.util.List;

public interface ProductService {
    AddProductResponse addProduct(AddProductRequest addProductRequest);

    UpdateProductResponse updateProduct(Long id, UpdateProductRequest updateProductRequest);

    GetProductResponse getProductById(Long id);

    List<GetProductResponse> getAllProducts();

    void deleteProduct(Long id);
}
