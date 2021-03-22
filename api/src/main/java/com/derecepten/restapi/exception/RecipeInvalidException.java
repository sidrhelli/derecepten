package com.derecepten.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class RecipeInvalidException extends RuntimeException {


    public RecipeInvalidException() {
        super("Resource invalid");
    }

    public RecipeInvalidException(Object invalidObject) {
        super("Resource invalid : " + invalidObject);
    }

}
