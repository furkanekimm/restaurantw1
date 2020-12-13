package com.example.restaurantapii.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.example.restaurantapii.builder.PlaceRestDTOBuilder;
import com.example.restaurantapii.converters.DTOConverter;
import com.example.restaurantapii.converters.EntityConvertor;
import com.example.restaurantapii.dto.PlaceRestDTO;
import com.example.restaurantapii.entity.PlaceRest;
import com.example.restaurantapii.repository.PlaceRestRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PlaceRestTest {

    @InjectMocks
    private PlaceRestService placeRestService;

    @Mock
    private PlaceRestRepository placeRestRepository;

    private PlaceRestDTO placeRestDTO = new PlaceRestDTO();

    private List<PlaceRestDTO> placeRestDTOList = new ArrayList<>();

    @Before
    public void setUp(){
        placeRestDTO = new PlaceRestDTOBuilder().id(1L).name("Terrace").tablePiece(12L).build();
        placeRestDTOList.add(placeRestDTO);
    }

    @Test
    public void shouldAddPlaceRest(){
        when(placeRestRepository.save(any())).thenReturn(DTOConverter.convertDTOPlaceRest(placeRestDTO));
        PlaceRestDTO res = placeRestService.addPlaceRest(placeRestDTO);
        assertNotNull(res);
        assertEquals(res,placeRestDTO);
    }

    @Test
    public void shouldListPlaceRests(){
        List<PlaceRest> placeRestList = new ArrayList<>();
        placeRestDTOList.forEach(placeRestDTO1 -> placeRestList.add(DTOConverter.convertDTOPlaceRest(placeRestDTO1)));
        when(placeRestRepository.findAll()).thenReturn(placeRestList);
        List<PlaceRestDTO> res = placeRestService.listPlaceRests();
        assertNotNull(res);
        assertEquals(res.get(0).getId(),placeRestList.get(0).getId());
    }

    @Test
    public void shouldUpdatePlaceRest(){
        when(placeRestRepository.saveAndFlush(any())).thenReturn(DTOConverter.convertDTOPlaceRest(placeRestDTO));
        PlaceRestDTO res = placeRestService.updatePlaceRest(placeRestDTO);
        assertNotNull(res);
        assertEquals(res,placeRestDTO);
    }

    @Test
    public void shouldDeletePlaceRest(){
        Long id = 1L;
        when(placeRestRepository.existsById(any())).thenReturn(Boolean.TRUE);
        Boolean res = placeRestService.deletePlaceRest(id);
        assertNotNull(res);
        assertEquals(res,true);
    }

    @Test
    public void shouldNotDeletePlaceRest(){
        Long id = 2L;
        when(placeRestRepository.existsById(any())).thenReturn(Boolean.FALSE);
        Boolean res = placeRestService.deletePlaceRest(id);
        assertNotNull(res);
        assertEquals(res,false);
    }



}
