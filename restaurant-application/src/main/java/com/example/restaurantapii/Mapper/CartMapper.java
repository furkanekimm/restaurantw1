package com.example.restaurantapii.Mapper;

import com.example.restaurantapii.dto.CartDTO;
import com.example.restaurantapii.entity.Cart;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartMapper {


    CartDTO toDTO(Cart cart);

    Cart toEntity(CartDTO cartDTO);

    List<Cart> toEntityList(List<CartDTO> cartDTOS);

    List<CartDTO> toDTOList(List<Cart> carts);
}
