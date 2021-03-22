package com.derecepten.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class RecipeNotFoundException extends RuntimeException {

    public RecipeNotFoundException(Long id) {
        super("Resource not found : " + id);
    }

}
