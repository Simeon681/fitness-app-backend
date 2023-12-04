package com.example.fitnessapp1.service.serviceImpl;

import com.example.fitnessapp1.repository.UserRepository;
import com.example.fitnessapp1.service.UserService;
import com.example.fitnessapp1.resource.request.LoginUserRequest;
import com.example.fitnessapp1.resource.request.RegisterUserRequest;
import com.example.fitnessapp1.resource.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public LoginResponse register(RegisterUserRequest registerUserRequest) {
        return null;
    }

    @Override
    public LoginResponse login(LoginUserRequest loginUserRequest) {
        return null;
    }
}
