package com.example.restaurantapii.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.example.restaurantapii.builder.MediaDTOBuilder;
import com.example.restaurantapii.builder.WaiterDTOBuilder;
import com.example.restaurantapii.converters.DTOConverter;
import com.example.restaurantapii.converters.EntityConvertor;
import com.example.restaurantapii.dto.MediaDTO;
import com.example.restaurantapii.dto.WaiterDTO;
import com.example.restaurantapii.entity.Media;
import com.example.restaurantapii.entity.Waiter;
import com.example.restaurantapii.repository.MediaRepository;
import com.example.restaurantapii.repository.WaiterRepository;
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
public class WaiterServiceTest {

    @InjectMocks
    private WaiterService waiterService;

    @Mock
    private WaiterRepository waiterRepository;

    @Mock
    private MediaRepository mediaRepository;

    private WaiterDTO waiterDTO = new WaiterDTO();

    private MediaDTO mediaDTO = new MediaDTO();

    private Media media = new Media();

    private Waiter waiter = new Waiter();

    private List<WaiterDTO> waiterDTOList = new ArrayList<>();

    @Before
    public void setUp(){
        media.setId(1L);
        media.setName("abc.PNG");
        waiterDTO = new WaiterDTOBuilder().id(1L).waiterName("Furkan").waiterLastName("Ekim").phoneNumber(53164339L).email("furkane@gma").media(media).build();
        mediaDTO = new MediaDTOBuilder().id(1L).name("abc.PNG").build();
    }

    @Test
    public void shouldAddWaiter(){
        when(mediaRepository.findById(any())).thenReturn(Optional.of(waiterDTO.getMedia()));
        when(waiterRepository.save(any())).thenReturn(DTOConverter.convertDTOWaiter(waiterDTO));
        Boolean res = waiterService.addWaiter(waiterDTO);
        assertNotNull(res);
        assertEquals(true,res);
    }



    @Test
    public void shouldUpdateWaiter(){
        when(mediaRepository.findById(any())).thenReturn(Optional.of(waiterDTO.getMedia()));
        when(waiterRepository.saveAndFlush(any())).thenReturn(DTOConverter.convertDTOWaiter(waiterDTO));
        WaiterDTO res = waiterService.updateWaiter(waiterDTO);
        assertNotNull(res);
        assertEquals(res,waiterDTO);
    }

    @Test
    public void shouldGetWaiterByID(){
        Long id = 1L;
        when(waiterRepository.findById(any())).thenReturn(Optional.of(DTOConverter.convertDTOWaiter(waiterDTO)));
        WaiterDTO res = waiterService.getWaiterByID(waiterDTO.getId());
        assertNotNull(res);
        assertEquals(res.getId(),waiterDTO.getId());
    }

    @Test
    public void shouldDeleteWaiterByID(){
        when(waiterRepository.existsById(any())).thenReturn(Boolean.TRUE);
        Boolean res = waiterService.deleteWaiter(waiterDTO.getId());
        assertNotNull(res);
        assertEquals(true,res);
    }

    @Test
    public void shouldNotDeleteWaiterByID(){
        when(waiterRepository.existsById(any())).thenReturn(Boolean.FALSE);
        Boolean res = waiterService.deleteWaiter(waiterDTO.getId());
        assertNotNull(res);
        assertEquals(false,res);
    }

    @Test
    public void shouldListAllWaiter(){
        List<Waiter> waiterList = new ArrayList<>();
        when(waiterRepository.findAll()).thenReturn(waiterList);
        List<WaiterDTO> res = waiterService.listAllWaiters();
        assertNotNull(res);
        assertEquals(res,waiterList);
    }
}
