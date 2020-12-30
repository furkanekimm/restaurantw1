package com.example.restaurantapii.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;


import com.example.restaurantapii.Mapper.MediaMapper;
import com.example.restaurantapii.Mapper.TableMapper;
import com.example.restaurantapii.builder.MediaDTOBuilder;
import com.example.restaurantapii.builder.PlaceRestDTOBuilder;
import com.example.restaurantapii.dto.CustomerDTO;
import com.example.restaurantapii.dto.MediaDTO;
import com.example.restaurantapii.dto.PlaceRestDTO;
import com.example.restaurantapii.entity.Media;
import com.example.restaurantapii.entity.PlaceRest;
import com.example.restaurantapii.exceptions.BusinessRuleException;
import com.example.restaurantapii.exceptions.ContentNotFoundException;
import com.example.restaurantapii.exceptions.SystemException;
import com.example.restaurantapii.repository.PlaceRestRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class PlaceRestTest {

    @InjectMocks
    private PlaceRestService placeRestService;

    @Mock
    private PlaceRestRepository placeRestRepository;

    @Mock
    private TableMapper tableMapper;

    @Mock
    private MediaMapper mediaMapper;

    private PlaceRestDTO placeRestDTO = new PlaceRestDTO();
    private PlaceRest placeRest = new PlaceRest();
    private List<PlaceRestDTO> placeRestDTOList = new ArrayList<>();
    private MediaDTO mediaDTO = new MediaDTO();
    private Media media = new Media();
    private List<PlaceRest> placeRestList = new ArrayList<>();

    @Before
    public void setUp(){
        mediaDTO = new MediaDTOBuilder().id(2L).build();
        media.setId(1L);
        when(mediaMapper.toEntity(any())).thenReturn(media);
        placeRest.setName("abc");
        placeRest.setId(2L);
        placeRest.setMedia(media);
        placeRest.setTablePiece(4L);
        placeRestDTO = new PlaceRestDTOBuilder().id(1L).name("Terrace").tablePiece(12L).build();
        placeRestDTO.setMedia(mediaDTO);
        placeRestDTOList.add(placeRestDTO);
        placeRestList.add(placeRest);
        when(tableMapper.toEntity(any())).thenReturn(placeRest);
        when(tableMapper.toDTO(any())).thenReturn(placeRestDTO);
        when(tableMapper.toDTOList(any())).thenReturn(placeRestDTOList);
    }

    @Test
    public void shouldAddTable(){
        when(placeRestRepository.save(any())).thenReturn(placeRest);
        PlaceRestDTO res = placeRestService.addPlaceRest(placeRestDTO);
        assertNotNull(res);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotAddTableWhenNameNull(){
        placeRestDTO.setName(null);
        when(placeRestRepository.save(any())).thenReturn(placeRest);
        placeRestService.addPlaceRest(placeRestDTO);
    }

    @Test
    public void shouldGetAllTables(){
        when(placeRestRepository.findAll()).thenReturn(placeRestList);
        List<PlaceRestDTO> res = placeRestService.listPlaceRests();
        assertNotNull(res);
    }

    @Test
    public void shouldDeleteCustomer(){
        Long id=1L;
        when(placeRestRepository.existsById(any())).thenReturn(Boolean.TRUE);
        placeRestService.deletePlaceRest(id);
        verify(placeRestRepository).deleteById(id);
    }

    @Test(expected = ContentNotFoundException.class)
    public void shouldNotDeleteWhenIdNotFound(){
        Long id=1L;
        when(placeRestRepository.existsById(any())).thenReturn(Boolean.FALSE);
        placeRestService.deletePlaceRest(id);
    }

    @Test
    public void shouldGetCustomerByID(){
        when(placeRestRepository.findById(any())).thenReturn(Optional.of(placeRest));
        PlaceRestDTO res = placeRestService.getTableById(placeRestDTO.getId());
        assertNotNull(res);
    }

    @Test(expected = SystemException.class)
    public void shouldNotGetCustomerWhenCustomerNotFound(){
        Long id =1L;
        when(placeRestRepository.findById(any())).thenReturn(Optional.empty());
        placeRestService.getTableById(id);
    }

    @Test
    public void shouldUpdateCustomer(){
        when(placeRestRepository.findById(any())).thenReturn(Optional.of(placeRest));
        when(placeRestRepository.save(any())).thenReturn(placeRest);
        PlaceRestDTO res = placeRestService.updatePlaceRest(placeRestDTO);
        assertNotNull(res);
        assertNotEquals(res.getId(),placeRest.getId());
    }

    @Test(expected = ContentNotFoundException.class)
    public void shouldNotUpdateWhenContentNotFound(){
        when(placeRestRepository.findById(any())).thenReturn(Optional.empty());
        placeRestService.updatePlaceRest(placeRestDTO);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotUpdateWhenContentIdNULL(){
        placeRestDTO.setId(null);
        placeRestService.updatePlaceRest(placeRestDTO);
    }


}
