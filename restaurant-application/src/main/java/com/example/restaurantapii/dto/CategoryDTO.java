package com.example.restaurantapii.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


@Getter
@Setter
public class CategoryDTO extends BaseDTO implements Serializable {
    @NotEmpty(message = "{RECORD_SHOULD_GET_NAME}")
    private String name;
    private String description;
    private Long mediaId;
    private byte[] fileContent;

    // private List<ProductDTO> products = new ArrayList<>();

    private MediaDTO media;
}
