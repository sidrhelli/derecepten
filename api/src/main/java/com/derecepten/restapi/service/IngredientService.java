package com.derecepten.restapi.service;


import com.derecepten.restapi.exception.IngredientInvalidException;
import com.derecepten.restapi.model.recipe.Ingredient;

import java.util.Optional;

/**
 * Created by sergioh on 03/21/2021
 **/
public interface IngredientService {
    Ingredient save(Ingredient ingredient) throws IngredientInvalidException;

    Optional<Ingredient> findById(Long aLong);

    Iterable<Ingredient> findAll();

    long count();

    void deleteById(Long aLong) throws IngredientInvalidException;
}
