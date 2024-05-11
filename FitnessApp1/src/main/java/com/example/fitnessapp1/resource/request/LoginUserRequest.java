package com.example.fitnessapp1.resource.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginUserRequest {
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Length(min = 8, max = 32)
    private String password;
}
