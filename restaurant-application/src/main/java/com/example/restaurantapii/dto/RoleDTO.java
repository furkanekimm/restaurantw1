package com.example.restaurantapii.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
public class RoleDTO extends BaseDTO implements Serializable {
    @NotEmpty(message = "{RECORD_SHOULD_GET_NAME}")
    private String name;
}
