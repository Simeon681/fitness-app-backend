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
import com.example.fitnessapp1.service.ActivityStatService;
import com.example.fitnessapp1.service.AuthenticationService;
import com.example.fitnessapp1.shared.exception.InvalidCredentialsException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final ActivityStatService activityStatService;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse register(RegisterUserRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new InvalidCredentialsException("User with username: "
                    + registerRequest.getUsername() + " already exists!");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

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

        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtService.generateToken(user));

        return response;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );

        User user = userRepository.findByUsername(authRequest.getUsername()).orElseThrow();

        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtService.generateToken(user));

        return response;
    }
}
