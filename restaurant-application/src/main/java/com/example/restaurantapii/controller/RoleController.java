package com.example.restaurantapii.controller;


import com.example.restaurantapii.dto.RoleDTO;
import com.example.restaurantapii.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public RoleDTO addRole(@RequestBody RoleDTO roleDTO){
        return roleService.addRole(roleDTO);
    }

    @PutMapping("/update")
    public RoleDTO updateRole(@RequestBody RoleDTO roleDTO){
        return roleService.updateRole(roleDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id){
        return roleService.deleteRole(id);
    }

    @GetMapping("/list")
    public List<RoleDTO> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public RoleDTO getRoleByID(@PathVariable Long id){
        return roleService.getRoleById(id);
    }
}
