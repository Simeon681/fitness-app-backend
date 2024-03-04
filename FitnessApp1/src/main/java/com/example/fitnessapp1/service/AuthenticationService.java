package com.example.fitnessapp1.service;

import com.example.fitnessapp1.resource.request.AuthenticationRequest;
import com.example.fitnessapp1.resource.request.RegisterUserRequest;
import com.example.fitnessapp1.resource.response.AuthenticationResponse;
import jakarta.transaction.Transactional;

public interface AuthenticationService {
    @Transactional
    AuthenticationResponse register(RegisterUserRequest registerRequest);
    AuthenticationResponse authenticate(AuthenticationRequest authRequest);
}
