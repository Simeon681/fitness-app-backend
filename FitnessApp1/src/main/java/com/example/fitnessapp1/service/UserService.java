package com.example.fitnessapp1.service;

import com.example.fitnessapp1.resource.request.UpdateUserRequest;
import com.example.fitnessapp1.resource.response.LoginResponse;
import jakarta.transaction.Transactional;

public interface UserService {
    LoginResponse update(UpdateUserRequest updateRequest);

    @Transactional
    void delete();
}
