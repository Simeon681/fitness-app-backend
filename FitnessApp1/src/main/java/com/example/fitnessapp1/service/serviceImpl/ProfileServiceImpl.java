package com.example.fitnessapp1.service.serviceImpl;

import com.example.fitnessapp1.entity.Profile;
import com.example.fitnessapp1.repository.ProfileRepository;
import com.example.fitnessapp1.resource.request.ProfileResource;
import com.example.fitnessapp1.service.ProfileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.fitnessapp1.mapper.ProfileMapper.PROFILE_MAPPER;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    @Override
    public ProfileResource getById(Long id) {
        Profile profile = profileRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Unable to find profile with id: " + id + "!"));

        return PROFILE_MAPPER.toProfileResource(profile);
    }

    @Override
    public ProfileResource update(ProfileResource profileResource, Long id) {
        try {
            Profile profile = profileRepository.getReferenceById(id);

            profile.setDateOfBirth(profileResource.getDateOfBirth());
            profile.setGender(profileResource.getGender());
            profile.setHeight(profileResource.getHeight());
            profile.setWeight(profileResource.getWeight());
            profile.setGoalCalories(profileResource.getGoalCalories());
            profile.setGoalWeight(profileResource.getGoalWeight());
            profile.setGoalSteps(profileResource.getGoalSteps());
            profile.setGoalWater(profileResource.getGoalWater());

            return PROFILE_MAPPER.toProfileResource(profileRepository.save(profile));
        } catch (Exception e) {
            throw new EntityNotFoundException("Profile not found with id: " + id + "!");
        }
    }
}
