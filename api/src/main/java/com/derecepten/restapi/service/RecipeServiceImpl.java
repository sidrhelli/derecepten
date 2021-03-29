package com.derecepten.restapi.service;

import com.derecepten.restapi.exception.BadResourceException;
import com.derecepten.restapi.exception.ResourceAlreadyExistsException;
import com.derecepten.restapi.exception.ResourceNotFoundException;
import com.derecepten.restapi.model.recipe.Recipe;
import com.derecepten.restapi.repository.IngredientRepository;
import com.derecepten.restapi.repository.RecipeRepository;
import com.derecepten.restapi.util.DatabaseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * Created by sergioh on 03/19/2021
 **/
@Service
public class RecipeServiceImpl implements RecipeService {

    private final static Logger LOG = LoggerFactory.getLogger(RecipeServiceImpl.class);
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;


    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    private boolean existsById(Long id) {
        return recipeRepository.existsById(id);
    }

    @Override
    public Recipe findById(Long id) throws ResourceNotFoundException {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        if (recipe == null) {
            String errMsg = "Recipe not found";
            LOG.error(errMsg);
            throw new ResourceNotFoundException(errMsg);
        } else return recipe;
    }

    public Iterable<Recipe> findAll() {
        return recipeRepository.findAll();
    }




    @Override
    public Recipe save(Recipe recipe) throws BadResourceException, ResourceAlreadyExistsException {
        if (!recipe.getTitle().isEmpty()) {
            if (recipe.getId() != null && existsById(recipe.getId())) {
                throw new ResourceAlreadyExistsException("Recipe with id: " + recipe.getId() +
                        " already exists");
            }

            // Set created timestamp form recipe
            recipe.setCreatedTimestamp(DatabaseUtils.parseTimestamp(Timestamp.from(Instant.now()).toString()));
            return recipeRepository.save(recipe);

        } else {
            BadResourceException exc = new BadResourceException("Failed to save recipe");
            exc.addErrorMessage("Recipe is null or empty");
            throw exc;
        }
    }

    @Override
    public void update(Recipe recipe)
            throws BadResourceException, ResourceNotFoundException {
        if (!recipe.getTitle().isEmpty()) {
            if (!existsById(recipe.getId())) {
                throw new ResourceNotFoundException("Can't find Recipe with id: " + recipe.getId());
            }

            // Set modified timestamp form recipe
            recipe.setCreatedTimestamp(DatabaseUtils.parseTimestamp(Timestamp.from(Instant.now()).toString()));
            recipeRepository.save(recipe);
        } else {
            BadResourceException exc = new BadResourceException("Failed to save recipe");
            exc.addErrorMessage("Recipe is null or empty");
            throw exc;
        }
    }



    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!existsById(id)) {
            throw new ResourceNotFoundException("Cannot find recipe with id: " + id);
        } else {
//           ingredientRepository.deleteById();
            recipeRepository.deleteById(id);
        }
    }

    @Override
    public Long count() {
        return recipeRepository.count();
    }

    @Override
    public Iterable<Recipe> findByUserRandomId(String randomId) {
        return recipeRepository.findByUserRandomId(randomId);
    }

    @Override
    public Iterable<Recipe> searchRecipe(String searchString) {
        return recipeRepository.findByTitleContainingIgnoreCase(searchString);
    }

}
