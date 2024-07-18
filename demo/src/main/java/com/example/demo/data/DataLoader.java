package com.example.demo.data;

import com.example.demo.model.Campaign;
import com.example.demo.model.CampaignKeyword;
import com.example.demo.model.CampaignStatus;
import com.example.demo.model.Town;
import com.example.demo.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final CampaignRepository campaignRepository;

    @Autowired
    public DataLoader(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Campaign campaign1 = new Campaign("Campaign 1", Arrays.asList(CampaignKeyword.BEAUTY, CampaignKeyword.ART), BigDecimal.valueOf(10.0), BigDecimal.valueOf(100.0), CampaignStatus.ON,  Town.BIAŁYSTOK, 10);
        Campaign campaign2 = new Campaign("Campaign 2", Arrays.asList(CampaignKeyword.ELECTRONICS, CampaignKeyword.GAMES), BigDecimal.valueOf(15.0), BigDecimal.valueOf(150.0), CampaignStatus.OFF, Town.ŁÓDŹ , 15);

        campaignRepository.save(campaign1);
        campaignRepository.save(campaign2);


    }
}