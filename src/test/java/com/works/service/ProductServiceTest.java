package com.works.service;

import com.works.entity.Product;
import com.works.repository.ProductRepository;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    /**
     * Test suite for the ProductService class.
     * The tests are ordered as follows to cover all methods of ProductService:
     * 1. save(Product product)
     * 2. getProductById(Long product_id)
     * 3. getAllProducts()
     */

    // Order 1: Tests for the save method
    @Test
    @Order(1)
    @Tag("security")
    void testSave_Success() {
        // Arrange
        Product productToSave = new Product();
        productToSave.setProduct_id(1L);

        when(productRepository.save(productToSave)).thenReturn(productToSave);

        // Act
        Product savedProduct = productService.save(productToSave);

        // Assert
        assertNotNull(savedProduct);
        assertEquals(productToSave.getProduct_id(), savedProduct.getProduct_id());
        verify(productRepository, times(1)).save(productToSave);
    }

    // Order 2: Tests for the getProductById method
    @Test
    @Order(2)
    void testGetProductById_Success() {
        // Arrange
        Long id = 1L;
        Product mockProduct = new Product();
        mockProduct.setProduct_id(id);

        when(productRepository.findById(id)).thenReturn(Optional.of(mockProduct));

        // Act
        Product fetchedProduct = productService.getProductById(id);

        // Assert
        assertNotNull(fetchedProduct);
        assertEquals(id, fetchedProduct.getProduct_id());
        verify(productRepository, times(1)).findById(id);
    }

    @Test
    @Order(3)
    void testGetProductById_NotFound() {
        // Arrange
        Long id = 1L;

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> productService.getProductById(id)
        );
        assertEquals("Product not found", exception.getMessage());
        verify(productRepository, times(1)).findById(id);
    }

    // Order 3: Tests for the getAllProducts method
    @Test
    @Order(4)
    void testGetAllProducts_ReturnsListOfProducts() {
        // Arrange
        List<Product> mockProducts = new ArrayList<>();
        Product product1 = new Product();
        product1.setProduct_id(1L);
        Product product2 = new Product();
        product2.setProduct_id(2L);
        mockProducts.add(product1);
        mockProducts.add(product2);

        when(productRepository.findAll()).thenReturn(mockProducts);

        // Act
        List<Product> products = productService.getAllProducts();

        // Assert
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals(1L, products.get(0).getProduct_id());
        assertEquals(2L, products.get(1).getProduct_id());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    @Order(5)
    void testGetAllProducts_ReturnsEmptyListWhenNoProducts() {
        // Arrange
        when(productRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<Product> products = productService.getAllProducts();

        // Assert
        assertNotNull(products);
        assertTrue(products.isEmpty());
        verify(productRepository, times(1)).findAll();
    }
}