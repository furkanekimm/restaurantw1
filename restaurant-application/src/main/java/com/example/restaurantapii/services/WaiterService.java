package com.example.restaurantapii.services;

import com.example.restaurantapii.converters.DTOConverter;
import com.example.restaurantapii.converters.EntityConvertor;
import com.example.restaurantapii.dto.WaiterDTO;
import com.example.restaurantapii.entity.Media;
import com.example.restaurantapii.entity.Waiter;
import com.example.restaurantapii.repository.MediaRepository;
import com.example.restaurantapii.repository.WaiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WaiterService {

    @Autowired
    private WaiterRepository waiterRepository;

    @Autowired
    private MediaRepository mediaRepository;

    public Boolean addWaiter(WaiterDTO waiterDTO){
        Waiter waiter = DTOConverter.convertDTOWaiter(waiterDTO);
        Media media = mediaRepository.findById(waiterDTO.getMediadto().getId()).get();
        waiter.setMedia(media);
        waiterRepository.save(waiter);
        return true;
    }

    public WaiterDTO updateWaiter(WaiterDTO waiterDTO){
        Waiter waiter = DTOConverter.convertDTOWaiter(waiterDTO);
        Media media = mediaRepository.findById(waiterDTO.getMediadto().getId()).get();
        waiter.setMedia(media);
        waiterRepository.saveAndFlush(DTOConverter.convertDTOWaiter(waiterDTO));
        return waiterDTO;
    }

    public WaiterDTO getWaiterByID(Long id){
        return EntityConvertor.convertToWaiter(waiterRepository.findById(id).get());
    }

    public Boolean deleteWaiter(Long id){
        if(waiterRepository.existsById(id)){
            waiterRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<WaiterDTO> listAllWaiters(){
        List<WaiterDTO> waiterDTOList = new ArrayList<>();
        waiterRepository.findAll().forEach(waiter -> waiterDTOList.add(EntityConvertor.convertToWaiter(waiter)));
        return  waiterDTOList;
    }


}
