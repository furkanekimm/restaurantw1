package com.example.restaurantapii.Mapper;

import com.example.restaurantapii.dto.WaiterDTO;
import com.example.restaurantapii.entity.Waiter;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WaiterMapper {

    Waiter toEntity(WaiterDTO waiterDTO);

    WaiterDTO toDTO(Waiter waiter);

    List<Waiter> toEntityList(List<WaiterDTO> waiterDTOList);

    List<WaiterDTO> toDTOList(List<Waiter> waiterList);

}
