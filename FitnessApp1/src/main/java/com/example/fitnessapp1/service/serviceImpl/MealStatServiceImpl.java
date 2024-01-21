package com.example.fitnessapp1.service.serviceImpl;

import com.example.fitnessapp1.entity.MealStat;
import com.example.fitnessapp1.repository.MealRepository;
import com.example.fitnessapp1.repository.MealStatRepository;
import com.example.fitnessapp1.repository.UserRepository;
import com.example.fitnessapp1.resource.request.AddMealStatRequest;
import com.example.fitnessapp1.resource.response.MealStatResponse;
import com.example.fitnessapp1.service.MealStatService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.example.fitnessapp1.mapper.MealStatMapper.MEAL_STAT_MAPPER;

@Service
@RequiredArgsConstructor
public class MealStatServiceImpl implements MealStatService {
    private final MealStatRepository mealStatRepository;
    private final MealRepository mealRepository;
    private final UserRepository userRepository;

    public MealStatResponse create(AddMealStatRequest addMealStatRequest, Long id, Long mealId) {
        try {
            MealStat mealStat = MEAL_STAT_MAPPER.fromMealStatRequest(addMealStatRequest);
            mealStat.setUser(userRepository.getReferenceById(id));
            mealStat.setMeal(mealRepository.getReferenceById(mealId));
            mealStat.setDate(LocalDate.now());
            mealStatRepository.save(mealStat);

            return MEAL_STAT_MAPPER.toMealStatResponse(mealStat);
        } catch (EntityNotFoundException e) { // TODO: specify exception
            throw new EntityNotFoundException("Unable to find meal with id: " + mealId + "!");
        }
    }

    @Override
    public void delete(Long id) {
        if (mealStatRepository.existsById(id)) {
            mealStatRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Unable to find meal stat with id: " + id + "!");
        }
    }
}
