package com.example.fitnessapp1.service.serviceImpl;

import com.example.fitnessapp1.entity.ConfirmationToken;
import com.example.fitnessapp1.entity.User;
import com.example.fitnessapp1.repository.ConfirmationTokenRepository;
import com.example.fitnessapp1.repository.UserRepository;
import com.example.fitnessapp1.service.ConfirmationTokenService;
import com.example.fitnessapp1.shared.exception.ConflictException;
import com.example.fitnessapp1.shared.exception.InvalidCredentialsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;

    @Override
    public String createConfirmationToken(User user) {
        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setToken(token);
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        confirmationToken.setUser(user);

        confirmationTokenRepository.save(confirmationToken);

        return token;
    }

    @Override
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token);

        if (confirmationToken.getConfirmedAt() != null) {
            throw new ConflictException("Email already confirmed!");
        }

        if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new InvalidCredentialsException("Confirmation token expired!");
        }

        confirmationToken.setConfirmedAt(LocalDateTime.now());
        confirmationTokenRepository.save(confirmationToken);

        User user = confirmationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);

        return "Confirmed!";
    }
}
