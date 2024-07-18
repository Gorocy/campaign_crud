package com.example.demo.service;

import com.example.demo.model.Campaign;
import com.example.demo.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    @Autowired
    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Campaign createCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public Optional<Campaign> updateCampaign(Integer id, Campaign campaignDetails) {
        Optional<Campaign> optionalCampaign = campaignRepository.findById(id);
        if (optionalCampaign.isPresent()) {
            Campaign campaign = optionalCampaign.get();
            campaign.setName(campaignDetails.getName());
            campaign.setKeywords(campaignDetails.getKeywords());
            campaign.setBidAmount(campaignDetails.getBidAmount());
            campaign.setCampaignFund(campaignDetails.getCampaignFund());
            campaign.setStatus(campaignDetails.getStatus());
            campaign.setTown(campaignDetails.getTown());
            campaign.setRadius(campaignDetails.getRadius());
            return Optional.of(campaignRepository.save(campaign));}

        return Optional.empty();
    }

    public void deleteCampaign(Integer id) {
        campaignRepository.deleteById(id);
    }
}