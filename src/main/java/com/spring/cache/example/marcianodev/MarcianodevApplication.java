package com.spring.cache.example.marcianodev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MarcianodevApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarcianodevApplication.class, args);
    }
}
