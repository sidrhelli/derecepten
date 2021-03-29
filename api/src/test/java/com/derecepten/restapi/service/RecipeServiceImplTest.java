package com.derecepten.restapi.service;

import com.derecepten.restapi.exception.BadResourceException;
import com.derecepten.restapi.exception.ResourceAlreadyExistsException;
import com.derecepten.restapi.exception.ResourceNotFoundException;
import com.derecepten.restapi.model.recipe.FoodThemeType;
import com.derecepten.restapi.model.recipe.Ingredient;
import com.derecepten.restapi.model.recipe.MeasurementUnitType;
import com.derecepten.restapi.model.recipe.Recipe;
import com.derecepten.restapi.repository.IngredientRepository;
import com.derecepten.restapi.repository.RecipeRepository;
import com.derecepten.restapi.util.DatabaseUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Created by sergioh on 03/23/2021
 **/
class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;
    Recipe recipeRetrieve;
    Set<Ingredient> ingredients;
    Ingredient pasta;

    @Mock
    RecipeRepository recipeRepository;
    @Mock
    IngredientRepository ingredientRepository;


    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, ingredientRepository);
        ingredients = new HashSet<>();

        pasta = Ingredient.IngredientBuilder.createIngredient()
                .withId(16L)
                .withMeasurementUnitType(MeasurementUnitType.GRAM)
                .withQuantity(30)
                .withTitle("pasta")
                .build();

        ingredients.add(pasta);

        recipeRetrieve = Recipe.RecipeBuilder.aRecipe()
                .withId(134L)
                .withFoodThemeType(FoodThemeType.MEAL)
                .withKcal(300)
                .withPersons(7)
                .withTitle("italiaanse roti")
                .withIngredients(ingredients)
                .build();
    }

    @Test
    void findUpdateDelete() throws BadResourceException, ResourceAlreadyExistsException {
        when(recipeRepository.save(recipeRetrieve)).thenReturn(recipeRetrieve);
        when(recipeRepository.findById(recipeRetrieve.getId())).thenReturn(Optional.ofNullable(recipeRetrieve));

        Recipe savedRecipe = recipeService.save(recipeRetrieve);
        assertNotNull(savedRecipe.getId());
        assertEquals("pasta", savedRecipe.getIngredients().stream().findFirst().get().getTitle());
        verify(recipeRepository, times(1)).save(recipeRetrieve);

        // Find by id
        Recipe recipeById = recipeService.findById(recipeRetrieve.getId());
        assertEquals("italiaanse roti", recipeById.getTitle());
        assertEquals(FoodThemeType.MEAL, recipeById.getFoodThemeType());
        verify(recipeRepository, times(1)).findById(recipeRetrieve.getId());

        // Update
        Ingredient extraIngredient = Ingredient.IngredientBuilder.createIngredient()
                .withTitle("Bakabana")
                .withQuantity(33)
                .build();
        // update record
        ingredients.add(extraIngredient);
        recipeRetrieve.setIngredients(ingredients);
        when(recipeRepository.existsById(recipeRetrieve.getId())).thenReturn(true);

        recipeService.update(recipeRetrieve);

        Recipe findUpdatedRecipe = recipeService.findById(recipeRetrieve.getId());
        assertEquals(2, findUpdatedRecipe.getIngredients().size());
        assertEquals("Bakabana", findUpdatedRecipe.getIngredients().stream().findFirst().get().getTitle());

        // Delete
        recipeService.deleteById(recipeRetrieve.getId());
        assertThrows(ResourceNotFoundException.class, () -> recipeService.findById(165L));
    }

    @Test
    void findAll() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipeRetrieve);
        recipes.add(recipeRetrieve);
        recipes.add(recipeRetrieve);
        recipes.add(recipeRetrieve);

        when(recipeRepository.findAll()).thenReturn(recipes);
        Iterable<Recipe> allRecipes = recipeService.findAll();
        assertNotNull(allRecipes.iterator().next().getId());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void count() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipeRetrieve);
        recipes.add(recipeRetrieve);
        recipes.add(recipeRetrieve);
        recipes.add(recipeRetrieve);
        recipes.add(recipeRetrieve);
        when(recipeRepository.count()).thenReturn(4L);
        long recordCount = recipeService.count();
        assertEquals(4L, recordCount);
        verify(recipeRepository, times(1)).count();
    }

    @Test void printDate(){
        Timestamp timestamp = Timestamp.from(Instant.now());
        System.out.println(timestamp);
        System.out.println(DatabaseUtils.parseTimestamp(timestamp.toString()));
    }


}