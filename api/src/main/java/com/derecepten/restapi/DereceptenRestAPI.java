package com.derecepten.restapi;

import com.derecepten.restapi.config.AppProperties;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableEncryptableProperties
@EnableConfigurationProperties(AppProperties.class)
public class DereceptenRestAPI {


    public static void main(String[] args) {
        SpringApplication.run(DereceptenRestAPI.class, args);
    }


}
