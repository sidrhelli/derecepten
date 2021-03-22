package com.derecepten.restapi.error;

import com.derecepten.restapi.exception.RecipeInvalidException;
import com.derecepten.restapi.exception.RecipeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * Created by sergioh on 03/22/2021
 **/
@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> recipeNotFound(Exception ex) {
        ApiErrorResponse errors = new ApiErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errors, HttpStatus.OK);
    }


    @ExceptionHandler(RecipeInvalidException.class)
    public ResponseEntity<ApiErrorResponse> recipeInvalidFound(Exception ex) {
        ApiErrorResponse errors = new ApiErrorResponse();
        errors.setTimestamp(LocalDateTime.now());
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errors, HttpStatus.OK);
    }

}
