package com.example.restaurantapii.Mapper;

import com.example.restaurantapii.dto.CategoryDTO;
import com.example.restaurantapii.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toEntity(CategoryDTO categoryDTO);

    CategoryDTO toDTO(Category category);
}
