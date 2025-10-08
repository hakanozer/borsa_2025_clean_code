package com.works.service;

import com.works.entity.Product;
import com.works.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;

    public Product save(Product product) {
        productRepository.save(product);
        return product;
    }


    public Product getProductById(Long product_id){
        Product dbProduct = productRepository.findById(product_id).get();
        if (dbProduct == null) {
            throw new IllegalArgumentException("Product not found");
        }
        return dbProduct;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

}
