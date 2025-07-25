package com.example.DigitalWalletBackend.controller;

import com.example.DigitalWalletBackend.dto.LoginRequestDTO;
import com.example.DigitalWalletBackend.dto.OtpVerficationDTO;
import com.example.DigitalWalletBackend.dto.WalletUserResponseDTO;
import com.example.DigitalWalletBackend.entity.WalletUser;
import com.example.DigitalWalletBackend.service.AuthService;
import com.example.DigitalWalletBackend.service.WalletUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private WalletUserService walletUserService;


    @PostMapping("/login")
    private ResponseEntity<String> login(@RequestBody LoginRequestDTO loginRequestDTO){
        String message = authService.login(loginRequestDTO.getEmail(),loginRequestDTO.getPassword());
        return ResponseEntity.ok(message);
    }

    @PostMapping("/register")
    public ResponseEntity<WalletUserResponseDTO> registerUser(@RequestBody WalletUser walletUser) {
        WalletUserResponseDTO walletUserResponseDTO = walletUserService.createUser(walletUser);
        return new ResponseEntity<>(walletUserResponseDTO, HttpStatus.CREATED);
    }


    @PostMapping("/verify-otp")
    private ResponseEntity<String> verifyOtp(@RequestBody OtpVerficationDTO otpVerficationDTO){
        String token = authService.verifyOtp(otpVerficationDTO.getEmail(), otpVerficationDTO.getOtp());


        return ResponseEntity.ok(token);
    }


}
