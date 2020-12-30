package com.example.restaurantapii.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
public class CustomerDTO extends BaseDTO implements Serializable {
    @NotEmpty(message = "{RECORD_SHOULD_GET_NAME}")
    private String name;
    private String lastName;
    private String address;
    @Min(value = 5)
    private Long phone;

    private MediaDTO media;
}
