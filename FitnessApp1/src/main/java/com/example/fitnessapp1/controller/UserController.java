package com.example.fitnessapp1.controller;

import com.example.fitnessapp1.resource.request.LoginUserRequest;
import com.example.fitnessapp1.resource.request.RegisterUserRequest;
import com.example.fitnessapp1.resource.request.UpdateUserRequest;
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
    private ResponseEntity<LoginResponse> registerUser(@Valid @RequestBody RegisterUserRequest registerRequest) {
        return ResponseEntity.ok(userService.register(registerRequest));
    }

    @PostMapping("/login")
    private ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginUserRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @PatchMapping("/{id}")
    private ResponseEntity<LoginResponse> updateUser(@Valid @RequestBody UpdateUserRequest updateRequest, @PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.update(updateRequest, id));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
