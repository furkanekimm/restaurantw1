package com.example.restaurantapii.dto;

import com.example.restaurantapii.entity.Media;

import java.util.Set;

public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private String urlToImage;
    private Long mediaId;
    private byte[] fileContent;



    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }
}
