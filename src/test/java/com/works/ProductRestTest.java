package com.works;

import com.works.restcontroller.ProductRestController;
import com.works.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ProductRestController.class)
public class ProductRestTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    ProductService productService;

    // getProductVar test
    @Test
    public void getProductVarTest(){

    }

}
