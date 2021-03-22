package com.derecepten.restapi.service;

import com.derecepten.restapi.exception.RecipeNotFoundException;
import com.derecepten.restapi.exception.RecipeInvalidException;
import com.derecepten.restapi.model.recipe.Recipe;
import com.derecepten.restapi.repository.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by sergioh on 03/19/2021
 **/
@Service
public class RecipeServiceImpl implements RecipeService {

    private final static Logger LOG = LoggerFactory.getLogger(RecipeServiceImpl.class);
    private final static String RECIPE_NOT_NULL_ERR_MSG = "Recipe can not be null";
    private final static String RECIPE_ID_NOT_NULL_ERR_MSG = "Recipe can not be null";
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Recipe save(Recipe recipe) throws RecipeInvalidException {
        if (recipe == null) {
            LOG.error(RECIPE_NOT_NULL_ERR_MSG);
            throw new RecipeInvalidException();
        }
        return recipeRepository.save(recipe);
    }

    public Optional<Recipe> findById(Long id) {
        return recipeRepository.findById(id);
    }

    @Override
    public Iterable<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    @Override
    public long count() {
        return recipeRepository.count();
    }

    @Override
    public void deleteById(Long id) throws RecipeNotFoundException {
        if (id == null) {
            throw new RecipeNotFoundException(id);
        }
        recipeRepository.deleteById(id);
    }
}
