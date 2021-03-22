package com.derecepten.restapi.service;

import com.derecepten.restapi.exception.RecipeNotFoundException;
import com.derecepten.restapi.exception.RecipeInvalidException;
import com.derecepten.restapi.model.recipe.Recipe;

import java.util.Optional;

/**
 * Created by sergioh on 03/19/2021
 **/
public interface RecipeService {
    Recipe save(Recipe recipe) throws RecipeInvalidException;

    Optional<Recipe> findById(Long id);

    Iterable<Recipe> findAll();

    long count();

    void deleteById(Long aLong) throws RecipeNotFoundException;
}
