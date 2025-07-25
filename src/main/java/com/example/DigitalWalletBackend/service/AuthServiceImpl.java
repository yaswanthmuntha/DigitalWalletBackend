package com.example.DigitalWalletBackend.service;

import com.example.DigitalWalletBackend.entity.WalletUser;
import com.example.DigitalWalletBackend.exception.CustomerNotFoundException;
import com.example.DigitalWalletBackend.repository.WalletUserRepository;
import com.example.DigitalWalletBackend.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService{

    private final WalletUserRepository walletUserRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(
            WalletUserRepository walletUserRepository,
            JwtTokenProvider jwtTokenProvider,
            PasswordEncoder passwordEncoder
    ) {
        this.walletUserRepository = walletUserRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(String email,String password) {
        WalletUser walletUser = walletUserRepository.findByEmail(email).
                orElseThrow(() -> new CustomerNotFoundException("User With Email Not Found"));

        if(!passwordEncoder.matches(password,walletUser.getPassword())){
            throw new RuntimeException("Invalid email or password");
        }

        String otp = generateOtp();
        walletUser.setOtp(otp);
        walletUser.setOtpGeneratedAt(LocalDateTime.now());

        walletUserRepository.save(walletUser);


        System.out.println("otp generated is "+otp);
        return "OTP has been sent to your registered mobile number (check console for now)";
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // 6-digit number
        return String.valueOf(otp);
    }

    @Override
    public String verifyOtp(String email, String otp) {

        WalletUser walletUser = walletUserRepository.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException("User With Email Not Found"));

        if (walletUser.getOtp() == null || !walletUser.getOtp().equals(otp)) {
            throw new RuntimeException("Invalid or Expired OTP");
        }

        walletUser.setOtp(null);
        walletUserRepository.save(walletUser);

        String token = jwtTokenProvider.generateToken(email, String.valueOf(walletUser.getRole()));
        return token;
    }



}
