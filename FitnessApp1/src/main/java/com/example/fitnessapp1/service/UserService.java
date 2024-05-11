package com.example.fitnessapp1.service;

import jakarta.transaction.Transactional;

public interface UserService {
    //LoginResponse update(UpdateUserRequest updateRequest);

    @Transactional
    void delete();
}
