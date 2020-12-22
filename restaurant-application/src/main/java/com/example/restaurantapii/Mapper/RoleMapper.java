package com.example.restaurantapii.Mapper;

import com.example.restaurantapii.dto.RoleDTO;
import com.example.restaurantapii.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toEntity(RoleDTO roleDTO);

    RoleDTO toDTO(Role role);

    List<Role> toEntityList(List<RoleDTO> roleDTOS);

    List<RoleDTO> toDTOList(List<Role> roleList);

}
