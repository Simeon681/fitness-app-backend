package com.example.fitnessapp1.service;

import com.example.fitnessapp1.entity.User;
import jakarta.transaction.Transactional;

public interface ConfirmationTokenService {
    String createConfirmationToken(User user);

    @Transactional
    String confirmToken(String token);
}
