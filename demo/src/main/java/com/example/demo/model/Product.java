package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    private String name;

    @Min(value = 1)
    @NotNull
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
