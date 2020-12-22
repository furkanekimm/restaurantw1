package com.example.restaurantapii.services;

import com.example.restaurantapii.Mapper.CartMapper;
import com.example.restaurantapii.dto.CartDTO;
import com.example.restaurantapii.entity.Cart;
import com.example.restaurantapii.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartMapper cartMapper;

    public List<CartDTO> addCart(List<CartDTO> cartDto){
        List<Cart> cartList = cartMapper.toEntityList(cartDto);
        cartRepository.saveAll(cartList);
        return cartDto;
    }


    public List<CartDTO> allCarts(){
        List<CartDTO> cartDTOList = cartMapper.toDTOList(cartRepository.findAll());
        return cartDTOList;
    }

}
