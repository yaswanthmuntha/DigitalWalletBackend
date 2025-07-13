package com.example.DigitalWalletBackend.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String doorNo;
    private String street;
    private String town;
    private String district;
    private String state;
    private String pinCode;

    @Override
    public String toString() {
        return doorNo + ", " + street + ", " + town + ", " + district + ", " + state + " - " + pinCode;
    }
}
