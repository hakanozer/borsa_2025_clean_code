package com.works.restcontroller;

import com.works.entity.Product;
import com.works.service.ProductService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.nullValue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductRestController.class)
public class ProductRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    /**
     * Test case for the getProductVar method with a valid product ID.
     * Ensures successful retrieval of a Product and correct HTTP status 200.
     */
    @Test
    @Tag("security")
    public void testGetProductVar_ValidProductId() throws Exception {
        Product product = new Product();
        product.setProduct_id(1L);
        product.setProduct_title("Test Product");
        product.setProduct_price(100f);

        when(productService.getProductById(1L)).thenReturn(product);

        mockMvc.perform(get("/product/getProduct/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.name").value("Test Product"))
                .andExpect(jsonPath("$.data.price").value(100.0))
                .andExpect(jsonPath("$.status").value("success"));
    }

    /**
     * Test case for the getProductVar method with a non-existent product ID.
     * Ensures Product is not found and appropriate error handling or HTTP status 404 is handled.
     */
    @Test
    public void testGetProductVar_NonExistentProductId() throws Exception {
        when(productService.getProductById(anyLong())).thenReturn(null);

        mockMvc.perform(get("/product/getProduct/999")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(nullValue()))
                .andExpect(jsonPath("$.status").value("success"));
    }

    /**
     * Test case for the getProductVar method with an invalid product ID parameter (null or empty).
     * Ensures proper input validation and error handling.
     */
    @Test
    public void testGetProductVar_InvalidProductId() throws Exception {
        mockMvc.perform(get("/product/getProduct/ ")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}