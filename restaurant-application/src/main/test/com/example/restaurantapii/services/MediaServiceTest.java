package com.example.restaurantapii.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.example.restaurantapii.builder.MediaDTOBuilder;
import com.example.restaurantapii.converters.DTOConverter;
import com.example.restaurantapii.converters.EntityConvertor;
import com.example.restaurantapii.dto.MediaDTO;
import com.example.restaurantapii.entity.Media;
import com.example.restaurantapii.repository.MediaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MediaServiceTest {
    @InjectMocks
    private MediaService mediaService;

    @Mock
    private MediaRepository mediaRepository;

    MediaDTO mediaDTO = new MediaDTO();
    List<MediaDTO> mediaDTOList = new ArrayList<>();
    List<Media> mediaList = new ArrayList<>();
    @Before
    public void setUp(){
    mediaDTO = new MediaDTOBuilder().id(1L).name("test.png").build();
    }

    @Test
    public void shouldAddMedia(){
       /* MultipartFile multipartFile = "c";
        String imageName="movie";
        when(mediaRepository.save(any())).thenReturn(DTOConverter.convertToMediaDTO(new MediaDTOBuilder().id(1L).name("movie").build()));
        verify()*/
    }

    @Test
    public void shouldGetAllFiles(){
        mediaDTOList.add(mediaDTO);
        mediaDTOList.forEach(mediaDTO1 -> mediaList.add(DTOConverter.convertToMediaDTO(mediaDTO1)));
        when(mediaRepository.findAll()).thenReturn(mediaList);
        List<MediaDTO> res = mediaService.getWholeFiles();
        assertNotNull(res.get(0).getName(),mediaList.get(0).getName());

    }


    }
