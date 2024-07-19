package com.example.demo.controller;

import com.example.demo.model.Campaign;
import com.example.demo.service.CampaignService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    private final CampaignService campaignService;

    @Autowired
    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @GetMapping("/all")
    public List<Campaign> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }

    @GetMapping("/user")
    public List<Campaign> getCampaignsByUser(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return campaignService.findCampaignsByUsername(username);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable Integer id) {
        Optional<Campaign> campaign = campaignService.getCampaignById(id);
        if (campaign.isPresent()) {
            return ResponseEntity.ok(campaign.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/create")
    public Campaign createCampaign(@Valid @RequestBody Campaign campaign) {
        return campaignService.createCampaign(campaign);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCampaign(@AuthenticationPrincipal UserDetails userDetails,
                                                   @PathVariable Integer id,
                                                   @Valid @RequestBody Campaign campaignDetails) {
        try {
            return campaignService.updateCampaign(id, userDetails, campaignDetails);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCampaign(@AuthenticationPrincipal UserDetails userDetails,
                                            @PathVariable Integer id) {
        try {
            return campaignService.deleteCampaign(id, userDetails);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

