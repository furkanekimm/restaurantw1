package com.example.restaurantapii.services;

import com.example.restaurantapii.Mapper.RoleMapper;

import com.example.restaurantapii.dto.RoleDTO;
import com.example.restaurantapii.entity.Role;
import com.example.restaurantapii.exceptions.BusinessRuleException;
import com.example.restaurantapii.exceptions.ContentNotFoundException;
import com.example.restaurantapii.exceptions.Errors;
import com.example.restaurantapii.exceptions.SystemException;
import com.example.restaurantapii.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public RoleDTO addRole(RoleDTO roleDTO){
        roleRepository.save(roleMapper.toEntity(roleDTO));
        return roleDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String deleteRole(Long id){
        if(!roleRepository.existsById(id)){
            throw new SystemException(Errors.ID_NOT_FOUND);
        }

        roleRepository.deleteById(id);
        return "Succesfull";
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public RoleDTO updateRole(RoleDTO roleDTO){
        if(roleDTO.getId()==null){
            throw new BusinessRuleException(Errors.ID_NULL);
        }

        Optional<Role> optionalRole = roleRepository.findById(roleDTO.getId());

        if(optionalRole.isEmpty()){
            throw new ContentNotFoundException(Errors.RECORD_NOT_FOUND);
        }

        Role role = optionalRole.get();

        if(!role.getName().equals(roleDTO.getName())){
            role.setName(roleDTO.getName());
        }

        roleRepository.save(roleMapper.toEntity(roleDTO));
        return roleDTO;
    }

    public List<RoleDTO> getAllRoles(){
        List<RoleDTO> roleDTOList = roleMapper.toDTOList(roleRepository.findAll());
        return  roleDTOList;
    }

    public RoleDTO getRoleById(Long id){
        return roleMapper.toDTO(roleRepository.findById(id).get());
    }

}
