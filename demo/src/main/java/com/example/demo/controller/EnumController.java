package com.example.demo.controller;

import com.example.demo.model.CampaignKeyword;
import com.example.demo.model.CampaignStatus;
import com.example.demo.model.Town;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/enums")
public class EnumController {

    @GetMapping("/campaign-status")
    public List<String> getCampaignStatus() {
        return Arrays.stream(CampaignStatus.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/towns")
    public List<String> getTowns() {
        return Arrays.stream(Town.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping("/keywords")
    public List<String> getKeywords() {
        return Arrays.stream(CampaignKeyword.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
