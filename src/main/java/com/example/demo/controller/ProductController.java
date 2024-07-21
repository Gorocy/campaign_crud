package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public Product createProduct(@Valid @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllProductsByUser() {
        Optional<List<Product>> productsOptional = productService.getAllProductsByUser();

        if (productsOptional.isPresent() && !productsOptional.get().isEmpty()) {
            return ResponseEntity.ok(productsOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

