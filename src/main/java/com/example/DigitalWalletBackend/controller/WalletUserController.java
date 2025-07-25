package com.example.DigitalWalletBackend.controller;

import com.example.DigitalWalletBackend.dto.WalletUserResponseDTO;
import com.example.DigitalWalletBackend.entity.WalletUser;
import com.example.DigitalWalletBackend.service.WalletUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/users")
public class WalletUserController {

    @Autowired
    private WalletUserService walletUserService;

    @GetMapping("/account/{accountNumber}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable String accountNumber){
        BigDecimal balance = walletUserService.getBalanceByAccountNumber(accountNumber);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

    @GetMapping("/balance")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> getWalletBalance() {
        return ResponseEntity.ok("Your wallet balance is ₹5000");
    }

}
