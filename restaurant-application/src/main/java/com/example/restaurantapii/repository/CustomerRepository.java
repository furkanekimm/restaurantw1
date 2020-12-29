package com.example.restaurantapii.repository;

import com.example.restaurantapii.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    Page<Customer> findCustomerByNameContains(String name, Pageable pageable);
}
