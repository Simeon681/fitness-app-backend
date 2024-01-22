package com.example.fitnessapp1.service;

import com.example.fitnessapp1.resource.request.LoginUserRequest;
import com.example.fitnessapp1.resource.request.RegisterUserRequest;
import com.example.fitnessapp1.resource.request.UpdateUserRequest;
import com.example.fitnessapp1.resource.response.LoginResponse;

public interface UserService {
    LoginResponse update(UpdateUserRequest updateRequest, Long id);
    void delete(Long id);
}
