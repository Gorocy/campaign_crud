package com.example.demo.controller;

import com.example.demo.model.Campaign;
import com.example.demo.service.CampaignService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/create")
    public Campaign createCampaign(@Valid @RequestBody Campaign campaign) {
        return campaignService.createCampaign(campaign);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Campaign> updateCampaign(@PathVariable Integer id,
                                                   @Valid @RequestBody Campaign campaignDetails) {
        Optional<Campaign> updatedCampaign = campaignService.updateCampaign(id, campaignDetails);
        if (updatedCampaign.isPresent()) {
            return ResponseEntity.ok(updatedCampaign.get());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCampaign(@PathVariable Integer id) {
        try {
            campaignService.deleteCampaign(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

