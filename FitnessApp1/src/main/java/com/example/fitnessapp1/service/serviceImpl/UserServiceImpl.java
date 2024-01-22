package com.example.fitnessapp1.service.serviceImpl;

import com.example.fitnessapp1.entity.User;
import com.example.fitnessapp1.repository.ProfileRepository;
import com.example.fitnessapp1.repository.UserRepository;
import com.example.fitnessapp1.resource.request.UpdateUserRequest;
import com.example.fitnessapp1.resource.response.LoginResponse;
import com.example.fitnessapp1.service.UserService;
import com.example.fitnessapp1.shared.exception.InvalidCredentialsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.fitnessapp1.mapper.UserMapper.USER_MAPPER;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public LoginResponse update(UpdateUserRequest userRequest, Long id) {
        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Unable to find user with id: " + id + "!"));

            user.setUsername(userRequest.getUsername());
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));

            return USER_MAPPER.toRegisterUserResponse(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            throw new InvalidCredentialsException("User with username: " + userRequest.getUsername() + " already exists!");
        }
    }

    @Override
    public void delete(Long id) {
        if (userRepository.existsById(id)) {
            profileRepository.deleteById(id);
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Unable to find user with id: " + id + "!");
        }
    }
}
