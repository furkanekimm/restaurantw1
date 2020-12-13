package com.example.restaurantapii.services;

import com.example.restaurantapii.converters.DTOConverter;
import com.example.restaurantapii.converters.EntityConvertor;
import com.example.restaurantapii.dto.PlaceRestDTO;
import com.example.restaurantapii.entity.PlaceRest;
import com.example.restaurantapii.repository.PlaceRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaceRestService {
    @Autowired
    PlaceRestRepository placeRestRepository;

    public PlaceRestDTO addPlaceRest(PlaceRestDTO placeRestDTO){
        placeRestRepository.save(DTOConverter.convertDTOPlaceRest(placeRestDTO));
        return placeRestDTO;
    }

    public List<PlaceRestDTO> listPlaceRests(){
        List<PlaceRestDTO> placeRestListDTO = new ArrayList<>();
        List<PlaceRest> placeRestList = placeRestRepository.findAll();
        placeRestList.forEach(placeRest -> placeRestListDTO.add(EntityConvertor.convertToPlaceRest(placeRest)));
        return placeRestListDTO;
    }

  /*  public Optional<PlaceRest> findById(Long id){
        return placeRestRepository.findById(id);
    }*/

    public PlaceRestDTO updatePlaceRest(PlaceRestDTO placeRestDTO){
        placeRestRepository.saveAndFlush(DTOConverter.convertDTOPlaceRest(placeRestDTO));
        return placeRestDTO;
    }

    public Boolean deletePlaceRest(Long id){
        if(!placeRestRepository.existsById(id)){
            return false;
        }
        placeRestRepository.deleteById(id);
        return true;
    }
}
