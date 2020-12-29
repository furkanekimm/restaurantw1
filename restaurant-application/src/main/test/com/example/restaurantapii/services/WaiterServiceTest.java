package com.example.restaurantapii.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.example.restaurantapii.Mapper.MediaMapper;
import com.example.restaurantapii.Mapper.WaiterMapper;
import com.example.restaurantapii.builder.MediaDTOBuilder;
import com.example.restaurantapii.builder.WaiterDTOBuilder;
import com.example.restaurantapii.dto.MediaDTO;
import com.example.restaurantapii.dto.WaiterDTO;
import com.example.restaurantapii.entity.Media;
import com.example.restaurantapii.entity.Waiter;
import com.example.restaurantapii.exceptions.ContentNotFoundException;
import com.example.restaurantapii.exceptions.SystemException;
import com.example.restaurantapii.repository.MediaRepository;
import com.example.restaurantapii.repository.WaiterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
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

    @Spy
    private WaiterMapper waiterMapper = Mappers.getMapper(WaiterMapper.class);

    @Spy
    private MediaMapper mediaMapper = Mappers.getMapper(MediaMapper.class);

    private WaiterDTO waiterDTO = new WaiterDTO();
    private MediaDTO mediaDTO = new MediaDTO();
    private Media media = new Media();
    private Waiter waiter = new Waiter();
    private List<WaiterDTO> waiterDTOList = new ArrayList<>();

    @Before
    public void setUp(){
        media.setId(1L);
        media.setName("abc.PNG");
        mediaDTO = mediaMapper.toDTO(media);
        waiterDTO = new WaiterDTOBuilder().id(1L).waiterName("Furkan").waiterLastName("Ekim").phoneNumber(53164339L).email("furkane@gma").media(mediaDTO).build();
        waiter = waiterMapper.toEntity(waiterDTO);
        when(mediaMapper.toEntity(any())).thenReturn(media);
    }

    @Test
    public void shouldAddWaiter(){
        when(waiterRepository.save(any())).thenReturn(waiter);
        Boolean res = waiterService.addWaiter(waiterDTO);
        assertNotNull(res);
        assertEquals(true,res);
    }



    @Test(expected = ContentNotFoundException.class)
    public void shouldNotUpdateWaiter(){
        when(mediaRepository.findById(any())).thenReturn(Optional.of(media));
        when(waiterRepository.saveAndFlush(any())).thenReturn(waiter);
        waiterService.updateWaiter(waiterDTO);
    }

    @Test
    public void shouldUpdataWaiter(){
        waiterDTO = new WaiterDTOBuilder().email("a").phoneNumber(111L).waiterLastName("B").media(mediaDTO).waiterName("C").id(2L).build();
        when(waiterRepository.findById(any())).thenReturn(Optional.of(waiter));
        when(waiterRepository.save(any())).thenReturn(waiter);
        WaiterDTO res = waiterService.updateWaiter(waiterDTO);
        assertNotNull(res);

    }

    @Test
    public void shouldGetWaiterByID(){
        Long id = 1L;
        when(waiterRepository.findById(any())).thenReturn(Optional.of(waiter));
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

    @Test(expected = SystemException.class)
    public void shouldNotDeleteWaiterByID(){
        when(waiterRepository.existsById(any())).thenReturn(Boolean.FALSE);
        waiterService.deleteWaiter(waiterDTO.getId());
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
