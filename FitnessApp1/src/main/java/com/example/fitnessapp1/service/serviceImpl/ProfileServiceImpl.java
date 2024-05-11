package com.example.fitnessapp1.service.serviceImpl;

import com.example.fitnessapp1.entity.Profile;
import com.example.fitnessapp1.repository.ProfileRepository;
import com.example.fitnessapp1.repository.UserRepository;
import com.example.fitnessapp1.resource.request.ProfileResource;
import com.example.fitnessapp1.resource.response.ProfileResponse;
import com.example.fitnessapp1.service.ProfileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.example.fitnessapp1.mapper.ProfileMapper.PROFILE_MAPPER;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Override
    public ProfileResource update(ProfileResource profileResource) {
        try {
            String id = userRepository.findByEmail(
                    SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            .getName()
            ).orElseThrow().getId();

            Profile profile = profileRepository.getReferenceById(id);

            profile.setDateOfBirth(LocalDate.parse(profileResource.getDateOfBirth()));
            profile.setGender(profileResource.getGender());
            profile.setHeight(profileResource.getHeight());
            profile.setWeight(profileResource.getWeight());
            profile.setActivityLevel(profileResource.getActivityLevel());
            profile.setWeightGoal(profileResource.getWeightGoal());
            profile.setGoalSteps(profileResource.getGoalSteps());

            return PROFILE_MAPPER.toProfileResource(profileRepository.save(profile));
        } catch (Exception e) {
            throw new EntityNotFoundException("Profile not found!");
        }
    }

    @Override
    public ProfileResponse getProfile() {
        String id = userRepository.findByEmail(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        ).orElseThrow().getId();
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find profile with id: " + id + "!"));

        return PROFILE_MAPPER.toProfileResponse(profile);
    }
}
