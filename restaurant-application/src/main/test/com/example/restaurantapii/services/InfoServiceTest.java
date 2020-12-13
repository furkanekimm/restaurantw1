package com.example.restaurantapii.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.example.restaurantapii.dto.InfoProfileDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class InfoServiceTest {
    @InjectMocks
    private InfoService infoService;



    @Before
    public void setUp(){

    }

    @Test
    public void shouldGetAllInfos(){
        List<InfoProfileDTO> infoProfileDTOList = new ArrayList<>();
        when(infoService.getServerPort()).thenReturn(infoProfileDTOList);
        List<InfoProfileDTO> res = infoService.getServerPort();
        assertEquals(res,infoProfileDTOList);
    }

}
