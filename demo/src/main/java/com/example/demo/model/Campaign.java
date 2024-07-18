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

import static com.example.demo.model.CampaignStatus.ON;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "campaign")
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

    @NotNull
    private Integer radius;


    public Campaign(String name, List<CampaignKeyword> keywords, BigDecimal bidAmount, BigDecimal campaignFund, Town town, Integer radius) {
        this.name = name;
        this.keywords = keywords;
        this.bidAmount = bidAmount;
        this.campaignFund = campaignFund;
        this.status = ON;
        this.town = town;
        this.radius = radius;

    }
}
