package com.example.fitnessapp1.service.serviceImpl;

import com.example.fitnessapp1.entity.Profile;
import com.example.fitnessapp1.entity.WeightChange;
import com.example.fitnessapp1.repository.ProfileRepository;
import com.example.fitnessapp1.repository.UserRepository;
import com.example.fitnessapp1.repository.WeightChangeRepository;
import com.example.fitnessapp1.resource.request.WeightRequest;
import com.example.fitnessapp1.resource.response.WeightChangeResponse;
import com.example.fitnessapp1.service.WeightChangeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.fitnessapp1.mapper.WeightChangeMapper.WEIGHT_CHANGE_MAPPER;

@Service
@RequiredArgsConstructor
public class WeightChangeServiceImpl implements WeightChangeService {
    private final UserRepository userRepository;
    private final WeightChangeRepository weightChangesRepository;
    private final ProfileRepository profileRepository;

    @Override
    public void saveWeight(Float weight, String userId) {
        WeightChange weightChanges = new WeightChange();
        weightChanges.setUser(userRepository.getReferenceById(userId));
        weightChanges.setDate(LocalDate.now());
        weightChanges.setWeight(weight);

        weightChangesRepository.save(weightChanges);
    }

    @Transactional
    public void updateWeight(WeightRequest weightRequest) {
        String userId = userRepository.findByEmail(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        ).orElseThrow().getId();

        if (weightChangesRepository.existsByUserIdAndDate(userId, LocalDate.now())) {
            WeightChange weightChanges = weightChangesRepository.findByUserIdAndDate(userId, LocalDate.now());
            weightChanges.setWeight(weightRequest.getWeight());

            weightChangesRepository.save(weightChanges);
        } else {
            saveWeight(weightRequest.getWeight(), userId);
        }

        Profile profile = profileRepository.findByUserId(userId);
        profile.setWeight(weightRequest.getWeight());
        profileRepository.save(profile);
    }

    @Override
    public List<WeightChangeResponse> getWeightChanges() {
        return WEIGHT_CHANGE_MAPPER.toResponse(weightChangesRepository.getTopSevenById(
                userRepository.findByEmail(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName()
                ).orElseThrow().getId()
        ));
    }
}
