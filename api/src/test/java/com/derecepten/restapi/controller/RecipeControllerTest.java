package com.derecepten.restapi.controller;

import com.derecepten.restapi.model.recipe.Recipe;
import com.derecepten.restapi.service.RecipeService;
import com.derecepten.restapi.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by sergioh on 03/24/2021
 **/
@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    final static String FILENAME = "src/main/resources/json/recipe.json";
    @Mock
    RecipeService recipeService;

    @InjectMocks
    RecipeController controller;

    MockMvc mockMvc;

    Recipe recipe;
    List<Recipe> recipes = new ArrayList<>();

    @BeforeEach
    void setUp() throws IOException {
        recipe = TestUtils.readJsonFromFile(FILENAME);
        recipes.add(recipe);
        recipes.add(recipe);
        recipes.add(recipe);
        recipes.add(recipe);

        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void findRecipe() throws Exception {
    }

    @Test
    void updateRecipe() {
    }

    @Test
    void saveRecipe() {
    }

    @Test
    void deleteRecipe() {
    }
}