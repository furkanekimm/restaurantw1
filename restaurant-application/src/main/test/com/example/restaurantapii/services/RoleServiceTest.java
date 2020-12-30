package com.example.restaurantapii.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

import com.example.restaurantapii.Mapper.RoleMapper;
import com.example.restaurantapii.dto.RoleDTO;
import com.example.restaurantapii.entity.Role;
import com.example.restaurantapii.exceptions.BusinessRuleException;
import com.example.restaurantapii.exceptions.ContentNotFoundException;
import com.example.restaurantapii.exceptions.SystemException;
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

    @Mock
    private RoleMapper roleMapper;

    private List<Role> roleList = new ArrayList<>();
    private List<RoleDTO> roleDTOList = new ArrayList<>();
    private Role role =new Role();
    private RoleDTO roleDTO = new RoleDTO();

    @Before
    public void setUp(){
        role.setName("abc");
        role.setId(1L);
        roleDTO.setName("bcd");
        roleDTO.setId(2L);
        role.setDeleted(false);
        roleList.add(role);
        roleDTOList.add(roleDTO);
        when(roleMapper.toEntity(any())).thenReturn(role);
        when(roleMapper.toDTO(any())).thenReturn(roleDTO);
        when(roleMapper.toDTOList(any())).thenReturn(roleDTOList);
    }

    @Test
    public void shouldAddTable(){
        when(roleRepository.save(any())).thenReturn(role);
        RoleDTO res = roleService.addRole(roleDTO);
        assertNotNull(res);
    }

    @Test
    public void shouldDeleteCustomer(){
        Long id=1L;
        when(roleRepository.existsById(any())).thenReturn(Boolean.TRUE);
        roleService.deleteRole(id);
        verify(roleRepository).deleteById(id);
    }

    @Test(expected = SystemException.class)
    public void shouldNotDeleteWhenIdNotFound(){
        Long id=1L;
        when(roleRepository.existsById(any())).thenReturn(Boolean.FALSE);
        roleService.deleteRole(id);
    }

    @Test
    public void shouldGetAllTables(){
        when(roleRepository.findAll()).thenReturn(roleList);
        List<RoleDTO> res = roleService.getAllRoles();
        assertNotNull(res);
    }

    @Test
    public void shouldUpdateCustomer(){
        when(roleRepository.findById(any())).thenReturn(Optional.of(role));
        when(roleRepository.save(any())).thenReturn(role);
        RoleDTO res = roleService.updateRole(roleDTO);
        assertNotNull(res);
        assertNotEquals(res.getId(),role.getId());
    }

    @Test(expected = ContentNotFoundException.class)
    public void shouldNotUpdateWhenContentNotFound(){
        when(roleRepository.findById(any())).thenReturn(Optional.empty());
        roleService.updateRole(roleDTO);
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotUpdateWhenContentIdNULL(){
        roleDTO.setId(null);
        roleService.updateRole(roleDTO);
    }

    @Test
    public void shouldGetCustomerByID(){
        when(roleRepository.findById(any())).thenReturn(Optional.of(role));
        RoleDTO res = roleService.getRoleById(roleDTO.getId());
        assertNotNull(res);
    }

}
