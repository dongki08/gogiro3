package com.green.gogiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class GogiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(GogiroApplication.class, args);
    }

}
