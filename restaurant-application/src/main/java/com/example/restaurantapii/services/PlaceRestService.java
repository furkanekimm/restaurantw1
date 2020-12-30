package com.example.restaurantapii.services;

import com.example.restaurantapii.Mapper.MediaMapper;
import com.example.restaurantapii.Mapper.TableMapper;
import com.example.restaurantapii.dto.PlaceRestDTO;
import com.example.restaurantapii.entity.PlaceRest;
import com.example.restaurantapii.exceptions.BusinessRuleException;
import com.example.restaurantapii.exceptions.ContentNotFoundException;
import com.example.restaurantapii.exceptions.Errors;
import com.example.restaurantapii.exceptions.SystemException;
import com.example.restaurantapii.repository.PlaceRestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceRestService {
    @Autowired
    private PlaceRestRepository placeRestRepository;

    @Autowired
    private TableMapper tableMapper;

    @Autowired
    private MediaMapper mediaMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public PlaceRestDTO addPlaceRest(PlaceRestDTO placeRestDTO){
        if(placeRestDTO.getName()==null){
            throw new BusinessRuleException(Errors.RECORD_SHOULD_GET_NAME);
        }

        placeRestRepository.save(tableMapper.toEntity(placeRestDTO));
        return placeRestDTO;
    }

    public List<PlaceRestDTO> listPlaceRests(){
        List<PlaceRestDTO> placeRestDTOS = tableMapper.toDTOList(placeRestRepository.findAll());
        return placeRestDTOS;
    }
    @Transactional(propagation = Propagation.REQUIRED)
    public PlaceRestDTO updatePlaceRest(PlaceRestDTO placeRestDTO){
        if(placeRestDTO==null || placeRestDTO.getId()==null){
            throw new BusinessRuleException(Errors.ID_NULL);
        }

        Optional<PlaceRest> optionalPlaceRest = placeRestRepository.findById(placeRestDTO.getId());

        if(optionalPlaceRest.isEmpty()){
            throw new ContentNotFoundException(Errors.RECORD_NOT_FOUND);
        }

        PlaceRest placeRest = optionalPlaceRest.get();

        if(!placeRest.getName().equals(placeRestDTO.getName())){
            placeRest.setName(placeRestDTO.getName());
        }
        if(!placeRest.getTablePiece().equals(placeRestDTO.getTablePiece())){
            placeRest.setTablePiece(placeRestDTO.getTablePiece());
        }
        if(!placeRest.getMedia().getId().equals(placeRestDTO.getMedia().getId())){
            placeRest.setMedia(mediaMapper.toEntity(placeRestDTO.getMedia()));
        }

        placeRestRepository.save(placeRest);
        return placeRestDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean deletePlaceRest(Long id){
        if(!placeRestRepository.existsById(id)){
            throw new ContentNotFoundException(Errors.RECORD_NOT_FOUND);
        }

        placeRestRepository.deleteById(id);
        return true;
    }

    public PlaceRestDTO getTableById(Long id){
        Optional<PlaceRest> optionalPlaceRest = placeRestRepository.findById(id);
        if(optionalPlaceRest.isEmpty()){
            throw new SystemException(Errors.RECORD_NOT_FOUND);
        }

        PlaceRestDTO placeRestDTO = tableMapper.toDTO(optionalPlaceRest.get());
        return placeRestDTO;
    }


}
