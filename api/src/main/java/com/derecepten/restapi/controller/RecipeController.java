package com.derecepten.restapi.controller;

import com.derecepten.restapi.exception.BadResourceException;
import com.derecepten.restapi.exception.RecipeNotFoundException;
import com.derecepten.restapi.exception.ResourceAlreadyExistsException;
import com.derecepten.restapi.model.recipe.Recipe;
import com.derecepten.restapi.repository.UserRepository;
import com.derecepten.restapi.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

/**
 * Created by sergioh on 03/18/2021
 **/
@RestController
@RequestMapping(value = "/api/v1/recipes")
public class RecipeController extends BasePage {


    public RecipeController(RecipeService recipeService, UserRepository userRepository) {
        super(recipeService, userRepository);
    }

    @GetMapping(value = "/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Iterable> findAllRecipes() {
        Iterable recipes = recipeService.findAll();
        return ResponseEntity.ok(recipes);
    }

    @GetMapping(value = "/myrecipes")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Iterable> findAllRecipesByRandomId() {
        String randomId = getRandomIdFromSecurityContext();
        Iterable<Recipe> recipes = recipeService.findByUserRandomId(randomId);
        return ResponseEntity.ok(recipes);
    }


    @GetMapping(value = "/search/{searchString}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Iterable> searchRecipesByTitle(@PathVariable("searchString") String searchString) {
        Iterable<Recipe> recipes = recipeService.searchRecipe(searchString);
        return ResponseEntity.ok(recipes);
    }

    @GetMapping(value = "/{recipeId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Recipe> findRecipe(@PathVariable("recipeId") Long recipeId) {
        Recipe recipe = recipeService.findById(recipeId);
        // TODO: add random id to query

        return ResponseEntity.ok(recipe);
    }

    @PutMapping("/{recipeId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Recipe> updateRecipe(@Valid @RequestBody Recipe recipe, @PathVariable Long recipeId)
            throws BadResourceException {
        recipe.setId(recipeId);
        recipeService.update(recipe);
        // TODO: add random id to query

        return ResponseEntity.ok(recipe);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Recipe> createRecipe(@Valid @RequestBody Recipe recipe) throws BadResourceException, ResourceAlreadyExistsException {
        Recipe createdRecipe = recipeService.save(recipe);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdRecipe.getId()).toUri();

        return ResponseEntity.created(location).body(createdRecipe);
    }

    @DeleteMapping("/{recipeId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> deleteRecipe(@PathVariable("recipeId") Long recipeId) throws RecipeNotFoundException {
        recipeService.deleteById(recipeId);
        // TODO: add random id to query
        return ResponseEntity.ok().build();
    }


}
