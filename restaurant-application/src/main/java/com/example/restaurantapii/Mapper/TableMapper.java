package com.example.restaurantapii.Mapper;

import com.example.restaurantapii.dto.PlaceRestDTO;
import com.example.restaurantapii.entity.PlaceRest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TableMapper {

    PlaceRestDTO toDTO(PlaceRest placeRest);

    PlaceRest toEntity(PlaceRestDTO dto);

    List<PlaceRestDTO> toDTOList(List<PlaceRest> placeRests);

    List<PlaceRest> toEntityList(List<PlaceRestDTO> placeRests);

}
