package com.example.DigitalWalletBackend.entity;

import com.example.DigitalWalletBackend.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wallet_user")
public class WalletUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dob;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    @Embedded
    private Address address;

    private String aadharNo;
    private String panCardNo;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private BigDecimal walletBalance;

    private Boolean isActive;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Optional: used if you want to persist OTP temporarily
    @Column
    private String otp;

    @Column
    private LocalDateTime otpGeneratedAt;

    @Override
    public String toString() {
        return "WalletUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", dob=" + dob +
                ", accountNumber='" + accountNumber + '\'' +
                ", address=" + address +
                ", aadharNo='" + aadharNo + '\'' +
                ", panCardNo='" + panCardNo + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", walletBalance=" + walletBalance +
                ", isActive=" + isActive +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", role='" + role + '\'' +
                '}';
    }
}
