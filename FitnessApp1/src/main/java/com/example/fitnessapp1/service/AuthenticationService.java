package com.example.fitnessapp1.service;

import com.example.fitnessapp1.resource.request.AuthenticationRequest;
import com.example.fitnessapp1.resource.request.RegisterUserRequest;
import com.example.fitnessapp1.resource.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterUserRequest registerRequest);
    AuthenticationResponse authenticate(AuthenticationRequest authRequest);
}
