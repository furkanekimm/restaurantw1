package com.example.restaurantapii.Mapper;

import com.example.restaurantapii.dto.CategoryDTO;
import com.example.restaurantapii.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryDTO categoryDTO);

    CategoryDTO toDTO(Category category);

    List<Category> toEntityList(List<CategoryDTO> categoryDTOS);

    List<CategoryDTO> toDTOList(List<Category> categories);
}
