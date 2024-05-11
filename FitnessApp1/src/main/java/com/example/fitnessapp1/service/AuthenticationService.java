package com.example.fitnessapp1.service;

import com.example.fitnessapp1.resource.request.AuthenticationRequest;
import com.example.fitnessapp1.resource.request.RegisterUserRequest;
import com.example.fitnessapp1.resource.response.AuthenticationResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

import java.io.IOException;

public interface AuthenticationService {
    @Transactional
    AuthenticationResponse register(RegisterUserRequest registerRequest);
    AuthenticationResponse authenticate(AuthenticationRequest authRequest);
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
    void logout();
}
