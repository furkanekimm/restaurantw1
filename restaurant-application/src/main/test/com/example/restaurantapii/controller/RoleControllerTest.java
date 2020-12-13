package com.example.restaurantapii.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.example.restaurantapii.builder.RoleDTOBuilder;
import com.example.restaurantapii.dto.RoleDTO;
import com.example.restaurantapii.services.RoleService;
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
public class RoleControllerTest {
    @InjectMocks
    private RoleController roleController;

    @Mock
    private RoleService roleService;

    private RoleDTO roleDTO = new RoleDTO();

    private List<RoleDTO> roleDTOList = new ArrayList<>();


    @Before
    public void setUp(){
        roleDTO = new RoleDTOBuilder().id(1L).name("EDITOR").build();
        roleDTOList.add(roleDTO);
    }

    @Test
    public void shouldAddRole(){
        when(roleService.addRole(any())).thenReturn(roleDTO);
        RoleDTO res = roleController.addRole(roleDTO);
        assertNotNull(res);
        assertEquals(res,roleDTO);
    }

    @Test
    public void shouldDeleteRole(){
        String res = roleController.deleteRole(roleDTO.getId());
        assertNotNull(res);
    }

    @Test
    public void shouldUpdateRole(){
        when(roleService.updateRole(any())).thenReturn(roleDTO);
        RoleDTO res = roleController.updateRole(roleDTO);
        assertNotNull(res);
        assertEquals(res.getId(),roleDTO.getId());
    }

    @Test
    public void shouldGetAllRoles(){
        when(roleService.getAllRoles()).thenReturn(roleDTOList);
        List<RoleDTO> res = roleController.getAllRoles();
        assertNotNull(res);
        assertEquals(res.get(0).getId(),roleDTOList.get(0).getId());
    }

    @Test
    public void shouldGetRoleByID(){
        when(roleService.getRoleById(any())).thenReturn(roleDTO);
        RoleDTO res = roleController.getRoleByID(roleDTO.getId());
        assertNotNull(res);
        assertEquals(res,roleDTO);
    }
}
