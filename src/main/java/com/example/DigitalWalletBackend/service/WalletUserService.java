package com.example.DigitalWalletBackend.service;

import com.example.DigitalWalletBackend.dto.WalletUserResponseDTO;
import com.example.DigitalWalletBackend.entity.WalletUser;

import java.math.BigDecimal;


public interface WalletUserService {

    WalletUserResponseDTO createUser(WalletUser walletUser);

    BigDecimal getBalanceByAccountNumber(String accountNumber);
    String generateAccountNumber();
}
