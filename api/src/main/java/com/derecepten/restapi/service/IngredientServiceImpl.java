package com.derecepten.restapi.service;


import com.derecepten.restapi.exception.IngredientInvalidException;
import com.derecepten.restapi.model.recipe.Ingredient;
import com.derecepten.restapi.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by sergioh on 03/21/2021
 **/

@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        if (ingredient == null) {
            throw new IngredientInvalidException();
        }
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Optional<Ingredient> findById(Long id) {
        return ingredientRepository.findById(id);
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public long count() {
        return ingredientRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new IngredientInvalidException(id);
        }
        ingredientRepository.deleteById(id);
    }
}
