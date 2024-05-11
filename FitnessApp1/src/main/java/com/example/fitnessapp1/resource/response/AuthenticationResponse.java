package com.example.fitnessapp1.resource.response;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthenticationResponse {
    @NotEmpty
    private String accessToken;

    @NotEmpty
    private String refreshToken;
}
