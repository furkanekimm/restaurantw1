package com.example.restaurantapii.dto;

import com.example.restaurantapii.entity.Media;
import lombok.Data;

@Data
public class WaiterDTO {

    private Long id;
    private String waiterName;
    private String waiterLastName;
    private Long phoneNumber;
    private String email;
    private MediaDTO mediadto;

}
