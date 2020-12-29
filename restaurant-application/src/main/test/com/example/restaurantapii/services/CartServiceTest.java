package com.example.restaurantapii.services;

import com.example.restaurantapii.Mapper.CartMapper;
import com.example.restaurantapii.builder.CartDTOBuilder;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CartMapper cartMapper;

    private List<CartDTO> cartListDTO = new ArrayList<>();

    private CartDTO cartDTO = new CartDTO();
    private  List<Cart> cartList = new ArrayList<>();

    @Before
    public void setUp() throws Exception{
        cartDTO = new CartDTOBuilder().id(1L).categoryId(1L).piece(4L).productId(1L).tableId(1L).total(22L).build();
        cartListDTO.add(cartDTO);
        cartList.add(cartMapper.toEntity(cartDTO));
    }

    @Test
    public void shouldAddCart(){
        when(cartMapper.toEntityList(any())).thenReturn(cartList);
        when(cartRepository.saveAll(any())).thenReturn(cartList);
        List<CartDTO> cartDTOList = cartService.addCart(cartListDTO);
        assertNotNull(cartDTOList);
        assertEquals(cartDTOList.get(0).getId(),cartList.get(0).getId());
    }

    @Test
    public void shouldGetAllCarts(){
        when(cartRepository.findAll()).thenReturn(cartList);
        List<CartDTO> cartListDTO = cartService.allCarts();
        assertNotNull(cartListDTO);
    }


}
