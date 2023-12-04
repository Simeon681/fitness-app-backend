package com.example.fitnessapp1.service;

import com.example.fitnessapp1.resource.request.LoginUserRequest;
import com.example.fitnessapp1.resource.request.RegisterUserRequest;
import com.example.fitnessapp1.resource.response.LoginResponse;

public interface UserService {
    LoginResponse register(RegisterUserRequest registerUserRequest);
    LoginResponse login(LoginUserRequest loginUserRequest);
}
