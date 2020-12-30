package com.example.restaurantapii.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
@Getter
@Setter
public class ProductDTO extends BaseDTO implements Serializable {
    @NotEmpty(message = "{RECORD_SHOULD_GET_NAME}")
    private String productName;
    private String description;
    private Long price;
    private List<CategoryDTO> category;
    private List<Long> categoryId;
    private MediaDTO media;
}
