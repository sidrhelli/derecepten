package com.derecepten.restapi.repository;

import com.derecepten.restapi.model.recipe.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by sergioh on 03/18/2021
 **/
@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    @Override
    Recipe save(Recipe recipe);

    @Override
    Optional<Recipe> findById(Long aLong);

    @Override
    Iterable<Recipe> findAll();

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

}
