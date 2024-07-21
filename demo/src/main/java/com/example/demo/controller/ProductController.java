package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/get")
    public ResponseEntity<Optional<List<Product>>> getAllProductsByUser() {
        return ResponseEntity.ok(productService.getAllProductsByUser());
    }

}

