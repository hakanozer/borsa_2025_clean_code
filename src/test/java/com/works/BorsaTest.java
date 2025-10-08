package com.works;

import com.works.entity.Product;
import com.works.service.ProductService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BorsaTest {

    Random ran;

    @Autowired
    ProductService productService;

    @BeforeAll
    void beforeAll() {
        System.out.println("Before All");
        ran = new Random();
    }

    @AfterAll
    void afterAll() {
        System.out.println("After All");
        ran = null;
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before Each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After Each");
    }


    @Test
    @DisplayName( "Product save test")
    @Order(1)
    @Tag("security")
    void productSaveTest() {
        Product product = new Product();
        product.setProduct_title("Test Product");
        product.setProduct_price(ran.nextFloat());
        productService.save(product);
        Assertions.assertAll(
                () ->  Assertions.assertNotNull(product, "Product null"),
                () -> Assertions.assertEquals(product.getProduct_id() > 0, true, "Product id not greater than 0")
        );
    }

    @Test
    @DisplayName( "Product get test")
    @Order(2)
    void productGetTest() {
        Product product = productService.getProductById(1L);
        Assertions.assertAll(
                () ->  Assertions.assertNotNull(product, "Product null"),
                () -> Assertions.assertEquals(product.getProduct_id() > 0, true, "Product id not greater than 0")
        );
    }

    @Test
    @DisplayName("Product get all")
    @Order(3)
    void productGetAllTest() {
        List<Product> list = productService.getAllProducts();
        Assertions.assertAll(
                () -> Assertions.assertNotNull(list, "List null"),
                () -> Assertions.assertEquals(list.size() > 0, true, "List size not greater than 0")
        );
    }

}
