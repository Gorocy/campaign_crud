package com.example.demo.service;

import com.example.demo.model.Campaign;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.request.CampaignRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    private final UserService userService;

    private final ProductService productService;

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    @Transactional
    public Object createCampaign(CampaignRequest campaignRequest) {
    
        Optional<Product> product = productService.findById(campaignRequest.getProductId());
    
        if (product.isEmpty() ||
                !campaignRequest.getProductId().equals(product.get().getId()) ||
                !product.get().getUser().equals(userService.getCurrentUser())) {
            return "INVALID PRODUCT ID";
        }
    
        User currentUser = userService.getCurrentUser();
        BigDecimal userBalance = currentUser.getBalance();
        if (campaignRequest.getCampaignFund().compareTo(userBalance) > 0) {
            return "Insufficient balance to set the campaign fund";
        }
    
        Campaign campaign = new Campaign(
                campaignRequest.getName(),
                campaignRequest.getKeywords(),
                campaignRequest.getBidAmount(),
                campaignRequest.getCampaignFund(),
                campaignRequest.getStatus(),
                campaignRequest.getTown(),
                campaignRequest.getRadius(),
                product.orElse(null)
        );
    
        campaign.setUser(currentUser);
        
        userService.updateUserBalance(currentUser, campaignRequest.getCampaignFund().negate());

        return campaignRepository.save(campaign);
    }

    @Transactional
    public String updateCampaign(Campaign campaignDetails) {
        Optional<Campaign> optionalCampaign = campaignRepository.findById(campaignDetails.getId());
    
        if (optionalCampaign.isEmpty() || !campaignDetails.getId().equals(optionalCampaign.get().getId())) {
            return "NO CAMPAIGN FOUND";
        }
    
        if (!optionalCampaign.get().getUser().getId().equals(userService.getCurrentUser().getId())) {
            return "You are not authorized to update this campaign";
        }
    
        User currentUser = userService.getCurrentUser();
        BigDecimal userBalance = currentUser.getBalance();
        if (campaignDetails.getCampaignFund().compareTo(userBalance) > 0) {
            return "Insufficient balance to set the campaign fund";
        }
    
        BigDecimal fundDifference = campaignDetails.getCampaignFund().subtract(optionalCampaign.get().getCampaignFund());
        
        Campaign campaign = optionalCampaign.get();
        campaign.setName(campaignDetails.getName());
        campaign.setKeywords(campaignDetails.getKeywords());
        campaign.setBidAmount(campaignDetails.getBidAmount());
        campaign.setCampaignFund(campaignDetails.getCampaignFund());
        campaign.setTown(campaignDetails.getTown());
        campaign.setRadius(campaignDetails.getRadius());
        campaignRepository.save(campaign);

        userService.updateUserBalance(currentUser, fundDifference.negate());
        
        return "Successfully updated campaign";
    }

    @Transactional
    public String deleteCampaign(Integer id) {
        Optional<Campaign> campaign = campaignRepository.findById(id);
        if (campaign.isEmpty()) {
            return "NO CAMPAIGN FOUND";
        }

        User currentUser = userService.getCurrentUser();

        if (!campaign.get().getUser().getId().equals(currentUser.getId())) {
            return "You are not authorized to delete this campaign";
        }

        userService.updateUserBalance(currentUser, campaign.get().getCampaignFund());

        campaignRepository.deleteById(id);
        return "Successfully deleted campaign";
    }

    public Optional<Campaign> getCampaignById(Integer id) {
        return campaignRepository.findById(id);
    }

    public List<Campaign> findCampaignsByUser() {
        return campaignRepository.findByUser(userService.getCurrentUser());
    }
}