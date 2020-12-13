package com.example.restaurantapii.builder;

import com.example.restaurantapii.dto.UserDTO;

import java.util.List;

public class UserDTOBuilder extends Builder {
    private String email;
    private String username;
    private String password;
    private Boolean enabled = true;
    private List<Long> rolesId;

    @Override
    public UserDTO build() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(super.id);
        userDTO.setUsername(this.username);
        userDTO.setEmail(this.email);
        userDTO.setPassword(this.password);
        userDTO.setEnabled(this.enabled);
        userDTO.setRolesId(this.rolesId);
        return userDTO;
    }

    public UserDTOBuilder roles(List<Long> roles){
        this.rolesId = roles;
        return this;
    }

    public UserDTOBuilder id(Long id){
        super.id = id;
        return this;
    }

    public UserDTOBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserDTOBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserDTOBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserDTOBuilder enabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
