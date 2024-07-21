package com.example.demo.request;

import com.example.demo.model.CampaignKeyword;
import com.example.demo.model.CampaignStatus;
import com.example.demo.model.Product;
import com.example.demo.model.Town;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class CampaignRequest {
    private String name;
    private List<CampaignKeyword> keywords;
    private BigDecimal bidAmount;
    private BigDecimal campaignFund;
    private CampaignStatus status;
    private Integer productId;
    private Town town;
    private Integer radius;
}
