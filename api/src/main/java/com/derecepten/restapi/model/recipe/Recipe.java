package com.derecepten.restapi.model.recipe;

import com.derecepten.restapi.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by sergioh on 03/18/2021
 **/
@Entity
public class Recipe implements Serializable {


    @Id
    @GeneratedValue(generator = "recipe_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "recipe_id_seq", sequenceName = "recipe_id_seq", initialValue = 2559)
    @Column(name = "recipe_id")
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

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Ingredient> ingredients = new HashSet<>(0);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    @Column(name = "created_timestamp")
    private Timestamp createdTimestamp;

    @Column(name = "modified_timestamp")
    private Timestamp modifiedTimestamp;

    public Recipe() {

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

    public FoodThemeType getFoodThemeType() {
        return foodThemeType;
    }

    public void setFoodThemeType(FoodThemeType foodThemeType) {
        this.foodThemeType = foodThemeType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;

        for (Ingredient ingredient : ingredients) {
            ingredient.setRecipe(this);
        }
    }

    public Timestamp getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Timestamp createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public Timestamp getModifiedTimestamp() {
        return modifiedTimestamp;
    }

    public void setModifiedTimestamp(Timestamp modifiedTimestamp) {
        this.modifiedTimestamp = modifiedTimestamp;
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
                ", foodThemeType=" + foodThemeType +
                ", ingredients=" + ingredients +
                '}';
    }


    // Builder class
    public static final class RecipeBuilder {
        private Long id;
        private String title;
        private double kcal;
        private int prepTime;
        private int persons;
        private String prepInstructions;
        private @NotNull FoodThemeType foodThemeType;
        private Set<Ingredient> ingredients;

        private RecipeBuilder() {
        }

        public static RecipeBuilder aRecipe() {
            return new RecipeBuilder();
        }

        public RecipeBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public RecipeBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public RecipeBuilder withKcal(double kcal) {
            this.kcal = kcal;
            return this;
        }

        public RecipeBuilder withPrepTime(int prepTime) {
            this.prepTime = prepTime;
            return this;
        }

        public RecipeBuilder withPersons(int persons) {
            this.persons = persons;
            return this;
        }

        public RecipeBuilder withPrepInstructions(String prepInstructions) {
            this.prepInstructions = prepInstructions;
            return this;
        }

        public RecipeBuilder withFoodThemeType(FoodThemeType foodThemeType) {
            this.foodThemeType = foodThemeType;
            return this;
        }

        public RecipeBuilder withIngredients(Set<Ingredient> ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public Recipe build() {
            Recipe recipe = new Recipe();
            recipe.setId(id);
            recipe.setTitle(title);
            recipe.setKcal(kcal);
            recipe.setPrepTime(prepTime);
            recipe.setPersons(persons);
            recipe.setPrepInstructions(prepInstructions);
            recipe.setFoodThemeType(foodThemeType);
            recipe.setIngredients(ingredients);
            return recipe;
        }
    }
}
