package com.example.fitnessapp1.controller;

import com.example.fitnessapp1.resource.request.LoginUserRequest;
import com.example.fitnessapp1.resource.request.RegisterUserRequest;
import com.example.fitnessapp1.resource.response.LoginResponse;
import com.example.fitnessapp1.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    private ResponseEntity<LoginResponse> registerUser(@Valid @RequestBody RegisterUserRequest request) {

        System.out.println(request.toString());

        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/login")
    private void loginUser(@Valid @RequestBody LoginUserRequest request) {
        System.out.println(request.toString());
    }

    @GetMapping("/profile")
    private void getProfile(@Valid @RequestBody RegisterUserRequest request) {
        System.out.println(request.toString());
    }
}
