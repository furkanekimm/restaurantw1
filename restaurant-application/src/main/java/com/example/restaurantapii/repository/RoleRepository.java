package com.example.restaurantapii.repository;

import com.example.restaurantapii.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

}
