package com.example.restaurantapii.Mapper;

import com.example.restaurantapii.dto.PlaceRestDTO;
import com.example.restaurantapii.entity.PlaceRest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TableMapper {

    TableMapper INSTANCE = Mappers.getMapper(TableMapper.class);

    PlaceRestDTO toDTO(PlaceRest placeRest);

    PlaceRest toEntity(PlaceRestDTO dto);

}
