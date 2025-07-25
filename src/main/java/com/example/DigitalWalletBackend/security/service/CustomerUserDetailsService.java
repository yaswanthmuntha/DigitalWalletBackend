package com.example.DigitalWalletBackend.security.service;

import com.example.DigitalWalletBackend.entity.WalletUser;
import com.example.DigitalWalletBackend.repository.WalletUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private WalletUserRepository walletUserRepository;
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        WalletUser user = walletUserRepository.findByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));


        return User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
