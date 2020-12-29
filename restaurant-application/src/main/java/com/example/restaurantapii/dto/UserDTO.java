package com.example.restaurantapii.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class UserDTO extends BaseDTO implements Serializable {
    private String email;
    private String username;
    private String password;
    private Boolean enabled = true;
    private List<Long> rolesId;
    private List<RoleDTO> roles;
}
