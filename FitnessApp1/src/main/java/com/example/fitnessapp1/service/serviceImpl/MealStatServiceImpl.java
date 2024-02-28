package com.example.fitnessapp1.service.serviceImpl;

import com.example.fitnessapp1.entity.ActivityStat;
import com.example.fitnessapp1.entity.MealStat;
import com.example.fitnessapp1.repository.ActivityStatRepository;
import com.example.fitnessapp1.repository.MealRepository;
import com.example.fitnessapp1.repository.MealStatRepository;
import com.example.fitnessapp1.repository.UserRepository;
import com.example.fitnessapp1.resource.request.ActivityStatResource;
import com.example.fitnessapp1.resource.request.AddMealStatRequest;
import com.example.fitnessapp1.resource.response.MealStatResponse;
import com.example.fitnessapp1.service.ActivityStatService;
import com.example.fitnessapp1.service.MealStatService;
import com.example.fitnessapp1.shared.MealType;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.fitnessapp1.mapper.ActivityStatMapper.ACTIVITY_STAT_MAPPER;
import static com.example.fitnessapp1.mapper.MealStatMapper.MEAL_STAT_MAPPER;

@Service
@RequiredArgsConstructor
public class MealStatServiceImpl implements MealStatService {
    private final MealStatRepository mealStatRepository;
    private final MealRepository mealRepository;
    private final UserRepository userRepository;
    private final ActivityStatService activityStatService;
    private final ActivityStatRepository activityStatRepository;

    public MealStatResponse create(AddMealStatRequest addMealStatRequest, Long mealId) {
        try {
            Long id = userRepository.findByUsername(
                    SecurityContextHolder
                            .getContext()
                            .getAuthentication()
                            .getName()
            ).orElseThrow().getId();

            MealStat mealStat = MEAL_STAT_MAPPER.fromMealStatRequest(addMealStatRequest);
            mealStat.setUser(userRepository.getReferenceById(id));
            mealStat.setMeal(mealRepository.getReferenceById(mealId));
            mealStat.setPortion(addMealStatRequest.getPortion());
            mealStat.setDate(LocalDate.now());

            ActivityStat activityStat = activityStatRepository.findByUserIdAndDate(id, LocalDate.now());
            ActivityStatResource activityStatResource;

            activityStatResource = ACTIVITY_STAT_MAPPER.toActivityStatResource(activityStat);
            activityStatResource
                    .setCalories(activityStatResource.getCalories(mealStat));
            activityStatResource
                    .setProtein(activityStatResource.getProtein(mealStat));
            activityStatResource
                    .setCarbs(activityStatResource.getCarbs(mealStat));
            activityStatResource
                    .setFat(activityStatResource.getFat(mealStat));
            activityStatService.update(activityStatResource);

            mealStatRepository.save(mealStat);

            return MEAL_STAT_MAPPER.toMealStatResponse(mealStat);
        } catch (EntityNotFoundException e) { // TODO: specify exception
            throw new EntityNotFoundException("Unable to find meal with id: " + mealId + "!");
        }
    }

    @Override
    public List<MealStatResponse> searchMealStatByUserIdAndDateAndType(MealType type) {
        Long id = userRepository.findByUsername(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        ).orElseThrow().getId();

        return MEAL_STAT_MAPPER.
                toMealStatResponses(mealStatRepository.findByUserIdAndDateAndType(id, LocalDate.now(), type));
    }

    @Override
    public List<MealStatResponse> searchMealStatByDate(LocalDate date) {
        Long id = userRepository.findByUsername(
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getName()
        ).orElseThrow().getId();

        return MEAL_STAT_MAPPER.
                toMealStatResponses(mealStatRepository.findByUserIdAndDate(id, date));
    }


    @Override
    public void delete(Long id) {
        if (mealStatRepository.existsById(id)) {
            MealStat mealStat = mealStatRepository.getReferenceById(id);

            if (!mealStat.getDate().equals(LocalDate.now())) {
                throw new EntityNotFoundException("Unable to delete meal stat from previous date!");
            }

            ActivityStat activityStat = activityStatRepository
                    .findByUserIdAndDate(mealStat.getUser().getId(), mealStat.getDate());

            ActivityStatResource activityStatResource = ACTIVITY_STAT_MAPPER.toActivityStatResource(activityStat);
            activityStatResource
                    .setCalories(activityStatResource.getCalories() - activityStatResource.getCalories(mealStat));
            activityStatResource
                    .setProtein(activityStatResource.getProtein() - activityStatResource.getProtein(mealStat));
            activityStatResource
                    .setCarbs(activityStatResource.getCarbs() - activityStatResource.getCarbs(mealStat));
            activityStatResource
                    .setFat(activityStatResource.getFat() - activityStatResource.getFat(mealStat));

            activityStatService.update(activityStatResource);

            mealStatRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Unable to find meal stat with id: " + id + "!");
        }
    }
}
