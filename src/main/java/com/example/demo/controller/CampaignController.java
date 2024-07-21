package com.example.demo.controller;

import com.example.demo.model.Campaign;
import com.example.demo.request.CampaignRequest;
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

    @PostMapping("/create")
    public ResponseEntity<Object> createCampaign(@Valid @RequestBody CampaignRequest campaignRequest) {
        try {
            return ResponseEntity.ok(campaignService.createCampaign(campaignRequest));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/all")
    public List<Campaign> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }

    @GetMapping("/user")
    public List<Campaign> getCampaignsByUser() {
        return campaignService.findCampaignsByUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Campaign>> getCampaignById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(campaignService.getCampaignById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateCampaign(@Valid @RequestBody Campaign campaignDetails) {
        try {
            return ResponseEntity.ok(campaignService.updateCampaign(campaignDetails));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCampaign(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(campaignService.deleteCampaign(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

