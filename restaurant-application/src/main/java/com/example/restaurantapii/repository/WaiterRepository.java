package com.example.restaurantapii.repository;

import com.example.restaurantapii.entity.Waiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaiterRepository extends JpaRepository<Waiter,Long> {
}
