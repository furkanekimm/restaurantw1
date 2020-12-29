package com.example.restaurantapii.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class WaiterDTO extends BaseDTO implements Serializable {
    private String waiterName;
    private String waiterLastName;
    private Long phoneNumber;
    private String email;
    private MediaDTO media;

}
