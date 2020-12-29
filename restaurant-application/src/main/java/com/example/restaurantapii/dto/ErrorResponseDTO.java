package com.example.restaurantapii.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorResponseDTO implements Serializable {
    private Date timeStamp;
    private String message;
    private String details;
    private Integer errorCode;
    private Locale locale;

}
