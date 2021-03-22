package com.derecepten.restapi.model.recipe;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sergioh on 03/18/2021
 **/
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private double kcal;

    @Column
    private int prepTime;

    @Column(nullable = false)
    private int persons;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String prepInstructions;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodThemeType foodThemeType;

    @ManyToMany
    @JoinTable(name = "recipe_ingredient",
            joinColumns = {@JoinColumn(name = "fk_recipe")},
            inverseJoinColumns = {@JoinColumn(name = "fk_ingredient")})
    private List<Ingredient> ingredients = new ArrayList<>(0);

    public void addRecipe(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        ingredient.getRecipes().add(this);
    }

    public void removeRecipe(Ingredient ingredient) {
        this.ingredients.remove(ingredient);
        ingredient.getRecipes().remove(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getKcal() {
        return kcal;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public String getPrepInstructions() {
        return prepInstructions;
    }

    public void setPrepInstructions(String prepInstructions) {
        this.prepInstructions = prepInstructions;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public FoodThemeType getFoodThemeType() {
        return foodThemeType;
    }

    public void setFoodThemeType(FoodThemeType foodThemeType) {
        this.foodThemeType = foodThemeType;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", kcal=" + kcal +
                ", prepTime=" + prepTime +
                ", persons=" + persons +
                ", prepInstructions='" + prepInstructions + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }
}
