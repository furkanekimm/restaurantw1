package com.example.restaurantapii.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.example.restaurantapii.builder.CartDTOBuilder;
import com.example.restaurantapii.dto.CartDTO;
import com.example.restaurantapii.services.CartService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {

    @InjectMocks
    private CartController cartController;

    @Mock
    private CartService cartService;

    CartDTO cartDTO = new CartDTO();

    private List<CartDTO> cartListDTO = new ArrayList<>();

    @Before
    public void setUp(){
        cartDTO = new CartDTOBuilder().id(1L).categoryId(1L).piece(4L).build();
        cartListDTO.add(cartDTO);
    }

    @Test
    public void shouldAddCarts(){
        when(cartService.addCart(any())).thenReturn(cartListDTO);
        List<CartDTO> res = cartController.addCart(cartListDTO);
        assertNotNull(res);
        assertEquals(res,cartListDTO);
    }
    
    @Test
    public void shouldAllCategories(){
        when(cartService.allCarts()).thenReturn(cartListDTO);
        List<CartDTO> res = cartController.allCarts();
        assertNotNull(res);
        assertEquals(res,cartListDTO);
    }

}
