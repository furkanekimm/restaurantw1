package com.example.restaurantapii.repository;

import com.example.restaurantapii.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("Select u from USERS u where u.username=:username and u.password=:pass")
    User login(String username,String pass);

    @Query("Select u from USERS u WHERE u.username = :username")
    User getUserByUsername(@Param("username") String username);

/*    @Query("DELETE FROM USER_ROLES WHERE USER_ID=:id")
    Boolean deleteUserRole(Long id);*/
}
