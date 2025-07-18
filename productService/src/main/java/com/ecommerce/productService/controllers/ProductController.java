package com.ecommerce.productService.controllers;

import com.ecommerce.productService.dtos.requests.AddProductRequest;
import com.ecommerce.productService.dtos.requests.UpdateProductRequest;
import com.ecommerce.productService.dtos.responses.AddProductResponse;
import com.ecommerce.productService.dtos.responses.ApiResponse;
import com.ecommerce.productService.dtos.responses.GetProductResponse;
import com.ecommerce.productService.dtos.responses.UpdateProductResponse;
import com.ecommerce.productService.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse> addProduct(@RequestBody @Valid AddProductRequest addProductRequest) {
        AddProductResponse addProductResponse = productService.addProduct(addProductRequest);
        return ResponseEntity.status(CREATED)
                .body(new ApiResponse(addProductResponse, true));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("id") Long id,@RequestBody @Valid UpdateProductRequest updateProductRequest) {
        UpdateProductResponse updateProductResponse = productService.updateProduct(id, updateProductRequest);
        return ResponseEntity.status(OK)
                .body(new ApiResponse(updateProductResponse, true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable("id") Long id) {
        GetProductResponse getProductResponse = productService.getProductById(id);
        return ResponseEntity.status(OK)
                .body(new ApiResponse(getProductResponse, true));
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<GetProductResponse> getAllProductResponses = productService.getAllProducts();
        return ResponseEntity.status(OK)
                .body(new ApiResponse(getAllProductResponses, true));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable  Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(OK)
                .body(new ApiResponse("Product deleted successfully", true));
    }
}
