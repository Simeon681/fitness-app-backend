package com.example.fitnessapp1.controller;

import com.example.fitnessapp1.service.AuthenticationService;
import com.example.fitnessapp1.resource.request.AuthenticationRequest;
import com.example.fitnessapp1.resource.request.RegisterUserRequest;
import com.example.fitnessapp1.resource.response.AuthenticationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterUserRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest authRequest) {
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }
}
