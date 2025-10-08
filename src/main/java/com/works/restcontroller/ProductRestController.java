package com.works.restcontroller;

import com.works.entity.Product;
import com.works.models.Result;
import com.works.service.ProductService;
import com.works.utils.RestResult;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Valid
public class ProductRestController {

    final ProductService productService;

    @GetMapping("getProduct")
    public ResponseEntity<Result> getProduct(@NotNull @NotEmpty @RequestParam Long product_id){
        return RestResult.success(null);
    }
    //getProduct?product_id=1

    @GetMapping("getProduct/{product_id}")
    public ResponseEntity<Result> getProductVar(@NotNull @NotEmpty @PathVariable Long product_id){
        Product proDb = productService.getProductById(product_id);
        return RestResult.success(proDb);
    }
    //getProduct/1



}
