package com.example.restaurantapii.services;

import com.example.restaurantapii.Mapper.MediaMapper;
import com.example.restaurantapii.Mapper.WaiterMapper;
import com.example.restaurantapii.dto.WaiterDTO;
import com.example.restaurantapii.entity.Waiter;
import com.example.restaurantapii.exceptions.BusinessRuleException;
import com.example.restaurantapii.exceptions.ContentNotFoundException;
import com.example.restaurantapii.exceptions.Errors;
import com.example.restaurantapii.exceptions.SystemException;
import com.example.restaurantapii.repository.WaiterRepository;
import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WaiterService {

    @Autowired
    private WaiterRepository waiterRepository;

    @Autowired
    private WaiterMapper waiterMapper;

    @Autowired
    private MediaMapper mediaMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean addWaiter(WaiterDTO waiterDTO){
        if(waiterDTO.getWaiterName()==null){
            throw new BusinessRuleException(Errors.RECORD_SHOULD_GET_NAME);
        }
        Waiter waiter = waiterMapper.toEntity(waiterDTO);
        waiterRepository.save(waiter);
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public WaiterDTO updateWaiter(WaiterDTO waiterDTO){
        if(waiterDTO.getId()==null){
            throw new BusinessRuleException(Errors.ID_NULL);
        }

        Optional<Waiter> optionalWaiter = waiterRepository.findById(waiterDTO.getId());
        if(optionalWaiter.isEmpty()){
            throw new ContentNotFoundException(Errors.RECORD_NOT_FOUND);
        }

        Waiter waiter = optionalWaiter.get();
        waiterUpdateControl(waiterDTO, waiter);
        waiterRepository.save(waiter);
        return waiterDTO;
    }

    public WaiterDTO getWaiterByID(Long id){
        waiterIdControl(id);
        Optional<Waiter> optionalWaiter = waiterRepository.findById(id);
        if(optionalWaiter.isEmpty()){
            throw new ContentNotFoundException(Errors.RECORD_NOT_FOUND);
        }

        return waiterMapper.toDTO(optionalWaiter.get());
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Boolean deleteWaiter(Long id){
        waiterIdControl(id);
        if(!waiterRepository.existsById(id)){
            throw new SystemException(Errors.ID_NOT_FOUND);
        }

        waiterRepository.deleteById(id);
        return true;
    }

    public List<WaiterDTO> listAllWaiters(){
        return  waiterMapper.toDTOList(waiterRepository.findAll());
    }

    private void waiterUpdateControl(WaiterDTO waiterDTO, Waiter waiter) {
        if(!waiter.getEmail().equals(waiterDTO.getEmail())){
            waiter.setEmail(waiterDTO.getEmail());
        }
        if(!waiter.getPhoneNumber().equals(waiterDTO.getPhoneNumber())){
            waiter.setPhoneNumber(waiterDTO.getPhoneNumber());
        }
        if(!waiter.getWaiterLastName().equals(waiterDTO.getWaiterLastName())){
            waiter.setWaiterLastName(waiterDTO.getWaiterLastName());
        }
        if(!waiter.getWaiterName().equals(waiterDTO.getWaiterName())){
            waiter.setWaiterName(waiterDTO.getWaiterName());
        }
        if(!waiter.getMedia().getId().equals(waiterDTO.getMedia().getId())){
            waiter.setMedia(mediaMapper.toEntity(waiterDTO.getMedia()));
        }
    }

    private void waiterIdControl(Long id) {
        if(id==null){
            throw new BusinessRuleException(Errors.ID_NULL);
        }
    }
}
