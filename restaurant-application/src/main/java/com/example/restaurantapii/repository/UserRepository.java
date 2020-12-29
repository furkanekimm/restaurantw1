package com.example.restaurantapii.repository;

import com.example.restaurantapii.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("Select u from USERS u WHERE u.username = :username")
    User getUserByUsername(@Param("username") String username);

}
