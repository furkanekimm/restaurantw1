package com.example.restaurantapii.dto;


import java.util.List;

public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String password;
    private Boolean enabled = true;
    private List<Long> rolesId;

    public List<Long> getRolesId() {
        return rolesId;
    }

    public void setRolesId(List<Long> rolesId) {
        this.rolesId = rolesId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
