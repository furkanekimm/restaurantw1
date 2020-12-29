package com.example.restaurantapii.builder;

import com.example.restaurantapii.dto.CategoryDTO;

public class CategoryDTOBuilder extends Builder {
    private String name;
    private String description;

    @Override
    public CategoryDTO build() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(this.name);
        categoryDTO.setId(super.id);
        categoryDTO.setDescription(this.description);
        return categoryDTO;
    }

    public CategoryDTOBuilder name(String name){
        this.name=name;
        return this;
    }

    public CategoryDTOBuilder id(Long id){
        super.id=id;
        return this;
    }

    public CategoryDTOBuilder description(String description){
        this.description=description;
        return this;
    }

}
