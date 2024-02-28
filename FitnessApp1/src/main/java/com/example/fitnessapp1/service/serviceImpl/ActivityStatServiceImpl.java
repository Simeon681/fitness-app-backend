package com.example.fitnessapp1.service.serviceImpl;

import com.example.fitnessapp1.entity.ActivityStat;
import com.example.fitnessapp1.entity.User;
import com.example.fitnessapp1.repository.ActivityStatRepository;
import com.example.fitnessapp1.repository.UserRepository;
import com.example.fitnessapp1.resource.request.ActivityStatResource;
import com.example.fitnessapp1.service.ActivityStatService;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public ActivityStatResource create(ActivityStatResource activityStatResource, @Nullable Long userId) {
        try {
            Long id;
            if (userId == null) {
                id = userRepository.findByUsername(
                        SecurityContextHolder
                                .getContext()
                                .getAuthentication()
                                .getName()
                ).orElseThrow().getId();
            } else {
                id = userId;
            }

            ActivityStat activityStat = ACTIVITY_STAT_MAPPER.fromActivityStatResource(activityStatResource);
            activityStat.setUser(userRepository.getReferenceById(id));
            activityStat.setDate(LocalDate.now());
            activityStat.setSteps(0);
            activityStat.setCalories(0);
            activityStat.setProtein(0f);
            activityStat.setCarbs(0f);
            activityStat.setFat(0f);
            activityStat.setWater(0f);

            return ACTIVITY_STAT_MAPPER.toActivityStatResource(activityStatRepository.save(activityStat));
        } catch (Exception e) { // TODO: specify exception
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    @Scheduled(cron = "00 00 00 * * *") // every day at midnight
    public void executeDailyTask() {
        ActivityStatResource activityStatResource = new ActivityStatResource();
        activityStatResource.setSteps(0);
        activityStatResource.setCalories(0);
        activityStatResource.setProtein(0f);
        activityStatResource.setCarbs(0f);
        activityStatResource.setFat(0f);
        activityStatResource.setWater(0f);

        for (User user : userRepository.getAll()) {
            create(activityStatResource, user.getId());
        }
    }

    @Override
    public List<ActivityStat> searchAllByDate(LocalDate date) {
        return activityStatRepository.searchAllByDate(date);
    }

    @Override
    public ActivityStat findByUserIdAndDate() {
        Long id = userRepository.findByUsername(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        ).orElseThrow().getId();

        return activityStatRepository.findByUserIdAndDate(id, LocalDate.now());
    }

    @Override
    public ActivityStatResource update(ActivityStatResource activityStatResource) {
        try {
            Long userId = userRepository.findByUsername(
                    SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            .getName()
            ).orElseThrow().getId();

            ActivityStat activityStat = activityStatRepository.findByUserIdAndDate(userId, LocalDate.now());

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new EntityNotFoundException("Unable to find user with id: " + userId + "!"));

            activityStat.setUser(user);

            if (activityStatResource.getCalories() != null) {
                activityStat.setCalories(activityStatResource.getCalories());
                activityStat.setProtein(activityStatResource.getProtein());
                activityStat.setCarbs(activityStatResource.getCarbs());
                activityStat.setFat(activityStatResource.getFat());
            } else {
                if (activityStatResource.getSteps() != 0) {
                    activityStat.setSteps(activityStatResource.getSteps());
                }
                activityStat.setWater(activityStat.getWater() + activityStatResource.getWater());
            }

            return ACTIVITY_STAT_MAPPER.toActivityStatResource(activityStatRepository.save(activityStat));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
