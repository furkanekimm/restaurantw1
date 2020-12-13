package com.example.restaurantapii.services;

import com.example.restaurantapii.converters.DTOConverter;
import com.example.restaurantapii.converters.EntityConvertor;
import com.example.restaurantapii.dto.RoleDTO;
import com.example.restaurantapii.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public RoleDTO addRole(RoleDTO roleDTO){
        roleRepository.save(DTOConverter.convertToRoleDTO(roleDTO));
        return roleDTO;
    }

    public String deleteRole(Long id){
        roleRepository.deleteById(id);
        return "Succesfull";
    }

    public RoleDTO updateRole(RoleDTO roleDTO){
        roleRepository.saveAndFlush(DTOConverter.convertToRoleDTO(roleDTO));
        return roleDTO;
    }

    public List<RoleDTO> getAllRoles(){
        List<RoleDTO> roleDTOList = new ArrayList<>();
        roleRepository.findAll().forEach(role -> roleDTOList.add(EntityConvertor.convertToRole(role)));
        return  roleDTOList;
    }

    public RoleDTO getRoleById(Long id){
        return EntityConvertor.convertToRole(roleRepository.findById(id).get());
    }

}
