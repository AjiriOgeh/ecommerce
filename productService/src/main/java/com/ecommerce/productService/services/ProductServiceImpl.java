package com.ecommerce.productService.services;

import com.ecommerce.productService.data.models.Product;
import com.ecommerce.productService.data.repositories.ProductRepository;
import com.ecommerce.productService.dtos.requests.AddProductRequest;
import com.ecommerce.productService.dtos.requests.UpdateProductRequest;
import com.ecommerce.productService.dtos.responses.AddProductResponse;
import com.ecommerce.productService.dtos.responses.GetProductResponse;
import com.ecommerce.productService.dtos.responses.UpdateProductResponse;
import com.ecommerce.productService.exceptions.ProductNotFoundException;
import com.ecommerce.productService.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public AddProductResponse addProduct(AddProductRequest addProductRequest) {
        Product product = Product.builder()
                .name(addProductRequest.getName())
                .description(addProductRequest.getDescription())
                .price(addProductRequest.getPrice())
                .quantity(addProductRequest.getQuantity())
                .category(addProductRequest.getCategory())
                .build();
        productRepository.save(product);
        log.info("added product {}", product);
        return productMapper.mapProductToAddProductResponse(product);
    }

    @Override
    public UpdateProductResponse updateProduct(Long id, UpdateProductRequest updateProductRequest) {
        Product product = Product.builder()
                .id(id)
                .name(updateProductRequest.getName())
                .description(updateProductRequest.getDescription())
                .price(updateProductRequest.getPrice())
                .quantity(updateProductRequest.getQuantity())
                .category(updateProductRequest.getCategory())
                .build();
        productRepository.save(product);
        log.info("updated product {}", product);

        List<Product> products = productRepository.findAll();
        log.info("all products {}", products);

        return productMapper.mapProductToUpdateProductResponse(product);
    }

    @Override
    public GetProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException(
                        String.format("Product with id %d does not exist", id)));

        return productMapper.mapProductToGetProductResponse(product);
    }

    @Override
    public List<GetProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        log.info("all products {}", products);
        return products.stream()
                .map(productMapper::mapProductToGetProductResponse).toList();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.delete(id);
    }
}
