package com.example.restaurantapii.controller;

import com.example.restaurantapii.dto.MediaDTO;
import com.example.restaurantapii.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/file")
@Validated
public class MediaController {
    @Autowired
    private MediaService mediaService;

    @PostMapping("/add")
    public String addMedia(@Valid @RequestParam MultipartFile multipartFile, @RequestParam String imageName) throws IOException {
        mediaService.addMedia(multipartFile,imageName);
        return "Media uploaded...";
    }

    @GetMapping("/list")
    public List<MediaDTO> getWholeFiles(){
        return mediaService.getWholeFiles();
    }

    @DeleteMapping("/{id}")
    public Boolean deleteMedia(@NotNull(message = "{ID_NULL}") @Min(value = 0,message = "{ID_NOT_BE_LITTLE_ZERO}") @PathVariable Long id){
        return mediaService.deleteMedia(id);
    }

}
