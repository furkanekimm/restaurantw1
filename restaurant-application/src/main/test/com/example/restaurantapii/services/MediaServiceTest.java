package com.example.restaurantapii.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import com.example.restaurantapii.Mapper.MediaMapper;
import com.example.restaurantapii.builder.MediaDTOBuilder;
import com.example.restaurantapii.dto.MediaDTO;
import com.example.restaurantapii.entity.Media;
import com.example.restaurantapii.repository.MediaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class MediaServiceTest {
    @InjectMocks
    private MediaService mediaService;

    @Mock
    private MediaRepository mediaRepository;

    @Spy
    private MediaMapper mediaMapper = Mappers.getMapper(MediaMapper.class);

    MediaDTO mediaDTO = new MediaDTO();
    Media media = new Media();
    List<MediaDTO> mediaDTOList = new ArrayList<>();
    List<Media> mediaList = new ArrayList<>();
    @Before
    public void setUp(){
    String uploadDir = "/Users/furkanekim/IdeaProjects/restaurant-application/target/media/";

    byte[] myvar = "Any String you want".getBytes();
    mediaDTO = new MediaDTOBuilder().id(1L).name("test.png").fileContent(myvar).build();
    media = mediaMapper.toEntity(mediaDTO);
    }

    @Test
    public void shouldAddMedia(){


    }

    @Test
    public void shouldGetAllFiles(){


    }

    @Test
    public void shouldDeleteMedia(){
        Long id = 1L;
        when(mediaRepository.findById(any())).thenReturn(Optional.of(media));
        Boolean res = mediaService.deleteMedia(id);
        assertEquals(res,true);
    }

    }
