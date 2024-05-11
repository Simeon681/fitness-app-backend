package com.example.fitnessapp1.controller;

import com.example.fitnessapp1.resource.request.ProfileResource;
import com.example.fitnessapp1.resource.response.ProfileResponse;
import com.example.fitnessapp1.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PatchMapping
    private ResponseEntity<ProfileResource> update(@Valid @RequestBody ProfileResource profileResource) {
        return ResponseEntity.ok(profileService.update(profileResource));
    }

    @GetMapping
    private ResponseEntity<ProfileResponse> getProfile() {
        return ResponseEntity.ok(profileService.getProfile());
    }
}
