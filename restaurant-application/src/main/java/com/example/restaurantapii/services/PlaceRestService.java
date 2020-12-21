package com.example.restaurantapii.services;

import com.example.restaurantapii.Mapper.TableMapper;
import com.example.restaurantapii.converters.DTOConverter;
import com.example.restaurantapii.converters.EntityConvertor;
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
    PlaceRestRepository placeRestRepository;

    public PlaceRestDTO addPlaceRest(PlaceRestDTO placeRestDTO){
        placeRestRepository.save(TableMapper.INSTANCE.toEntity(placeRestDTO));
        return placeRestDTO;
    }

    public List<PlaceRestDTO> listPlaceRests(){
        List<PlaceRestDTO> placeRestListDTO = new ArrayList<>();
        List<PlaceRest> placeRestList = placeRestRepository.findAll();
        placeRestList.forEach(placeRest -> placeRestListDTO.add(TableMapper.INSTANCE.toDTO(placeRest)));
        return placeRestListDTO;
    }


    public PlaceRestDTO updatePlaceRest(PlaceRestDTO placeRestDTO){
        placeRestRepository.saveAndFlush(TableMapper.INSTANCE.toEntity(placeRestDTO));
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
        PlaceRestDTO placeRestDTO = TableMapper.INSTANCE.toDTO(placeRestRepository.findById(id).get());
        return placeRestDTO;
    }
}
