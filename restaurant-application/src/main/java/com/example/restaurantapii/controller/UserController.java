package com.example.restaurantapii.controller;

import com.example.restaurantapii.dto.RoleDTO;
import com.example.restaurantapii.dto.UserDTO;
import com.example.restaurantapii.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/person")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/control/add")
    public UserDTO addUser(@Valid @RequestBody UserDTO userDTO){
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
    public String deleteUser(@NotNull(message = "{ID_NULL}") @Min(value = 0,message = "{ID_NOT_BE_LITTLE_ZERO}") @PathVariable Long id){
        return userService.deleteUser(id);
    }

    @GetMapping("/control/{id}")
    public UserDTO getUserByID(@NotNull(message = "{ID_NULL}") @Min(value = 0,message = "{ID_NOT_BE_LITTLE_ZERO}") @PathVariable Long id){
        return userService.getUserByID(id);
    }

    @GetMapping("/login")
    public String login(){
        return "Basic authentication is okey.";
    }


}
