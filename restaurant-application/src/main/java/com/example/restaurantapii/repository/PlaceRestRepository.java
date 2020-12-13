package com.example.restaurantapii.repository;

import com.example.restaurantapii.entity.PlaceRest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRestRepository extends JpaRepository<PlaceRest,Long> {
}
