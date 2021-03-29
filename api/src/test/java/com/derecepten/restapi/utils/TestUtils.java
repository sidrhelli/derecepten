package com.derecepten.restapi.utils;

import com.derecepten.restapi.model.recipe.Recipe;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Created by sergioh on 03/23/2021
 **/
public class TestUtils {
    private TestUtils() {
    }

     public static Recipe readJsonFromFile(String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filename), Recipe.class);
    }
}
