package com.derecepten.restapi.service;

import com.derecepten.restapi.exception.*;
import com.derecepten.restapi.model.recipe.Recipe;
import org.springframework.data.repository.query.Param;

/**
 * Created by sergioh on 03/19/2021
 **/
public interface RecipeService {
    Recipe findById(Long id) throws ResourceNotFoundException;

    Iterable<Recipe> findAll();

    Recipe save(Recipe recipe) throws BadResourceException, ResourceAlreadyExistsException;

    void update(Recipe recipe)
            throws BadResourceException, ResourceNotFoundException;

    void deleteById(Long id) throws ResourceNotFoundException;

    Long count();

    Iterable<Recipe> findByUserRandomId(@Param("randomId") String randomId);

    Iterable<Recipe> searchRecipe(@Param("title") String title);



}
