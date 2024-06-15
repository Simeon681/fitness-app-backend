package com.example.fitnessapp1.service.serviceImpl;

import com.example.fitnessapp1.config.JWTService;
import com.example.fitnessapp1.entity.Profile;
import com.example.fitnessapp1.entity.User;
import com.example.fitnessapp1.repository.ProfileRepository;
import com.example.fitnessapp1.repository.UserRepository;
import com.example.fitnessapp1.resource.request.ActivityStatResource;
import com.example.fitnessapp1.resource.request.AuthenticationRequest;
import com.example.fitnessapp1.resource.request.RegisterUserRequest;
import com.example.fitnessapp1.resource.response.AuthenticationResponse;
import com.example.fitnessapp1.service.*;
import com.example.fitnessapp1.shared.exception.InvalidCredentialsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.example.fitnessapp1.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final ActivityStatService activityStatService;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSenderService emailSenderService;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final HeightChangeService heightChangesService;
    private final WeightChangeService weightChangesService;

    @Transactional
    public AuthenticationResponse register(RegisterUserRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new InvalidCredentialsException("User with email \""
                    + registerRequest.getEmail() + "\" already exists!");
        }

        User user = USER_MAPPER.fromRegisterRequest(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        String refreshToken = jwtService.generateRefreshToken(user);
        user.setRefreshToken(refreshToken);

        Profile profile = new Profile();
        profile.setUser(user);
        profile.setDateOfBirth(
                LocalDate.parse(registerRequest.getDateOfBirth(),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"))
        );
        profile.setGender(registerRequest.getGender());
        profile.setHeight(registerRequest.getHeight());
        profile.setWeight(registerRequest.getWeight());
        profile.setActivityLevel(registerRequest.getActivityLevel());
        profile.setWeightGoal(registerRequest.getWeightGoal());
        profile.setGoalCalories(registerRequest.getGoalCalories());
        profile.setGoalProtein(registerRequest.getGoalProtein());
        profile.setGoalCarbs(registerRequest.getGoalCarbs());
        profile.setGoalFat(registerRequest.getGoalFat());
        profile.setGoalWater(registerRequest.getGoalWater());
        profile.setGoalSteps(registerRequest.getGoalSteps());

        userRepository.save(user);
        profileRepository.save(profile);

        activityStatService.create(new ActivityStatResource(), user.getId());

        heightChangesService.saveHeight(registerRequest.getHeight(), user.getId());
        weightChangesService.saveWeight(registerRequest.getWeight(), user.getId());

        String confirmationToken = confirmationTokenService.createConfirmationToken(user);
        String link = "http://localhost:8080/api/v1/auth/register/confirm?token=" + confirmationToken;
        emailSenderService.sendEmail(
                registerRequest.getEmail(),
                emailSenderService.buildEmail(registerRequest.getUsername(), link)
        );

        AuthenticationResponse response = new AuthenticationResponse();
        response.setAccessToken(jwtService.generateToken(user));
        response.setRefreshToken(refreshToken);

        return response;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
        User user = userRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Email not found!"));

        if (!user.isEnabled()) {
            throw new InvalidCredentialsException("Email not confirmed!");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getEmail(),
                        authRequest.getPassword()
                )
        );

        String refreshToken = jwtService.generateRefreshToken(user);
        AuthenticationResponse response = new AuthenticationResponse();
        response.setAccessToken(jwtService.generateToken(user));
        response.setRefreshToken(refreshToken);
        user.setRefreshToken(refreshToken);
        userRepository.save(user);

        return response;
    }

    @Override
    public void autoLogin(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String accessToken;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        accessToken = authHeader.substring(7);
        userEmail = jwtService.extractEmail(accessToken);
        if (userEmail != null) {
            User user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(accessToken, user)) {
                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                user.getEmail(),
                                null,
                                user.getAuthorities()
                        )
                );
            }
        }
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }

        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractEmail(refreshToken);
        if (userEmail != null) {
            User user = this.userRepository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                String accessToken = jwtService.generateToken(user);
                AuthenticationResponse authResponse = new AuthenticationResponse();
                authResponse.setAccessToken(accessToken);
                authResponse.setRefreshToken(refreshToken);

                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    @Override
    public void logout() {
        User user = userRepository.findByEmail(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        ).orElseThrow(
                () -> new InvalidCredentialsException("User not found!")
        );

        user.setRefreshToken(null);
        userRepository.save(user);
    }
}
