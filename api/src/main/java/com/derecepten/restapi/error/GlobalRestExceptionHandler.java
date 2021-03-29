package com.derecepten.restapi.error;

import com.derecepten.restapi.exception.RecipeInvalidException;
import com.derecepten.restapi.exception.RecipeNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * Created by sergioh on 03/22/2021
 **/
@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalRestExceptionHandler.class);

    @ExceptionHandler(RecipeNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> recipeNotFound(Exception ex) {
        ApiErrorResponse errors = new ApiErrorResponse();
        errors.setTimestamp(LocalDateTime.now(ZoneId.from(ZoneOffset.UTC)));
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.NOT_FOUND.value());
        LOG.error(ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.OK);
    }


    @ExceptionHandler(RecipeInvalidException.class)
    public ResponseEntity<ApiErrorResponse> recipeInvalidFound(Exception ex) {
        ApiErrorResponse errors = new ApiErrorResponse();
        errors.setTimestamp(LocalDateTime.now(ZoneId.from(ZoneOffset.UTC)));
        errors.setError(ex.getMessage());
        errors.setStatus(HttpStatus.BAD_REQUEST.value());
        LOG.error(ex.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity <Object> handleCustomAPIException(Exception ex) {

        ApiErrorResponse response = new ApiErrorResponse.ApiErrorResponseBuilder()
                .withStatus(HttpStatus.BAD_GATEWAY.value())
                .withError("Something went wrong.")
                .withTimestamp(LocalDateTime.now(ZoneId.from(ZoneOffset.UTC)))
                .build();

        LOG.error(ex.getLocalizedMessage());
        return new ResponseEntity <> (response, HttpStatus.valueOf(response.getStatus()));
    }

}
