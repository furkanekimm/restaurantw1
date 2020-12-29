package com.example.restaurantapii.controller;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.example.restaurantapii.builder.RoleDTOBuilder;
import com.example.restaurantapii.builder.UserDTOBuilder;
import com.example.restaurantapii.dto.RoleDTO;
import com.example.restaurantapii.dto.UserDTO;
import com.example.restaurantapii.entity.User;
import com.example.restaurantapii.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private List<UserDTO> userDTOList = new ArrayList<>();

    private UserDTO userDTO = new UserDTO();

    private List<Long> longList = new ArrayList<>();

    private RoleDTO roleDTO = new RoleDTO();

    @Before
    public void setUp(){
        roleDTO = new RoleDTOBuilder().id(1l).name("EDITOR").build();
        longList.add(1L);
        userDTO = new UserDTOBuilder().id(1L).username("new").password("new").roles(longList).build();
        userDTOList.add(userDTO);
    }

    @Test
    public void shouldAddUser(){
        when(userService.addUser(any())).thenReturn(userDTO);
        UserDTO res = userController.addUser(userDTO);
        assertNotNull(res);
    }

    @Test
    public void shouldUpdateUser(){
        when(userService.updateUser(userDTO)).thenReturn(userDTO);
        UserDTO res = userController.updateUser(userDTO);
        assertNotNull(res);
    }

    @Test
    public void shouldGetAllUsers(){
        when(userService.allUsers()).thenReturn(userDTOList);
        List<UserDTO> res = userController.getAllUsers();
        assertNotNull(res);
        assertEquals(res.get(0).getId(),userDTOList.get(0).getId());
    }

    @Test
    public void shouldDeleteUser(){
        String res = userController.deleteUser(userDTO.getId());
        assertNotNull(res);
    }

    @Test
    public void shouldGetAllRoles(){
        List<RoleDTO> roleDTOList = new ArrayList<>();
        roleDTOList.add(roleDTO);
        /*when(userService.getAllRoles()).thenReturn(roleDTOList);
        List<RoleDTO> res = userController.getAllRoles();
        assertNotNull(res);*/
    }

    @Test
    public void shouldLogin(){
        String res =userController.login();
        assertNotNull(res);
        assertEquals("Basic authentication is okey.",res);
    }

    @Test
    public void shouldGetUserById(){
        when(userService.getUserByID(any())).thenReturn(userDTO);
        UserDTO res = userController.getUserByID(userDTO.getId());
        assertNotNull(res);
    }


}
