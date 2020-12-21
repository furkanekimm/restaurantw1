package com.example.restaurantapii.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private String urlToImage;
    private Long mediaId;
    private byte[] fileContent;

    private Set<ProductDTO>  products = new HashSet<>();

    private MediaDTO media;


}
