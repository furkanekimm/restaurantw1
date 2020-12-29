package com.example.restaurantapii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class RestaurantApiiApplication {

    public static void main(String[] args) {

        SpringApplication.run(RestaurantApiiApplication.class, args);
    }

}
