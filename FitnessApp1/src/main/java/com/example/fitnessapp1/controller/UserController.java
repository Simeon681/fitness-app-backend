package com.example.fitnessapp1.controller;

import com.example.fitnessapp1.resource.request.UpdateUserRequest;
import com.example.fitnessapp1.resource.response.LoginResponse;
import com.example.fitnessapp1.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PatchMapping()
    private ResponseEntity<LoginResponse> update(@Valid @RequestBody UpdateUserRequest updateRequest) {
        return ResponseEntity.ok(userService.update(updateRequest));
    }


    @Transactional
    @DeleteMapping()
    protected ResponseEntity<?> delete() {
        userService.delete();
        return ResponseEntity.noContent().build();
    }
}
