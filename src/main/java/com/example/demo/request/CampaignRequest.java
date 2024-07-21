package com.example.demo.request;

import com.example.demo.model.CampaignKeyword;
import com.example.demo.model.CampaignStatus;
import com.example.demo.model.Town;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
public class CampaignRequest {
    private final String name;
    private final List<CampaignKeyword> keywords;
    private final BigDecimal bidAmount;
    private final BigDecimal campaignFund;
    private final CampaignStatus status;
    private final Integer productId;
    private final Town town;
    private final Integer radius;
}
