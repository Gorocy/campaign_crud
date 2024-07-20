package com.example.demo.service;

import com.example.demo.model.Campaign;
import com.example.demo.model.CampaignStatus;
import com.example.demo.model.User;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignService {

    private final CampaignRepository campaignRepository;

    private final UserRepository userRepository;

    @Autowired
    public CampaignService(CampaignRepository campaignRepository, UserRepository userRepository) {
        this.campaignRepository = campaignRepository;
        this.userRepository = userRepository;
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

    public ResponseEntity<String>  updateCampaign(Integer id,
                                             UserDetails userDetails,
                                             Campaign campaignDetails) {
        Optional<Campaign> optionalCampaign = campaignRepository.findById(id);

        if (optionalCampaign.isEmpty() || !campaignDetails.getId().equals(optionalCampaign.get().getId())) {
            return ResponseEntity.notFound().build();
        }

        String username = userDetails.getUsername();
        Optional<User> user = userRepository.findByEmail(username);

        if (user.isEmpty() || !optionalCampaign.get().getUser().getId().equals(user.get().getId())) {
            return ResponseEntity.status(403).body("You are not authorized to delete this campaign");
        }

        Campaign campaign = optionalCampaign.get();
        campaign.setName(campaignDetails.getName());
        campaign.setKeywords(campaignDetails.getKeywords());
        campaign.setBidAmount(campaignDetails.getBidAmount());
        campaign.setCampaignFund(campaignDetails.getCampaignFund());
        campaign.setTown(campaignDetails.getTown());
        campaign.setRadius(campaignDetails.getRadius());
        Optional.of(campaignRepository.save(campaign));
        return ResponseEntity.ok("Successfully updated campaign");
    }

    public ResponseEntity<String> deleteCampaign(Integer id, UserDetails userDetails) {
        Optional<Campaign> campaign = campaignRepository.findById(id);
        if (campaign.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        String username = userDetails.getUsername();
        Optional<User> user = userRepository.findByEmail(username);

        if (user.isEmpty() || !campaign.get().getUser().getId().equals(user.get().getId())) {
            return ResponseEntity.status(403).body("You are not authorized to delete this campaign");
        }

        campaignRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public Optional<Campaign> getCampaignById(Integer id) {
        return campaignRepository.findById(id);
    }

    public List<Campaign> findCampaignsByUsername(String username) {
        User user = userRepository.findByEmail(username).orElseThrow(() -> new RuntimeException("User not found"));
        return campaignRepository.findByUser(user);
    }
}