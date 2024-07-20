package com.example.demo.data;

import com.example.demo.auth.AuthenticationResponse;
import com.example.demo.auth.RegisterRequest;
import com.example.demo.model.*;
import com.example.demo.repository.CampaignRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final CampaignRepository campaignRepository;

    private final AuthenticationService authenticationService;

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setFirstname("Adam");
        registerRequest.setLastname("Kowalski");
        registerRequest.setEmail("adamk@gmail.com");
        registerRequest.setPassword("rootroot");

        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setFirstname("Maciej");
        registerRequest1.setLastname("Nowak");
        registerRequest1.setEmail("maciejn@gmail.com");
        registerRequest1.setPassword("rootroot");

        AuthenticationResponse registerResponse = authenticationService.register(registerRequest);

        AuthenticationResponse registerResponse1 = authenticationService.register(registerRequest1);


        User user = userRepository.findByEmail("adamk@gmail.com")
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product1 = new Product("Product 1", 100, user);
        Product product2 = new Product("Product 2", 200, user);

        productRepository.save(product1);
        productRepository.save(product2);

        Campaign campaign1 = new Campaign("Campaign 1", Arrays.asList(CampaignKeyword.BEAUTY, CampaignKeyword.ART), BigDecimal.valueOf(10.0), BigDecimal.valueOf(100.0), CampaignStatus.ON,  Town.BIAŁYSTOK, 10, product1);
        Campaign campaign2 = new Campaign("Campaign 2", Arrays.asList(CampaignKeyword.ELECTRONICS, CampaignKeyword.GAMES), BigDecimal.valueOf(15.0), BigDecimal.valueOf(150.0), CampaignStatus.OFF, Town.ŁÓDŹ , 15, product1);
        Campaign campaign3 = new Campaign("Campaign 3", Arrays.asList(CampaignKeyword.BEAUTY, CampaignKeyword.ART), BigDecimal.valueOf(10.0), BigDecimal.valueOf(100.0), CampaignStatus.ON,  Town.BIAŁYSTOK, 10, product1);
        Campaign campaign4 = new Campaign("Campaign 4", Arrays.asList(CampaignKeyword.ELECTRONICS, CampaignKeyword.GAMES), BigDecimal.valueOf(15.0), BigDecimal.valueOf(150.0), CampaignStatus.OFF, Town.ŁÓDŹ , 15, product1);
        Campaign campaign5 = new Campaign("Campaign 5", Arrays.asList(CampaignKeyword.BEAUTY, CampaignKeyword.ART), BigDecimal.valueOf(10.0), BigDecimal.valueOf(100.0), CampaignStatus.ON,  Town.BIAŁYSTOK, 10, product1);
        Campaign campaign6 = new Campaign("Campaign 6", Arrays.asList(CampaignKeyword.ELECTRONICS, CampaignKeyword.GAMES), BigDecimal.valueOf(15.0), BigDecimal.valueOf(150.0), CampaignStatus.OFF, Town.ŁÓDŹ , 15, product1);
        Campaign campaign7 = new Campaign("Campaign 7", Arrays.asList(CampaignKeyword.BEAUTY, CampaignKeyword.ART), BigDecimal.valueOf(10.0), BigDecimal.valueOf(100.0), CampaignStatus.ON,  Town.BIAŁYSTOK, 10, product1);
        Campaign campaign8 = new Campaign("Campaign 8", Arrays.asList(CampaignKeyword.ELECTRONICS, CampaignKeyword.GAMES), BigDecimal.valueOf(15.0), BigDecimal.valueOf(150.0), CampaignStatus.OFF, Town.ŁÓDŹ , 15, product2);
        Campaign campaign9 = new Campaign("Campaign 9", Arrays.asList(CampaignKeyword.BEAUTY, CampaignKeyword.ART), BigDecimal.valueOf(10.0), BigDecimal.valueOf(100.0), CampaignStatus.ON,  Town.BIAŁYSTOK, 10, product2);
        Campaign campaign10 = new Campaign("Campaign 10", Arrays.asList(CampaignKeyword.ELECTRONICS, CampaignKeyword.GAMES), BigDecimal.valueOf(15.0), BigDecimal.valueOf(150.0), CampaignStatus.OFF, Town.ŁÓDŹ , 15, product2);
        Campaign campaign11 = new Campaign("Campaign 11", Arrays.asList(CampaignKeyword.BEAUTY, CampaignKeyword.ART), BigDecimal.valueOf(10.0), BigDecimal.valueOf(100.0), CampaignStatus.ON,  Town.BIAŁYSTOK, 10, product2);
        Campaign campaign12 = new Campaign("Campaign 12", Arrays.asList(CampaignKeyword.ELECTRONICS, CampaignKeyword.GAMES), BigDecimal.valueOf(15.0), BigDecimal.valueOf(150.0), CampaignStatus.OFF, Town.ŁÓDŹ , 15, product2);
        Campaign campaign13 = new Campaign("Campaign 13", Arrays.asList(CampaignKeyword.BEAUTY, CampaignKeyword.ART), BigDecimal.valueOf(10.0), BigDecimal.valueOf(100.0), CampaignStatus.ON,  Town.BIAŁYSTOK, 10, product2);
        Campaign campaign14 = new Campaign("Campaign 14", Arrays.asList(CampaignKeyword.ELECTRONICS, CampaignKeyword.GAMES), BigDecimal.valueOf(15.0), BigDecimal.valueOf(150.0), CampaignStatus.OFF, Town.ŁÓDŹ , 15, product2);


        campaign1.setUser(user);
        campaign2.setUser(user);
        campaign3.setUser(user);
        campaign4.setUser(user);
        campaign5.setUser(user);
        campaign6.setUser(user);
        campaign7.setUser(user);
        campaign8.setUser(user);
        campaign9.setUser(user);
        campaign10.setUser(user);
        campaign11.setUser(user);
        campaign12.setUser(user);
        campaign13.setUser(user);
        campaign14.setUser(user);

        campaignRepository.save(campaign1);
        campaignRepository.save(campaign2);
        campaignRepository.save(campaign3);
        campaignRepository.save(campaign4);
        campaignRepository.save(campaign5);
        campaignRepository.save(campaign6);
        campaignRepository.save(campaign7);
        campaignRepository.save(campaign8);
        campaignRepository.save(campaign9);
        campaignRepository.save(campaign10);
        campaignRepository.save(campaign11);
        campaignRepository.save(campaign12);
        campaignRepository.save(campaign13);
        campaignRepository.save(campaign14);

        user = userRepository.findByEmail("maciejn@gmail.com")
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product3 = new Product("Product 3", 300, user);
        Product product4 = new Product("Product 4", 400, user);

        productRepository.save(product3);
        productRepository.save(product4);

        Campaign campaign1a = new Campaign("Campaign 15", Arrays.asList(CampaignKeyword.BEAUTY, CampaignKeyword.ART), BigDecimal.valueOf(10.0), BigDecimal.valueOf(100.0), CampaignStatus.ON,  Town.BIAŁYSTOK, 10, product3);
        Campaign campaign2a = new Campaign("Campaign 16", Arrays.asList(CampaignKeyword.ELECTRONICS, CampaignKeyword.GAMES), BigDecimal.valueOf(15.0), BigDecimal.valueOf(150.0), CampaignStatus.OFF, Town.ŁÓDŹ , 15, product3);
        Campaign campaign3a = new Campaign("Campaign 17", Arrays.asList(CampaignKeyword.BEAUTY, CampaignKeyword.ART), BigDecimal.valueOf(10.0), BigDecimal.valueOf(100.0), CampaignStatus.ON,  Town.BIAŁYSTOK, 10, product3);
        Campaign campaign4a = new Campaign("Campaign 18", Arrays.asList(CampaignKeyword.ELECTRONICS, CampaignKeyword.GAMES), BigDecimal.valueOf(15.0), BigDecimal.valueOf(150.0), CampaignStatus.OFF, Town.ŁÓDŹ , 15, product3);
        Campaign campaign5a = new Campaign("Campaign 19", Arrays.asList(CampaignKeyword.BEAUTY, CampaignKeyword.ART), BigDecimal.valueOf(10.0), BigDecimal.valueOf(100.0), CampaignStatus.ON,  Town.BIAŁYSTOK, 10, product3);
        Campaign campaign6a = new Campaign("Campaign 20", Arrays.asList(CampaignKeyword.ELECTRONICS, CampaignKeyword.GAMES), BigDecimal.valueOf(15.0), BigDecimal.valueOf(150.0), CampaignStatus.OFF, Town.ŁÓDŹ , 15, product3);
        Campaign campaign7a = new Campaign("Campaign 21", Arrays.asList(CampaignKeyword.BEAUTY, CampaignKeyword.ART), BigDecimal.valueOf(10.0), BigDecimal.valueOf(100.0), CampaignStatus.ON,  Town.BIAŁYSTOK, 10, product3);
        Campaign campaign8a = new Campaign("Campaign 22", Arrays.asList(CampaignKeyword.ELECTRONICS, CampaignKeyword.GAMES), BigDecimal.valueOf(15.0), BigDecimal.valueOf(150.0), CampaignStatus.OFF, Town.ŁÓDŹ , 15, product4);
        Campaign campaign9a = new Campaign("Campaign 23", Arrays.asList(CampaignKeyword.BEAUTY, CampaignKeyword.ART), BigDecimal.valueOf(10.0), BigDecimal.valueOf(100.0), CampaignStatus.ON,  Town.BIAŁYSTOK, 10, product4);
        Campaign campaign10a = new Campaign("Campaign 24", Arrays.asList(CampaignKeyword.ELECTRONICS, CampaignKeyword.GAMES), BigDecimal.valueOf(15.0), BigDecimal.valueOf(150.0), CampaignStatus.OFF, Town.ŁÓDŹ , 15, product4);
        Campaign campaign11a = new Campaign("Campaign 25", Arrays.asList(CampaignKeyword.BEAUTY, CampaignKeyword.ART), BigDecimal.valueOf(10.0), BigDecimal.valueOf(100.0), CampaignStatus.ON,  Town.BIAŁYSTOK, 10, product4);
        Campaign campaign12a = new Campaign("Campaign 26", Arrays.asList(CampaignKeyword.ELECTRONICS, CampaignKeyword.GAMES), BigDecimal.valueOf(15.0), BigDecimal.valueOf(150.0), CampaignStatus.OFF, Town.ŁÓDŹ , 15, product4);
        Campaign campaign13a = new Campaign("Campaign 27", Arrays.asList(CampaignKeyword.BEAUTY, CampaignKeyword.ART), BigDecimal.valueOf(10.0), BigDecimal.valueOf(100.0), CampaignStatus.ON,  Town.BIAŁYSTOK, 10, product4);
        Campaign campaign14a = new Campaign("Campaign 28", Arrays.asList(CampaignKeyword.ELECTRONICS, CampaignKeyword.GAMES), BigDecimal.valueOf(15.0), BigDecimal.valueOf(150.0), CampaignStatus.OFF, Town.ŁÓDŹ , 15, product4);

        campaign1a.setUser(user);
        campaign2a.setUser(user);
        campaign3a.setUser(user);
        campaign4a.setUser(user);
        campaign5a.setUser(user);
        campaign6a.setUser(user);
        campaign7a.setUser(user);
        campaign8a.setUser(user);
        campaign9a.setUser(user);
        campaign10a.setUser(user);
        campaign11a.setUser(user);
        campaign12a.setUser(user);
        campaign13a.setUser(user);
        campaign14a.setUser(user);

        campaignRepository.save(campaign1a);
        campaignRepository.save(campaign2a);
        campaignRepository.save(campaign3a);
        campaignRepository.save(campaign4a);
        campaignRepository.save(campaign5a);
        campaignRepository.save(campaign6a);
        campaignRepository.save(campaign7a);
        campaignRepository.save(campaign8a);
        campaignRepository.save(campaign9a);
        campaignRepository.save(campaign10a);
        campaignRepository.save(campaign11a);
        campaignRepository.save(campaign12a);
        campaignRepository.save(campaign13a);
        campaignRepository.save(campaign14a);

    }
}