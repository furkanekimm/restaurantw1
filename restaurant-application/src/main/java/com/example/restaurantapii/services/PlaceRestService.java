package com.example.restaurantapii.services;

import com.example.restaurantapii.Mapper.TableMapper;
import com.example.restaurantapii.dto.PlaceRestDTO;
import com.example.restaurantapii.entity.PlaceRest;
import com.example.restaurantapii.repository.PlaceRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceRestService {
    @Autowired
    private PlaceRestRepository placeRestRepository;

    @Autowired
    private TableMapper tableMapper;



    public PlaceRestDTO addPlaceRest(PlaceRestDTO placeRestDTO){
        placeRestRepository.save(tableMapper.toEntity(placeRestDTO));
        return placeRestDTO;
    }

    public List<PlaceRestDTO> listPlaceRests(){
        List<PlaceRestDTO> placeRestDTOS = tableMapper.toDTOList(placeRestRepository.findAll());
        return placeRestDTOS;
    }


    public PlaceRestDTO updatePlaceRest(PlaceRestDTO placeRestDTO){
        placeRestRepository.saveAndFlush(tableMapper.toEntity(placeRestDTO));
        return placeRestDTO;
    }

    public Boolean deletePlaceRest(Long id){
        if(!placeRestRepository.existsById(id)){
            return false;
        }
        placeRestRepository.deleteById(id);
        return true;
    }

    public PlaceRestDTO getTableById(Long id){
        PlaceRestDTO placeRestDTO = tableMapper.toDTO(placeRestRepository.findById(id).get());
        return placeRestDTO;
    }
}
