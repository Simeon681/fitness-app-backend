package com.example.fitnessapp1.resource.response;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginResponse {
    private Long id;

    @NotEmpty
    @Length(min = 6, max = 32)
    private String username;
}
