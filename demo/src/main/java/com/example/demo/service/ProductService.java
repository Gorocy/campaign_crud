package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product, UserDetails userDetails) {
        product.setUser((User) userDetails);
        return productRepository.save(product);
    }

    public Optional<List<Product>> getAllProductsByUser(UserDetails userDetails) {
        User user = (User) userDetails;
        return productRepository.findAllByUser(user);
    }

    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

}
