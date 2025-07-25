package com.example.DigitalWalletBackend.repository;

import com.example.DigitalWalletBackend.entity.WalletUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletUserRepository extends JpaRepository<WalletUser,Long> {

    boolean existsByAccountNumber(String accountNumber);

    Optional<WalletUser> findByAccountNumber(String accountNumber);

    Optional<WalletUser> findByEmail(String email);
}
