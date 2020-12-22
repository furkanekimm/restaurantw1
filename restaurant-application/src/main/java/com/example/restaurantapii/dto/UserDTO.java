package com.example.restaurantapii.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String password;
    private Boolean enabled = true;
    private List<Long> rolesId;
    private List<RoleDTO> roles;
}
