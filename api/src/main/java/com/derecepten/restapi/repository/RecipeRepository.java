package com.derecepten.restapi.repository;

import com.derecepten.restapi.model.recipe.Recipe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by sergioh on 03/18/2021
 **/
@Repository
public interface RecipeRepository extends PagingAndSortingRepository<Recipe, Long> {
    Iterable<Recipe> findByTitleContainingIgnoreCase(@Param("title") String title);

    @Query("select r from Recipe r, User u where u.id = r.user.id and u.randomId = :randomId")
    Iterable<Recipe> findByUserRandomId(@Param("randomId") String randomId);
}
