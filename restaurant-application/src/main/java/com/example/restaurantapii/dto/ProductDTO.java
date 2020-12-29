package com.example.restaurantapii.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
public class ProductDTO extends BaseDTO implements Serializable {
    private String productName;
    private String description;
    private Long price;
    private List<CategoryDTO> category;
    private List<Long> categoryId;
    private MediaDTO media;
}
