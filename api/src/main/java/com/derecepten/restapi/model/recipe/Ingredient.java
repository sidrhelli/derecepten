package com.derecepten.restapi.model.recipe;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergioh on 03/19/2021
 **/
@Entity
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double quantity;

    @Column
    @NotNull
    private MeasurementUnitType measurementUnitType;

    @Column
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_recipe_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Recipe recipe;

    public Ingredient() {

    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient )) return false;
        return id != null && id.equals(((Ingredient) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", measurementUnitType=" + measurementUnitType +
                ", title='" + title + '\'' +
                '}';
    }




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

    public MeasurementUnitType getMeasurementUnitType() {
        return measurementUnitType;
    }

    public void setMeasurementUnitType(MeasurementUnitType measurementUnitType) {
        this.measurementUnitType = measurementUnitType;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static final class IngredientBuilder {
        private Long id;
        private double quantity;
        private @NotNull MeasurementUnitType measurementUnitType;
        private List<Recipe> recipes;
        private String title;

        private IngredientBuilder() {
        }

        public static IngredientBuilder createIngredient() {
            return new IngredientBuilder();
        }

        public IngredientBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public IngredientBuilder withQuantity(double quantity) {
            this.quantity = quantity;
            return this;
        }

        public IngredientBuilder withMeasurementUnitType(MeasurementUnitType measurementUnitType) {
            this.measurementUnitType = measurementUnitType;
            return this;
        }


        public IngredientBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Ingredient build() {
            Ingredient ingredient = new Ingredient();
            ingredient.setId(id);
            ingredient.setQuantity(quantity);
            ingredient.setMeasurementUnitType(measurementUnitType);
//            ingredient.setRecipes(recipes);
            ingredient.setTitle(title);
            return ingredient;
        }
    }
}