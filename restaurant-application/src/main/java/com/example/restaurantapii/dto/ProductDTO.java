package com.example.restaurantapii.dto;

import com.example.restaurantapii.entity.Category;
import com.example.restaurantapii.entity.Media;

import java.util.List;
import java.util.Set;

public class ProductDTO {
    private Long id;
    private String productName;
    private String description;
    private Long price;
    private String urlToImage;
    private Set<Category> categoryName;
    private List<Long> categoryId;
    private Media media;

    public Media getMedia() {
        return media;
    }

    public void setMedia(Media media) {
        this.media = media;
    }

    public Set<Category> getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Set<Category> categoryName) {
        this.categoryName = categoryName;
    }

    private Set<CategoryDTO> categoryDTOSet;

    public Set<CategoryDTO> getCategoryDTOSet() {
        return categoryDTOSet;
    }

    public void setCategoryDTOSet(Set<CategoryDTO> categoryDTOSet) {
        this.categoryDTOSet = categoryDTOSet;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public List<Long> getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(List<Long> categoryId) {
        this.categoryId = categoryId;
    }
}
