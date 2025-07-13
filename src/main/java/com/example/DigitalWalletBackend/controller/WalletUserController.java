package com.example.DigitalWalletBackend.controller;

import com.example.DigitalWalletBackend.dto.WalletUserResponseDTO;
import com.example.DigitalWalletBackend.entity.WalletUser;
import com.example.DigitalWalletBackend.service.WalletUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/users")
public class WalletUserController {

    @Autowired
    private WalletUserService walletUserService;

    @PostMapping
    public ResponseEntity<WalletUserResponseDTO> createUser(@RequestBody WalletUser walletUser){

        WalletUserResponseDTO walletUserResponseDTO = walletUserService.createUser(walletUser);
        // response entity is for sending status code
        return new ResponseEntity<>(walletUserResponseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/account/{accountNumber}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable String accountNumber){
        BigDecimal balance = walletUserService.getBalanceByAccountNumber(accountNumber);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }




}
