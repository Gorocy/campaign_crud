package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final UserService userService;

    @Autowired
    public ProductService(ProductRepository productRepository, UserService userService) {
        this.productRepository = productRepository;
        this.userService = userService;
    }


    public Product createProduct(Product product) {
        product.setUser(userService.getCurrentUser());
        return productRepository.save(product);
    }

    public Optional<List<Product>> getAllProductsByUser() {
        return productRepository.findAllByUser(userService.getCurrentUser());
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

    public Optional<Product> findById(Integer productId) {
        return productRepository.findById(productId);
    }

}
