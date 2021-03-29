package com.derecepten.restapi.controller;

import com.derecepten.restapi.config.RandomIdGenerator;
import com.derecepten.restapi.exception.BadRequestException;
import com.derecepten.restapi.model.AuthProvider;
import com.derecepten.restapi.model.User;
import com.derecepten.restapi.payload.ApiResponse;
import com.derecepten.restapi.payload.AuthResponse;
import com.derecepten.restapi.payload.LoginRequest;
import com.derecepten.restapi.payload.SignUpRequest;
import com.derecepten.restapi.repository.UserRepository;
import com.derecepten.restapi.security.TokenProvider;
import com.derecepten.restapi.util.DatabaseUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.sql.Timestamp;
import java.time.Instant;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final TokenProvider tokenProvider;

    private final RandomIdGenerator randomIdGenerator;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          PasswordEncoder passwordEncoder, TokenProvider tokenProvider, RandomIdGenerator randomIdGenerator, RandomIdGenerator randomIdGenerator1) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.randomIdGenerator = randomIdGenerator1;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.local);
        user.setRandomId(randomIdGenerator.generateRandomId());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Set created timestamp form recipe
        user.setCreatedTimestamp(DatabaseUtils.parseTimestamp(Timestamp.from(Instant.now()).toString()));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/user/me")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User registered successfully@"));
    }

}
