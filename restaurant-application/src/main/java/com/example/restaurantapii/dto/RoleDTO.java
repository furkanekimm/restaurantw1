package com.example.restaurantapii.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RoleDTO extends BaseDTO implements Serializable {
    private String name;
}
