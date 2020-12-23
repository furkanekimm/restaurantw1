package com.example.restaurantapii.repository;

import com.example.restaurantapii.entity.Category;
import com.example.restaurantapii.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

   Slice<Product> findProductsByCategories(Category category,Pageable pageable);


}
