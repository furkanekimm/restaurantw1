package com.example.restaurantapii.services;

import com.example.restaurantapii.Mapper.MediaMapper;
import com.example.restaurantapii.converters.DTOConverter;
import com.example.restaurantapii.converters.EntityConvertor;
import com.example.restaurantapii.dto.MediaDTO;
import com.example.restaurantapii.entity.Media;
import com.example.restaurantapii.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MediaService {
    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private MediaMapper mediaMapper;

    private static final String JPG_EXTENSION = ".jpg";
    private static final String PNG_EXTENSION = ".png";
    private static final String BMP_EXTENSION = ".bmp";

    private static final String BMP_CONTENT = "image/bmp";
    private static final String PNG_CONTENT = "image/png";

    @Value("/Users/furkanekim/IdeaProjects/restaurant-application/target/media/")
    private String uploadDir;

    public List<MediaDTO> getWholeFiles(){
        List<MediaDTO> mediaDTOList = mediaMapper.toDTOList(mediaRepository.findAll());
        return mediaDTOList;
    }


    public String addMedia(MultipartFile file, String imageName) throws IOException {
        Files.createDirectories(Paths.get(uploadDir));
        String filePath = generateFullFilePath(file);
        Path targetLocation = FileSystems.getDefault().getPath(filePath);
        Files.copy(file.getInputStream(),targetLocation, StandardCopyOption.REPLACE_EXISTING);

        byte[] bytes = file.getBytes();

        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setFileContent(bytes);
        mediaDTO.setName(imageName);
        mediaRepository.save(mediaMapper.toEntity(mediaDTO));
        return "Media uploaded...";
    }

    public Boolean deleteMedia(Long id){
        Optional<Media> optionalMedia = mediaRepository.findById(id);
        if(optionalMedia.isPresent()){
            mediaRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    private String generateFullFilePath(MultipartFile file){
        String extension = JPG_EXTENSION;
        if(BMP_CONTENT.equals(file.getContentType())){
            extension = BMP_EXTENSION;
        }else if(PNG_CONTENT.equals(file.getContentType())){
            extension = PNG_EXTENSION;
        }
        return uploadDir + generateUUID() + extension;
    }

    private String generateUUID(){
        return String.valueOf(java.util.UUID.randomUUID());
    }

}
