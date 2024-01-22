package com.example.fitnessapp1.auth;

import com.example.fitnessapp1.config.JWTService;
import com.example.fitnessapp1.entity.Profile;
import com.example.fitnessapp1.entity.User;
import com.example.fitnessapp1.repository.ProfileRepository;
import com.example.fitnessapp1.repository.UserRepository;
import com.example.fitnessapp1.resource.request.RegisterUserRequest;
import com.example.fitnessapp1.shared.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterUserRequest registerRequest) {
        var user = User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();

        var profile = Profile.builder()
                .user(user)
                .dateOfBirth(registerRequest.getDateOfBirth())
                .gender(registerRequest.getGender())
                .height(registerRequest.getHeight())
                .weight(registerRequest.getWeight())
                .goalCalories(registerRequest.getGoalCalories())
                .goalWeight(registerRequest.getGoalWeight())
                .goalSteps(registerRequest.getGoalSteps())
                .goalWater(registerRequest.getGoalWater())
                .build();

        profileRepository.save(profile);
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        var user = userRepository.findByUsername(authRequest.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
