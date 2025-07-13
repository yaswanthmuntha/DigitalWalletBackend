package com.example.DigitalWalletBackend.dto;

import java.math.BigDecimal;

public class WalletUserResponseDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String accountNumber;
    private BigDecimal walletBalance;


    public WalletUserResponseDTO(String firstName, String lastName, String email,
                                 String phoneNumber, String accountNumber, BigDecimal walletBalance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;
        this.walletBalance = walletBalance;
    }


    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAccountNumber() { return accountNumber; }
    public BigDecimal getWalletBalance() { return walletBalance; }
}
