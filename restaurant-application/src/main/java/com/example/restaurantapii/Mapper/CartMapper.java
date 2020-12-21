package com.example.restaurantapii.Mapper;

import com.example.restaurantapii.dto.CartDTO;
import com.example.restaurantapii.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CartMapper {

    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    CartDTO toDTO(Cart cart);

    Cart toEntity(CartDTO cartDTO);
}
