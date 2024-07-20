package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "_user")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private Set<Campaign> campaigns;

    public Product(String name, Integer price, User user) {
        this.name = name;
        this.price = price;
        this.user = user;
    }
}
