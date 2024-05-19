# 🗃️ Spring Redis Cache Example

Welcome to the Spring Redis Cache Example repository! This repository provides examples of using Redis cache to enhance the performance of a Spring Boot application. By leveraging Redis, we can significantly reduce the load on our database, improve response times, and enhance the overall scalability of the application.

## 🌟 Introduction

Caching is a crucial technique for improving the performance of applications by temporarily storing frequently accessed data in memory. This repository demonstrates how to integrate Redis, an in-memory data structure store, with a Spring Boot application to implement caching. The application includes examples of caching database query results for products and customers.

## ✨ Features

- Basic setup and configuration of Redis in a Spring Boot application.
- Examples of caching service responses to improve performance.
- Demonstrates usage of Spring's `@Cacheable`, `@CacheEvict`, `@CachePut` annotation.
- Provides a simple REST API with endpoints for products and customers.
- Docker Compose setup for running Redis and Redis Insight.

## ⚙️ Installation
To run this app you will need docker and docker-compose.
- To up the infra: `docker-compose up -d` to up redis and redis-insight
- To up the app: `./mvnw spring-boot:run`

## Examples 

### GET request for product
`curl -X GET "http://localhost:8080/product/cacheable/123" -H "Accept: application/json"`

### GET request for car
`curl -X GET "http://localhost:8080/car/put/123" -H "Accept: application/json"`

### GET request for customer
`curl -X GET "http://localhost:8080/customer/cacheable/123" -H "Accept: application/json"`

### POST create product (evict cache for customer)
`curl -X POST "http://localhost:8080/customer/evict/123" -H "Accept: application/json"`