package com.example.fitnessapp1.service.serviceImpl;

import com.example.fitnessapp1.repository.ActivityStatRepository;
import com.example.fitnessapp1.repository.MealStatRepository;
import com.example.fitnessapp1.repository.ProfileRepository;
import com.example.fitnessapp1.repository.UserRepository;
import com.example.fitnessapp1.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final MealStatRepository mealStatRepository;
    private final ActivityStatRepository activityStatRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    @Override
//    public LoginResponse update(UpdateUserRequest userRequest) {
//        try {
//            String id = userRepository.findByUsername(
//                    SecurityContextHolder
//                            .getContext()
//                            .getAuthentication()
//                            .getName()
//            ).orElseThrow().getId();
//            User user = userRepository.findById(id)
//                    .orElseThrow(() -> new EntityNotFoundException("Unable to find user with username: " + id + "!"));
//
//            user.setUsername(userRequest.getUsername());
//            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
//
//            return USER_MAPPER.toRegisterUserResponse(userRepository.save(user));
//        } catch (DataIntegrityViolationException e) {
//            throw new InvalidCredentialsException("User with username: "
//                    + userRequest.getUsername() + " already exists!");
//        }
//    }

    @Transactional
    @Override
    public void delete() {
        String id = userRepository.findByEmail(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        ).orElseThrow().getId();

        if (userRepository.existsById(id)) {
            mealStatRepository.deleteByUserId(id);
            activityStatRepository.deleteByUserId(id);
            profileRepository.deleteById(id);
            userRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Unable to find user with id: " + id + "!");
        }
    }
}
