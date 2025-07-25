package com.example.DigitalWalletBackend.service;

import com.example.DigitalWalletBackend.dto.WalletUserResponseDTO;
import com.example.DigitalWalletBackend.entity.Address;
import com.example.DigitalWalletBackend.entity.WalletUser;
import com.example.DigitalWalletBackend.enums.Role;
import com.example.DigitalWalletBackend.exception.CustomerNotFoundException;
import com.example.DigitalWalletBackend.repository.WalletUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class WalletUserServiceImpl implements WalletUserService{

    @Autowired
    private WalletUserRepository walletUserRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public WalletUserServiceImpl(WalletUserRepository walletUserRepository) {
        this.walletUserRepository = walletUserRepository;
    }

    @Override
    public WalletUserResponseDTO createUser(WalletUser userInput) {
        WalletUser user = new WalletUser();
        user.setFirstName(userInput.getFirstName());
        user.setLastName(userInput.getLastName());
        user.setGender(userInput.getGender());
        user.setDob(userInput.getDob());
        user.setEmail(userInput.getEmail());
        user.setPhoneNumber(userInput.getPhoneNumber());
        user.setAadharNo(userInput.getAadharNo());
        user.setPanCardNo(userInput.getPanCardNo());

        // Hashing password
        user.setPassword(passwordEncoder.encode(userInput.getPassword()));
        // setting default role
        user.setRole(Role.USER);

        // Embedded Address
        Address address = userInput.getAddress(); // or build it manually
        user.setAddress(address);

        // System-generated fields
        user.setAccountNumber(generateAccountNumber());
        user.setWalletBalance(BigDecimal.ZERO);
        user.setIsActive(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // Save and return response
        WalletUser saved = walletUserRepository.save(user);
        return new WalletUserResponseDTO(
                saved.getFirstName(),
                saved.getLastName(),
                saved.getEmail(),
                saved.getPhoneNumber(),
                saved.getAccountNumber(),
                saved.getWalletBalance()
        );
    }

    @Override
    public BigDecimal getBalanceByAccountNumber(String accountNumber) {
        WalletUser user = walletUserRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "No user found with account number: " + accountNumber));


        return user.getWalletBalance();

    }

    @Override
    public String generateAccountNumber() {
        String accountNumber;
        do {
            accountNumber = String.valueOf(generate12DigitNumber());
        } while (walletUserRepository.existsByAccountNumber(accountNumber));
        return accountNumber;
    }

    private long generate12DigitNumber() {
        long min = 100000000000L; // 12 digits, doesn't start with 0
        long max = 999999999999L;
        return min + (long)(Math.random() * (max - min));
    }

}
