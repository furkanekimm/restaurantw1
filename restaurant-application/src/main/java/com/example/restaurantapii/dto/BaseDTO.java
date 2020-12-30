package com.example.restaurantapii.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public abstract class BaseDTO {
    @Min(value = 0,message = "{ID_NOT_BE_LITTLE_ZERO}")
    private Long id ;
}
