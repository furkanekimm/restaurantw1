package com.example.restaurantapii.dto;


import lombok.Data;

import java.util.List;
@Data
public class ProductDTO {
    private Long id;
    private String productName;
    private String description;
    private Long price;
    private String urlToImage;
    private List<CategoryDTO> category;
    private List<Long> categoryId;
    private MediaDTO media;

}
