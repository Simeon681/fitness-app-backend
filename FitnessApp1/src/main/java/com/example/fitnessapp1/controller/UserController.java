package com.example.fitnessapp1.controller;

import com.example.fitnessapp1.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

//    @PatchMapping()
//    private ResponseEntity<LoginResponse> update(@Valid @RequestBody UpdateUserRequest updateRequest) {
//        return ResponseEntity.ok(userService.update(updateRequest));
//    }


    @Transactional
    @DeleteMapping
    protected ResponseEntity<?> delete() {
        userService.delete();
        return ResponseEntity.noContent().build();
    }
}
