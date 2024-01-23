package com.example.fitnessapp1.service.serviceImpl;

import com.example.fitnessapp1.entity.ActivityStat;
import com.example.fitnessapp1.entity.User;
import com.example.fitnessapp1.repository.ActivityStatRepository;
import com.example.fitnessapp1.repository.UserRepository;
import com.example.fitnessapp1.resource.request.ActivityStatResource;
import com.example.fitnessapp1.service.ActivityStatService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.fitnessapp1.mapper.ActivityStatMapper.ACTIVITY_STAT_MAPPER;

@Service
@RequiredArgsConstructor
public class ActivityStatServiceImpl implements ActivityStatService {
    private final ActivityStatRepository activityStatRepository;
    private final UserRepository userRepository;

    @Override
    public ActivityStatResource create(ActivityStatResource activityStatResource, Long id) {
        try {
            ActivityStat activityStat = ACTIVITY_STAT_MAPPER.fromActivityStatResource(activityStatResource);
            activityStat.setUser(userRepository.getReferenceById(id));
            activityStat.setDate(LocalDate.now());

            return ACTIVITY_STAT_MAPPER.toActivityStatResource(activityStatRepository.save(activityStat));
        } catch (Exception e) { // TODO: specify exception
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ActivityStat> searchAllByDate(LocalDate date) {
        return activityStatRepository.searchAllByDate(date);
    }

    @Override
    public ActivityStatResource update(ActivityStatResource activityStatResource, Long userId, Long id) {
        try {
            ActivityStat activityStat = activityStatRepository.getReferenceById(id);

            if (!activityStat.getDate().equals(LocalDate.now())) {
                create(activityStatResource, userId);
            }

            User user = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Unable to find user with id: " + id + "!"));

            activityStat.setUser(user);
            activityStat.setSteps(activityStatResource.getSteps());
            activityStat.setCalories(activityStatResource.getCalories());
            activityStat.setWater(activityStatResource.getWater());

            return ACTIVITY_STAT_MAPPER.toActivityStatResource(activityStatRepository.save(activityStat));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
