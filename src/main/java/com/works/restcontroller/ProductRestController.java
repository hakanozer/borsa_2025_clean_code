package com.works.restcontroller;

import com.works.entity.Product;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Valid
public class ProductRestController {


    @GetMapping("getProduct")
    public Product getProduct(@NotNull @NotEmpty @RequestParam Long product_id){
        return null;
    }
    //getProduct?product_id=1

    @GetMapping("getProduct/{product_id}")
    public Product getProductVar(@NotNull @NotEmpty @PathVariable Long product_id){
        return null;
    }
    //getProduct/1



}
