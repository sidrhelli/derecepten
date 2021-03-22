package com.derecepten.restapi.security;

import com.derecepten.restapi.config.AppProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by sergioh on 03/19/2021
 **/
class TokenProviderTest {

    TokenProvider tokenProvider;
    AppProperties appProperties;

    static long TOKEN_EXPIRATION_TIME;
    SecretKey key;
    String token;

    @BeforeEach
    void setUp() {
        appProperties = new AppProperties();
        tokenProvider = new TokenProvider(appProperties);
        key = Keys.hmacShaKeyFor("926D96C90030DD58429D2751AC1BDBBC".getBytes());
        TOKEN_EXPIRATION_TIME = 864000000L; // 15 min
    }

    String generateToken() {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + TOKEN_EXPIRATION_TIME);
        SecretKey key = Keys.hmacShaKeyFor("926D96C90030DD58429D2751AC1BDBBC".getBytes());


        return Jwts.builder()
                .setSubject(Long.toString(3L))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();

    }
    @Test
    void createToken() {
        String token = generateToken();
        System.out.println("JWS generated: "
                + token);
    }
}