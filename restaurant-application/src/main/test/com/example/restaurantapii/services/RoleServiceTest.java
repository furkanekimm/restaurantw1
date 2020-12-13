package com.example.restaurantapii.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.example.restaurantapii.builder.RoleDTOBuilder;
import com.example.restaurantapii.converters.DTOConverter;
import com.example.restaurantapii.dto.RoleDTO;
import com.example.restaurantapii.entity.Role;
import com.example.restaurantapii.repository.RoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {
    @InjectMocks
    private RoleService roleService;

    @Mock
    private RoleRepository roleRepository;

    RoleDTO roleDTO = new RoleDTO();

    @Before
    public void setUp(){
        roleDTO = new RoleDTOBuilder().id(1L).name("EDITOR").build();
    }

    @Test
    public void shouldAddRole(){
        when(roleRepository.save(any())).thenReturn(DTOConverter.convertToRoleDTO(roleDTO));
        RoleDTO res = roleService.addRole(roleDTO);
        assertNotNull(res);
        assertEquals(res,roleDTO);
    }

    @Test
    public void shouldDeleteRole(){
        String res = roleService.deleteRole(roleDTO.getId());
        assertNotNull(res);
    }

    @Test
    public void shouldUpdateRole(){
        when(roleRepository.saveAndFlush(any())).thenReturn(DTOConverter.convertToRoleDTO(roleDTO));
        RoleDTO res = roleService.updateRole(roleDTO);
        assertNotNull(res);
        assertEquals(res,roleDTO);
    }

    @Test
    public void shouldGetAllRoles(){
        List<Role> roleList = new ArrayList<>();
        roleList.add(DTOConverter.convertToRoleDTO(roleDTO));
        when(roleRepository.findAll()).thenReturn(roleList);
        List<RoleDTO> res = roleService.getAllRoles();
        assertNotNull(res);
        assertEquals(res.get(0).getId(),roleList.get(0).getId());
    }

    @Test
    public void shouldGetRoleByID(){
        when(roleRepository.findById(any())).thenReturn(Optional.of(DTOConverter.convertToRoleDTO(roleDTO)));
        RoleDTO res = roleService.getRoleById(roleDTO.getId());
        assertNotNull(res);
        assertEquals(res.getId(),roleDTO.getId());
    }

}
