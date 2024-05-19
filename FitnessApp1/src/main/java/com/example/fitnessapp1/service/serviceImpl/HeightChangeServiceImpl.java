package com.example.fitnessapp1.service.serviceImpl;

import com.example.fitnessapp1.entity.HeightChange;
import com.example.fitnessapp1.entity.Profile;
import com.example.fitnessapp1.repository.HeightChangeRepository;
import com.example.fitnessapp1.repository.ProfileRepository;
import com.example.fitnessapp1.repository.UserRepository;
import com.example.fitnessapp1.resource.request.HeightRequest;
import com.example.fitnessapp1.resource.response.HeightChangeResponse;
import com.example.fitnessapp1.service.HeightChangeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.fitnessapp1.mapper.HeightChangeMapper.HEIGHT_CHANGE_MAPPER;

@Service
@RequiredArgsConstructor
public class HeightChangeServiceImpl implements HeightChangeService {
    private final UserRepository userRepository;
    private final HeightChangeRepository heightChangesRepository;
    private final ProfileRepository profileRepository;

    @Override
    public void saveHeight(Float height, String userId) {
        HeightChange heightChanges = new HeightChange();
        heightChanges.setUser(userRepository.getReferenceById(userId));
        heightChanges.setDate(LocalDate.now());
        heightChanges.setHeight(height);

        heightChangesRepository.save(heightChanges);
    }

    @Transactional
    public void updateHeight(HeightRequest heightRequest) {
        String userId = userRepository.findByEmail(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        ).orElseThrow().getId();

        if (heightChangesRepository.existsByUserIdAndDate(userId, LocalDate.now())) {
            HeightChange heightChanges = heightChangesRepository.findByUserIdAndDate(userId, LocalDate.now());
            heightChanges.setHeight(heightRequest.getHeight());

            heightChangesRepository.save(heightChanges);
        } else {
            saveHeight(heightRequest.getHeight(), userId);
        }

        Profile profile = profileRepository.findByUserId(userId);
        profile.setHeight(heightRequest.getHeight());
        profileRepository.save(profile);
    }

    @Override
    public List<HeightChangeResponse> getHeightChanges() {
        return HEIGHT_CHANGE_MAPPER.toResponse(heightChangesRepository.getTopSevenById(
                userRepository.findByEmail(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName()
                ).orElseThrow().getId()
        ));
    }
}
