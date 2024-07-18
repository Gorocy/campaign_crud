package com.example.demo.service;

import com.example.demo.model.Campaign;
import com.example.demo.model.CampaignStatus;
import com.example.demo.model.User;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public CampaignService(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Campaign createCampaign(Campaign campaign) {
        User user = getCurrentUser();
        campaign.setUser(user);
        campaign.setStatus(CampaignStatus.ON);
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
            campaign.setTown(campaignDetails.getTown());
            campaign.setRadius(campaignDetails.getRadius());
            return Optional.of(campaignRepository.save(campaign));}

        return Optional.empty();
    }

    public void deleteCampaign(Integer id) {
        campaignRepository.deleteById(id);
    }
}