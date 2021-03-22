package com.derecepten.restapi.repository;

import com.derecepten.restapi.exception.IngredientInvalidException;
import com.derecepten.restapi.model.recipe.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by sergioh on 03/21/2021
 **/
@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {

    @Override
    Ingredient save(Ingredient ingredient);

    @Override
    Optional<Ingredient> findById(Long aLong);

    @Override
    Iterable<Ingredient> findAll();

    @Override
    long count();

    @Override
    void deleteById(Long aLong);
}
