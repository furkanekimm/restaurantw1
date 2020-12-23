package com.example.restaurantapii.repository;

import com.example.restaurantapii.entity.Category;
import com.example.restaurantapii.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long>{


}
