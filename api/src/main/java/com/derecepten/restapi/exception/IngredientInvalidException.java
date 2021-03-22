package com.derecepten.restapi.exception;

public class IngredientInvalidException extends RuntimeException {


    public IngredientInvalidException() {
        super("Ingredient invalid");
    }

    public IngredientInvalidException(Object invalidObject) {
        super("Ingredient invalid : " + invalidObject);
    }

}
