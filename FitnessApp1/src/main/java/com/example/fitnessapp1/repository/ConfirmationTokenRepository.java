package com.example.fitnessapp1.repository;

import com.example.fitnessapp1.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {
    ConfirmationToken findByToken(String token);
}
