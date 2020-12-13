package com.example.restaurantapii.services;

import com.example.restaurantapii.converters.DTOConverter;
import com.example.restaurantapii.converters.EntityConvertor;
import com.example.restaurantapii.dto.CartDTO;
import com.example.restaurantapii.entity.Cart;
import com.example.restaurantapii.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;


    public List<CartDTO> addCart(List<CartDTO> cartDto){
        List<Cart> cartList = new ArrayList<>();
        cartDto.forEach(cartDTO ->cartList.add(DTOConverter.convertToCartDTO(cartDTO)));
        cartRepository.saveAll(cartList);
        return cartDto;
    }


    public List<CartDTO> allCarts(){
        List<CartDTO> cartDTOList = new ArrayList<>();
        List<Cart> carts = cartRepository.findAll();
        carts.forEach(cart -> cartDTOList.add(EntityConvertor.convertToCart(cart)));
        return cartDTOList;
    }

}
