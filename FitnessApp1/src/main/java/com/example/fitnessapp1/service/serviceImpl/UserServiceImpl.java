package com.example.fitnessapp1.service.serviceImpl;

import com.example.fitnessapp1.entity.Profile;
import com.example.fitnessapp1.entity.User;
import com.example.fitnessapp1.repository.ProfileRepository;
import com.example.fitnessapp1.repository.UserRepository;
import com.example.fitnessapp1.resource.request.LoginUserRequest;
import com.example.fitnessapp1.resource.request.RegisterUserRequest;
import com.example.fitnessapp1.resource.response.LoginResponse;
import com.example.fitnessapp1.service.UserService;
import com.example.fitnessapp1.shared.exception.InvalidCredentialsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.fitnessapp1.mapper.ProfileMapper.PROFILE_MAPPER;
import static com.example.fitnessapp1.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public LoginResponse register(RegisterUserRequest registerRequest) {
        try {
            User user = USER_MAPPER.fromRegisterRequest(registerRequest);
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            User savedUser = userRepository.save(user);

            Profile profile = PROFILE_MAPPER.fromProfileRequest(registerRequest);
            profile.setUser(savedUser);
            profileRepository.save(profile);

            return USER_MAPPER.toRegisterUserResponse(savedUser);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidCredentialsException("User with username " + registerRequest.getUsername() + " already exists!");
        }
    }

    @Override
    public LoginResponse login(LoginUserRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username!"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid password!");
        } else {
            return USER_MAPPER.toRegisterUserResponse(user);
        }
    }

    @Override
    public void delete(Long id) {
        if (userRepository.existsById(id)) {
            profileRepository.deleteById(id);
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Unable to find user with id " + id + "!");
        }
    }
}
