package com.example.restaurantapii.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CustomerDTO extends BaseDTO implements Serializable {
    private String name;
    private String lastName;
    private String address;
    private Long phone;

    private MediaDTO media;
}
