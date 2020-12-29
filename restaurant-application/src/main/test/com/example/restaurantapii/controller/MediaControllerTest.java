package com.example.restaurantapii.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

import com.example.restaurantapii.builder.MediaDTOBuilder;
import com.example.restaurantapii.dto.MediaDTO;
import com.example.restaurantapii.services.MediaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MediaControllerTest {
    @InjectMocks
    private MediaController mediaController;

    @Mock
    private MediaService mediaService;

    private MediaDTO mediaDTO = new MediaDTO();
    private List<MediaDTO> mediaDTOList = new ArrayList<>();
    private MultipartFile multipartFile;

    @Before
    public void setUp(){
        mediaDTO = new MediaDTOBuilder().id(1L).name("abc").build();
        mediaDTOList.add(mediaDTO);
    }

    @Test
    public void shouldAddMedia() throws IOException {
        String name = "adv";
        when(mediaService.addMedia(any(),any())).thenReturn("okey");
        String res = mediaController.addMedia(multipartFile,name);
        assertNotNull(res);
    }

    @Test
    public void shouldGetAllMedia(){
        when(mediaService.getWholeFiles()).thenReturn(mediaDTOList);
        List<MediaDTO> res = mediaController.getWholeFiles();
        assertNotNull(res);
    }

    @Test
    public void shouldDeleteMedia(){
        when(mediaService.deleteMedia(any())).thenReturn(Boolean.TRUE);
        Boolean res = mediaController.deleteMedia(mediaDTO.getId());
        assertEquals(res,true);
    }


}
