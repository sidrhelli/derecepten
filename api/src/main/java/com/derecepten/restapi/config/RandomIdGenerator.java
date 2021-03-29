package com.derecepten.restapi.config;

import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by sergioh on 03/27/2021
 **/
@Component
public class RandomIdGenerator extends SequenceGenerator {


    public String generateRandomId() {
        return String.valueOf(createRandomLong()).concat(createRandomString());
    }

    private String createRandomString() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
//
//    private String createString() {
//        int leftLimit = 48; // numeral '0'
//        int rightLimit = 122; // letter 'z'
//        int targetStringLength = 10;
//        Random random = new Random();
//
//        Long generatedString = random.ints(leftLimit, rightLimit + 1)
//                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
//                .limit(targetStringLength)
//                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//                .toString();
//
//        return generatedString;
//
//    }

    private Long createRandomLong() {
        return nextId();
    }


}
