package com.derecepten.restapi.controller;

import com.derecepten.restapi.model.User;
import com.derecepten.restapi.repository.UserRepository;
import com.derecepten.restapi.service.RecipeService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by sergioh on 03/29/2021
 **/
@Component
public abstract class BasePage {

     final RecipeService recipeService;
     final UserRepository userRepository;

     BasePage(RecipeService recipeService, UserRepository userRepository) {
        this.recipeService = recipeService;
        this.userRepository = userRepository;
    }

     String getRandomIdFromSecurityContext() {
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        User user = userOptional.orElse(null);
        String randomId = user.getRandomId();
        return randomId;
    }
}
