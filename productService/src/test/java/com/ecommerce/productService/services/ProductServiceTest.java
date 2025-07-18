package com.ecommerce.productService.services;

import com.ecommerce.productService.data.models.Product;
import com.ecommerce.productService.data.repositories.ProductRepository;
import com.ecommerce.productService.dtos.requests.AddProductRequest;
import com.ecommerce.productService.dtos.requests.UpdateProductRequest;
import com.ecommerce.productService.dtos.responses.AddProductResponse;
import com.ecommerce.productService.dtos.responses.GetProductResponse;
import com.ecommerce.productService.dtos.responses.UpdateProductResponse;
import com.ecommerce.productService.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static com.ecommerce.productService.data.models.Category.BOOKS;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        Product product = Product.builder()
                .name("Purple Hibiscus")
                .description("Written by Chimamanda Adichie")
                .price(new BigDecimal("5000"))
                .quantity(5L)
                .category(BOOKS)
                .build();
        productRepository.save(product);
    }

    @AfterEach
    public void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    public void addNewProductSuccessfullyTest() {
        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setName("Half of a Yellow Sun");
        addProductRequest.setDescription("Written by Chimamanda Adichie");
        addProductRequest.setPrice(new BigDecimal("5000"));
        addProductRequest.setQuantity(2L);
        addProductRequest.setCategory(BOOKS);
        AddProductResponse addProductResponse = productService.addProduct(addProductRequest);

        assertNotNull(addProductResponse);
        assertEquals("Half of a Yellow Sun", addProductResponse.getName());
        assertEquals(2L, addProductResponse.getId());
    }

    @Test
    public void updateProductSuccessfullyTest() {
        UpdateProductRequest updateProductRequest = new UpdateProductRequest();
        updateProductRequest.setName("Americanah");
        updateProductRequest.setDescription("Written by Chimamanda Adichie");
        updateProductRequest.setQuantity(3L);
        updateProductRequest.setCategory(BOOKS);
        UpdateProductResponse updateProductResponse = productService.updateProduct(1L, updateProductRequest);

        assertNotNull(updateProductResponse);
        assertEquals("Americanah", updateProductResponse.getName());
        assertEquals(1L, updateProductResponse.getId());
    }

    @Test
    public void updateNonExistentProduct_ThrowsException() {
        UpdateProductRequest updateProductRequest = new UpdateProductRequest();
        updateProductRequest.setName("Americanah");
        updateProductRequest.setDescription("Written by Chimamanda Adichie");
        updateProductRequest.setQuantity(3L);
        updateProductRequest.setCategory(BOOKS);

        assertThrows(ProductNotFoundException.class, ()-> productService.updateProduct(2345L, updateProductRequest));
    }

    @Test
    public void fetchProductByIdSuccessfullyTest() {
        GetProductResponse getProductResponse = productService.getProductById(1L);

        assertNotNull(getProductResponse);
        assertEquals("Purple Hibiscus", getProductResponse.getName());
        assertEquals(BOOKS, getProductResponse.getCategory());
    }

    @Test
    public void fetchNonExistentProduct_ThrowsExceptionTest() {
        assertThrows(ProductNotFoundException.class, ()-> productService.getProductById(2345L));
    }

    @Test
    public void fetchAllProductsSuccessfully() {
        List<GetProductResponse> getAllProductsResponses = productService.getAllProducts();

        assertNotNull(getAllProductsResponses);
        assertEquals(1, getAllProductsResponses.size());
        assertEquals("Purple Hibiscus", getAllProductsResponses.getFirst().getName());
    }

    @Test
    public void deleteProductSuccessfully () {
        productService.deleteProduct(1L);

        try {
            productService.getProductById(1L);
        } catch (ProductNotFoundException e) {
            assertEquals("Product with id 1 does not exist", e.getMessage());
        }
    }

    @Test
    public void deleteNonExistentProduct_ThrowsExceptionTest() {
        assertThrows(ProductNotFoundException.class, ()-> productService.deleteProduct(2345L));
    }

}