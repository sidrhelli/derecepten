package com.derecepten.restapi.model.recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergioh on 03/19/2021
 **/
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private double quantity;

    @Column
    @NotNull
    private MeasurementUnitType measurementUnitType;


    @ManyToMany(mappedBy="ingredients")
    @JsonIgnore
    private List<Recipe> recipes = new ArrayList<>();

    @Column
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public MeasurementUnitType getMeasurementUnitType() {
        return measurementUnitType;
    }

    public void setMeasurementUnitType(MeasurementUnitType measurementUnitType) {
        this.measurementUnitType = measurementUnitType;
    }
    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
        recipe.getIngredients().add(this);
    }

    public void removeRecipe(Recipe recipe) {
        this.recipes.remove(recipe);
        recipe.getIngredients().remove(this);
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String

    toString() {
        return "Ingredient{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", measurementUnitType=" + measurementUnitType +
                ", recipes=" + recipes +
                ", title='" + title + '\'' +
                '}';
    }
}