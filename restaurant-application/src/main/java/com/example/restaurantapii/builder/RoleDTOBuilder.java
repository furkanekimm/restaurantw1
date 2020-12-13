package com.example.restaurantapii.builder;

import com.example.restaurantapii.dto.RoleDTO;

public class RoleDTOBuilder extends Builder{
    private String name;

    @Override
    public RoleDTO build() {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(super.id);
        roleDTO.setName(this.name);
        return roleDTO;
    }

    public RoleDTOBuilder id(Long id){
        super.id = id;
        return this;
    }

    public RoleDTOBuilder name(String name) {
        this.name = name;
        return this;
    }
}
