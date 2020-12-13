package com.example.restaurantapii;

import com.example.restaurantapii.entity.Category;
import com.example.restaurantapii.entity.Product;
import com.example.restaurantapii.repository.CategoryRepository;
import com.example.restaurantapii.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class RestaurantApiiApplication {

    public static void main(String[] args) {

        SpringApplication.run(RestaurantApiiApplication.class, args);
    }

}
