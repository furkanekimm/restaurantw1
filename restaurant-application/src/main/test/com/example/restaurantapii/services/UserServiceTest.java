package com.example.restaurantapii.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

import com.example.restaurantapii.Mapper.RoleMapper;
import com.example.restaurantapii.Mapper.UserMapper;
import com.example.restaurantapii.builder.RoleDTOBuilder;
import com.example.restaurantapii.builder.UserDTOBuilder;
import com.example.restaurantapii.dto.RoleDTO;
import com.example.restaurantapii.dto.UserDTO;
import com.example.restaurantapii.entity.Role;
import com.example.restaurantapii.entity.User;
import com.example.restaurantapii.exceptions.BusinessRuleException;
import com.example.restaurantapii.repository.RoleRepository;
import com.example.restaurantapii.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
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

    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Spy
    private RoleMapper roleMapper = Mappers.getMapper(RoleMapper.class);


    private UserDTO userDTO = new UserDTO();

    private User user = new User();

    private RoleDTO roleDTO = new RoleDTO();

    private List<UserDTO> userDTOList = new ArrayList<>();

    private List<Long> longList = new ArrayList<>();

    private List<Role> roles = new ArrayList<>();

    private List<RoleDTO> roleDTOList = new ArrayList<>();

    @Before
    public void setUp(){
        roleDTO = new RoleDTOBuilder().id(1L).name("EDITOR").build();
        roles.add(roleMapper.toEntity(roleDTO));
        longList.add(1L);
        userDTO = new UserDTOBuilder().id(1L).username("new").password("new").roles(longList).build();
        userDTOList.add(userDTO);
        user = userMapper.toEntity(userDTO);
        user.setRoles(roles);
        roleDTOList = roleMapper.toDTOList(roles);
        userDTO.setRoles(roleDTOList);
        userDTO.setRolesId(longList);

    }

    @Test
    public void shouldAddUser(){
        when(roleRepository.findAllById(any())).thenReturn(roles);
        when(userRepository.save(any())).thenReturn(user);
        UserDTO res = userService.addUser(userDTO);
        assertNotNull(res);
    }

    @Test
    public void shouldUpdateUser(){
        when(roleRepository.findAllById(any())).thenReturn(roles);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(userRepository.save(any())).thenReturn(user);
        UserDTO res = userService.updateUser(userDTO);
        assertNotNull(res);
    }

    @Test
    public void shouldAllUsers(){
        List<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepository.findAll()).thenReturn(userList);
        List<UserDTO> res = userService.allUsers();
        assertNotNull(res);
        assertEquals(res.get(0).getId(),userDTOList.get(0).getId());
    }

    @Test
    public void shouldDeleteUser(){
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        String res = userService.deleteUser(userDTO.getId());
        assertNotNull(res);
    }

    @Test
    public void shouldGetUserById(){
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        UserDTO res = userService.getUserByID(userDTO.getId());
        assertNotNull(res);
    }


}
