package com.derecepten.restapi.controller;

import com.derecepten.restapi.exception.RecipeInvalidException;
import com.derecepten.restapi.exception.RecipeNotFoundException;
import com.derecepten.restapi.model.recipe.Ingredient;
import com.derecepten.restapi.model.recipe.Recipe;
import com.derecepten.restapi.service.IngredientService;
import com.derecepten.restapi.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by sergioh on 03/18/2021
 **/
@RestController
@RequestMapping(value = "/recipe")
public class RecipeController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public RecipeController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping(value = "/find/{recipeId}")
    @PreAuthorize("hasRole('USER')")
    Recipe findRecipe(@PathVariable Long recipeId) {
        return recipeService.findById(recipeId)
                .orElseThrow(() -> new RecipeNotFoundException(recipeId));
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<?> createRecipe(@Valid @RequestBody Recipe recipe) throws RecipeInvalidException {
        List<Ingredient> ingredients = recipe.getIngredients();
        ingredients.forEach(ingredient -> ingredient.getRecipes().add(recipe));
        ingredients.forEach(r -> r.getRecipes().add(recipe));

        for (Ingredient ingredient : ingredients) {
            ingredientService.save(ingredient);
        }

        Recipe savedRecipe = recipeService.save(recipe);
        return ResponseEntity.ok(savedRecipe);
    }


}
