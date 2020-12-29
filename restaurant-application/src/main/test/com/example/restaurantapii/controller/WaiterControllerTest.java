package com.example.restaurantapii.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.example.restaurantapii.Mapper.WaiterMapper;
import com.example.restaurantapii.dto.WaiterDTO;
import com.example.restaurantapii.services.WaiterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class WaiterControllerTest {
    @InjectMocks
    private WaiterController waiterController;

    @Mock
    private WaiterService waiterService;


    WaiterDTO waiterDTO = new WaiterDTO();

    @Before
    public void setUp(){
        waiterDTO.setId(1L);
        waiterDTO.setWaiterName("Furkan");
        waiterDTO.setWaiterLastName("Ekim");
        waiterDTO.setPhoneNumber(5316433981L);
        waiterDTO.setEmail("furkanekim@gmail.com");
    }

    @Test
    public void shouldAddWaiter(){
        when(waiterService.addWaiter(any())).thenReturn(Boolean.TRUE);
        Boolean res = waiterController.addWaiter(waiterDTO);
        assertNotNull(res);
        assertEquals(true,res);
    }

    @Test
    public void shouldNotAddWaiter(){
        when(waiterService.addWaiter(any())).thenReturn(Boolean.FALSE);
        Boolean res = waiterController.addWaiter(waiterDTO);
        assertNotNull(res);
        assertEquals(false,res);
    }

    @Test
    public void shouldUpdateWaiter(){
        when(waiterService.updateWaiter(any())).thenReturn(waiterDTO);
        WaiterDTO res = waiterController.updateWaiter(waiterDTO);
        assertNotNull(res);
        assertEquals(res,waiterDTO);
    }

    @Test
    public void shouldGetWaiterByID(){
        Long id = 1L;
        when(waiterService.getWaiterByID(any())).thenReturn(waiterDTO);
        WaiterDTO res = waiterController.getWaiterByID(id);
        assertNotNull(res);
        assertEquals(res,waiterDTO);
    }

    @Test
    public void shouldDeleteWaiter(){
        when(waiterService.deleteWaiter(any())).thenReturn(Boolean.TRUE);
        Boolean res = waiterController.deleteWaiter(waiterDTO.getId());
        assertNotNull(res);
        assertEquals(true,res);
    }

    @Test
    public void shouldNotDeleteWaiter(){
        when(waiterService.deleteWaiter(any())).thenReturn(Boolean.FALSE);
        Boolean res = waiterController.deleteWaiter(waiterDTO.getId());
        assertNotNull(res);
        assertEquals(false,res);
    }

    @Test
    public void shouldAllWaiters(){
        List<WaiterDTO> waiterDTOList = new ArrayList<>();
        waiterDTOList.add(waiterDTO);
        when(waiterService.listAllWaiters()).thenReturn(waiterDTOList);
        List<WaiterDTO> res = waiterController.getAllWaiter();
        assertNotNull(res);
        assertEquals(res,waiterDTOList);
    }



}
