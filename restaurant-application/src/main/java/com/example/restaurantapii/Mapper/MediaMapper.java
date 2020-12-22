package com.example.restaurantapii.Mapper;

import com.example.restaurantapii.dto.CategoryDTO;
import com.example.restaurantapii.dto.MediaDTO;
import com.example.restaurantapii.entity.Category;
import com.example.restaurantapii.entity.Media;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MediaMapper {

    Media toEntity(MediaDTO mediaDTO);

    MediaDTO toDTO(Media media);

    List<Media> toEntityList(List<MediaDTO> mediaDTOS);

    List<MediaDTO> toDTOList(List<Media> medias);
}
