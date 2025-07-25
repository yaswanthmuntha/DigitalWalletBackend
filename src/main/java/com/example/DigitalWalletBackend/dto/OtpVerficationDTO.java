package com.example.DigitalWalletBackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpVerficationDTO {
    private String email;
    private String otp;
}
