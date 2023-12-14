package com.example.fitnessapp1.service.serviceImpl;

import com.example.fitnessapp1.entity.Meal;
import com.example.fitnessapp1.repository.MealRepository;
import com.example.fitnessapp1.resource.request.AddMealRequest;
import com.example.fitnessapp1.resource.response.MealResponse;
import com.example.fitnessapp1.service.MealService;
import com.example.fitnessapp1.shared.exception.InvalidCredentialsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import static com.example.fitnessapp1.mapper.MealMapper.MEAL_MAPPER;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {
    private final MealRepository mealRepository;

    @Override
    public MealResponse add(AddMealRequest addMealRequest) {
        try {
            Meal meal = MEAL_MAPPER.fromMealRequest(addMealRequest);
            mealRepository.save(meal);

            return MEAL_MAPPER.toMealResponse(meal);
        } catch (DataIntegrityViolationException e) {
            throw new InvalidCredentialsException("Meal with name " + addMealRequest.getName() + " already exists!");
        }
    }

    @Override
    public MealResponse getById(Long id) {
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find meal with id " + id + "!"));

        return MEAL_MAPPER.toMealResponse(meal);
    }

    @Override
    public MealResponse getByName(String name) {
        Meal meal = mealRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find meal with name " + name + "!"));

        return MEAL_MAPPER.toMealResponse(meal);
    }

    @Override
    public void delete(Long id) {
        if (mealRepository.existsById(id)) {
            mealRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Unable to find meal with id " + id + "!");
        }
    }
}
