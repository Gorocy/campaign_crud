package com.example.demo.controller;

import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getBalance() {
        BigDecimal balance = userService.getUserBalance();
        return ResponseEntity.ok(balance);
    }
}