package com.works.repository;

import com.works.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // List<Product> findByProduct_titleContainsIgnoreCase(String product_title);
}
