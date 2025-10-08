package com.works;

import com.works.entity.Product;
import com.works.repository.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@TestMethodOrder( MethodOrderer.OrderAnnotation.class )
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JPATest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeAll
    public void beforeAll(){
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            product.setProduct_title("Test Product " + i);
            product.setProduct_price(100.0f);
            productRepository.save(product);
        }
    }

    @Test
    @Order(1)
    @Tag("security")
    public void productSaveTest(){
        Product product = new Product();
        product.setProduct_title("Test Product");
        product.setProduct_price(100.0f);
        productRepository.save(product);
        Assertions.assertNotNull(product.getProduct_id());
        Assertions.assertEquals(product.getProduct_title(),"Test Product");
        Assertions.assertEquals(product.getProduct_price(),100.0f);
        System.out.println(product);
    }

    @Test
    @Order(2)
    public void productGetAllTest(){
        List<Product> list = productRepository.findAll();
        System.out.println(list);
        Assertions.assertNotNull(list);
        Assertions.assertEquals(list.size(),10);
    }

}
