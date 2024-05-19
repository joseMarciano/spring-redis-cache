package com.spring.cache.example.marcianodev.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Random;

@RestController
public class CacheTestingController {

    @GetMapping("/product/cacheable/{id}")
    @Cacheable(value = "product")
    public ProductExampleOutput productById() throws InterruptedException {
        System.out.println("Finding product in database");
        Thread.sleep(1_000L);
        return new ProductExampleOutput("123", "productExample", 10.5, LocalDateTime.now());
    }

    @GetMapping("/customer/cacheable/{id}")
    @Cacheable(value = "customer")
    public CustumerExampleOutput customerById() throws InterruptedException {
        System.out.println("Finding customer in database");
        Thread.sleep(500L);
        return new CustumerExampleOutput("John", new Random().nextInt(), LocalDateTime.now());
    }

    @PostMapping("/customer/evict/{id}")
    @CacheEvict(value = "customer", allEntries = true)
    public CustumerExampleOutput customerCreate() throws InterruptedException {
        System.out.println("Finding customer in database");
        Thread.sleep(1_000L);
        return new CustumerExampleOutput("John", 45, LocalDateTime.now());
    }

    @GetMapping("/car/put/{id}")
    @CachePut(value = "car")
    public CarExampleOutput carById() throws InterruptedException {
        System.out.println("Finding car in database");
        Thread.sleep(3_500L);
        return new CarExampleOutput("Hi lux", "978", LocalDateTime.now());
    }


    public record ProductExampleOutput(String id, String name, double price,
                                       LocalDateTime dateTime) implements Serializable {
    }

    public record CustumerExampleOutput(String name, int age, LocalDateTime dateTime) implements Serializable {
    }

    public record CarExampleOutput(String name, String model, LocalDateTime dateTime) implements Serializable {
    }
}
