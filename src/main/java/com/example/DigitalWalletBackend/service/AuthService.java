package com.example.DigitalWalletBackend.service;


import com.example.DigitalWalletBackend.dto.LoginRequestDTO;

public interface AuthService {

    String login(String email,String password);
    String verifyOtp(String email,String password);
}
