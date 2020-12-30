package com.example.restaurantapii.controller;


import com.example.restaurantapii.dto.RoleDTO;
import com.example.restaurantapii.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/role")
@Validated
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public RoleDTO addRole(@Valid @RequestBody RoleDTO roleDTO){
        return roleService.addRole(roleDTO);
    }

    @PutMapping("/update")
    public RoleDTO updateRole(@RequestBody RoleDTO roleDTO){
        return roleService.updateRole(roleDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteRole(@NotNull(message = "{ID_NULL}") @Min(value = 0,message = "{ID_NOT_BE_LITTLE_ZERO}") @PathVariable Long id){
        return roleService.deleteRole(id);
    }

    @GetMapping("/list")
    public List<RoleDTO> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public RoleDTO getRoleByID(@NotNull(message = "{ID_NULL}") @Min(value = 0,message = "{ID_NOT_BE_LITTLE_ZERO}") @PathVariable Long id){
        return roleService.getRoleById(id);
    }
}
