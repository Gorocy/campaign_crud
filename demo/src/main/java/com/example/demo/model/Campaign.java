package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "campaigns")
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String name;

    @ElementCollection(targetClass = CampaignKeyword.class)
    @Enumerated(EnumType.STRING)
    private List<CampaignKeyword> keywords = new ArrayList<>();

    @NotNull
    @Min(value = 1)
    private BigDecimal bidAmount;

    @NotNull
    @Min(value = 1)
    private BigDecimal campaignFund;

    @NotNull
    private CampaignStatus status;

    @NotNull
    private Town town;

    @Min(value = 1)
    @NotNull
    private Integer radius;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Campaign(String name, List<CampaignKeyword> keywords, BigDecimal bidAmount, BigDecimal campaignFund, CampaignStatus status, Town town, Integer radius, Product product) {
        this.name = name;
        this.keywords = keywords;
        this.bidAmount = bidAmount;
        this.campaignFund = campaignFund;
        this.status = status;
        this.town = town;
        this.radius = radius;
        this.product = product;
    }
}
