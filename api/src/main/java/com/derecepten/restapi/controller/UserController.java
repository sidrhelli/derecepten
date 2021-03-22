package com.derecepten.restapi.controller;

import com.derecepten.restapi.exception.ResourceNotFoundException;
import com.derecepten.restapi.model.User;
import com.derecepten.restapi.repository.UserRepository;
import com.derecepten.restapi.security.CurrentUser;
import com.derecepten.restapi.security.UserPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
