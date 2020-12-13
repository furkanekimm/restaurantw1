package com.example.restaurantapii.services;

import com.example.restaurantapii.builder.CartDTOBuilder;
import com.example.restaurantapii.converters.DTOConverter;
import com.example.restaurantapii.converters.EntityConvertor;
import com.example.restaurantapii.dto.CartDTO;
import com.example.restaurantapii.entity.Cart;
import com.example.restaurantapii.repository.CartRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private CartRepository cartRepository;

    private List<CartDTO> cartListDTO = new ArrayList<>();

    private CartDTO cartDTO = new CartDTO();

    private List<Cart> cartList = new ArrayList<>();

    @Before
    public void setUp() throws Exception{
        cartDTO = new CartDTOBuilder().id(1L).categoryId(1L).piece(4L).productId(1L).tableId(1L).total(22L).build();
        cartListDTO.add(cartDTO);
    }

    @Test
    public void shouldAddCart(){
        cartListDTO.forEach(cart -> cartList.add(DTOConverter.convertToCartDTO(cartDTO)));
        when(cartRepository.saveAll(any())).thenReturn(cartList);
        List<CartDTO> cartDTOList = cartService.addCart(cartListDTO);
        assertNotNull(cartDTOList);
        assertEquals(cartDTOList.get(0).getId(),cartList.get(0).getId());
    }

    @Test
    public void shouldGetAllCarts(){
        cartListDTO.forEach(cart -> cartList.add(DTOConverter.convertToCartDTO(new CartDTOBuilder().id(1L).categoryId(1L).piece(4L).productId(1L).tableId(1L).total(22L).build())));
        when(cartRepository.findAll()).thenReturn(cartList);
        List<CartDTO> cartListDTO = cartService.allCarts();
        assertNotNull(cartListDTO);
    }


}
