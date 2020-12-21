package com.example.restaurantapii.dto;

import com.example.restaurantapii.entity.Category;
import com.example.restaurantapii.entity.Media;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
import java.util.Set;
@Data
public class ProductDTO {
    private Long id;
    private String productName;
    private String description;
    private Long price;
    private String urlToImage;
    private Set<CategoryDTO> categoryName;
    private List<Long> categoryId;
    private Media media;

}
