package com.example.fitnessapp1.resource.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UpdateUserRequest {
    @NotEmpty
    @Length(min = 6, max = 32)
    private String username;

    @NotEmpty
    @Length(min = 8, max = 32)
    private String password;
}
