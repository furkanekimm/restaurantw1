package com.example.restaurantapii.controller;

import com.example.restaurantapii.dto.RoleDTO;
import com.example.restaurantapii.dto.UserDTO;
import com.example.restaurantapii.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/person")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/control/add")
    public UserDTO addUser(@RequestBody UserDTO userDTO){
        return userService.addUser(userDTO);
    }

    @PutMapping("/control/update")
    public UserDTO updateUser(@RequestBody UserDTO userDTO){
        return userService.updateUser(userDTO);
    }

    @GetMapping("/control/all")
    public List<UserDTO> getAllUsers(){
        return userService.allUsers();
    }

    @DeleteMapping("/control/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

    @GetMapping("/control/{id}")
    public UserDTO getUserByID(@PathVariable Long id){
        return userService.getUserByID(id);
    }

    @GetMapping("/login")
    public String login(){
        return "Basic authentication is okey.";
    }


}
