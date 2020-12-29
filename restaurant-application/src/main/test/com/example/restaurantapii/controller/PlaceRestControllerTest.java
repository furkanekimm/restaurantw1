package com.example.restaurantapii.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.example.restaurantapii.builder.PlaceRestDTOBuilder;
import com.example.restaurantapii.dto.PlaceRestDTO;
import com.example.restaurantapii.repository.PlaceRestRepository;
import com.example.restaurantapii.services.PlaceRestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PlaceRestControllerTest {
    @InjectMocks
    private PlaceRestController placeRestController;

    @Mock
    private PlaceRestService placeRestService;

    private PlaceRestDTO placeRestDTO = new PlaceRestDTO();

    private List<PlaceRestDTO> placeRestDTOList = new ArrayList<>();

    @Before
    public void setUp(){
        placeRestDTO = new PlaceRestDTOBuilder().id(1L).name("Terrace").tablePiece(12L).build();
        placeRestDTOList.add(placeRestDTO);
    }

    @Test
    public void shouldAddPlaceRest(){
        when(placeRestService.addPlaceRest(any())).thenReturn(placeRestDTO);
        PlaceRestDTO res = placeRestController.addPlaceRest(placeRestDTO);
        assertNotNull(res);
        assertEquals(res,placeRestDTO);
    }

    @Test
    public void shouldPlaceRestListAll(){
        when(placeRestService.listPlaceRests()).thenReturn(placeRestDTOList);
        List<PlaceRestDTO> res = placeRestController.placeRestList();
        assertNotNull(res);
        assertEquals(res,placeRestDTOList);
    }

    @Test
    public void shouldDeletePlaceRest(){
        Long id=1L;
        when(placeRestService.deletePlaceRest(any())).thenReturn(Boolean.TRUE);
        Boolean res = placeRestController.deletePlaceRest(id);
        assertNotNull(res);
        assertEquals(true,res);
    }

    @Test
    public void shouldNotDeletePlaceRest(){
        Long id = 1L;
        when(placeRestService.deletePlaceRest(any())).thenReturn(Boolean.FALSE);
        Boolean res = placeRestController.deletePlaceRest(id);
        assertNotNull(res);
        assertEquals(false,res);
    }

    @Test
    public void shouldUpdatePlaceRest(){
        when(placeRestService.updatePlaceRest(any())).thenReturn(placeRestDTO);
        PlaceRestDTO res = placeRestController.updatePlace(placeRestDTO);
        assertNotNull(res);
        assertEquals(res,placeRestDTO);
    }

    @Test
    public void shouldGetTableById(){
        when(placeRestService.getTableById(any())).thenReturn(placeRestDTO);
        PlaceRestDTO res = placeRestController.getTableById(placeRestDTO.getId());
        assertNotNull(res);
    }

}
