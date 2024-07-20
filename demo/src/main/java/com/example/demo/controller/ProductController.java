package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@AuthenticationPrincipal UserDetails userDetails,
                                                 @RequestBody Product product) {
        Product createdProduct = productService.createProduct(product, userDetails);
        return ResponseEntity.ok(createdProduct);
    }

    @GetMapping("/get")
    public ResponseEntity<Optional<List<Product>>> getAllProductsByUserId(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            Optional<List<Product>> products = productService.getAllProductsByUser(userDetails);
            return ResponseEntity.ok(products);
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        try {
        Optional<Product> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

