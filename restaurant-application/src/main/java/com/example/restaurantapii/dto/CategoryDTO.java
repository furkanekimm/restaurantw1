package com.example.restaurantapii.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Data
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private String urlToImage;
    private Long mediaId;
    private byte[] fileContent;

   // private List<ProductDTO> products = new ArrayList<>();

    private MediaDTO media;


}
