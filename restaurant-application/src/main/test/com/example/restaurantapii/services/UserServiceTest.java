package com.example.restaurantapii.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import com.example.restaurantapii.builder.RoleDTOBuilder;
import com.example.restaurantapii.builder.UserDTOBuilder;
import com.example.restaurantapii.converters.DTOConverter;
import com.example.restaurantapii.dto.RoleDTO;
import com.example.restaurantapii.dto.UserDTO;
import com.example.restaurantapii.entity.Role;
import com.example.restaurantapii.entity.User;
import com.example.restaurantapii.repository.RoleRepository;
import com.example.restaurantapii.repository.UserRepository;
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
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    private UserDTO userDTO = new UserDTO();

    private RoleDTO roleDTO = new RoleDTO();

    private List<UserDTO> userDTOList = new ArrayList<>();

    private List<Long> longList = new ArrayList<>();

    private List<Role> roles = new ArrayList<>();

    @Before
    public void setUp(){
        roleDTO = new RoleDTOBuilder().id(1l).name("EDITOR").build();
        roles.add(DTOConverter.convertToRoleDTO(roleDTO));
        longList.add(1L);
        userDTO = new UserDTOBuilder().id(1L).username("new").password("new").roles(longList).build();
        userDTOList.add(userDTO);
    }

    @Test
    public void shouldAddUser(){

        when(roleRepository.findAllById(any())).thenReturn(roles);
        when(userRepository.save(any())).thenReturn(DTOConverter.convertToUserDTO(userDTO));
        UserDTO res = userService.addUser(userDTO);
        assertNotNull(res);
    }

    @Test
    public void shouldUpdateUser(){
        when(roleRepository.findAllById(any())).thenReturn(roles);
        when(userRepository.saveAndFlush(any())).thenReturn(DTOConverter.convertToUserDTO(userDTO));
        UserDTO res = userService.updateUser(userDTO);
        assertNotNull(res);
    }

    @Test
    public void shouldAllUsers(){
        List<User> userList = new ArrayList<>();
        userList.add(DTOConverter.convertToUserDTO(userDTO));
        when(userRepository.findAll()).thenReturn(userList);
        List<UserDTO> res = userService.allUsers();
        assertNotNull(res);
        assertEquals(res.get(0).getId(),userDTOList.get(0).getId());
    }

    @Test
    public void shouldDeleteUser(){
        when(userRepository.findById(any())).thenReturn(Optional.of(DTOConverter.convertToUserDTO(userDTO)));
        String res = userService.deleteUser(userDTO.getId());
        assertNotNull(res);
    }

    @Test
    public void shouldGetAllRoles(){
        when(roleRepository.findAll()).thenReturn(roles);
        List<RoleDTO> res = userService.getAllRoles();
        assertNotNull(res);
    }






}
