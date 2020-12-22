package com.example.restaurantapii.services;

import com.example.restaurantapii.Mapper.RoleMapper;
import com.example.restaurantapii.Mapper.UserMapper;
import com.example.restaurantapii.dto.RoleDTO;
import com.example.restaurantapii.dto.UserDTO;
import com.example.restaurantapii.entity.Role;
import com.example.restaurantapii.entity.User;
import com.example.restaurantapii.repository.RoleRepository;
import com.example.restaurantapii.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public UserDTO addUser(UserDTO userDTO){
        User user = userMapper.toEntity(userDTO);
        List<Role> roles = roleRepository.findAllById(userDTO.getRolesId());
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setRoles(roles);
        userRepository.save(user);
        return userDTO;
    }

    public UserDTO updateUser(UserDTO userDTO){
        User user = userMapper.toEntity(userDTO);
        List<Role> roles = roleRepository.findAllById(userDTO.getRolesId());
        user.setRoles(roles);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        userRepository.saveAndFlush(user);
        return userDTO;
    }

    public List<UserDTO> allUsers(){
        List<UserDTO> userDTOList = userMapper.toDTOList(userRepository.findAll());
        return userDTOList;
    }

    public String deleteUser(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser.get().setRoles(null);
        userRepository.deleteById(id);
        return "Deleted users.";
    }

    public List<RoleDTO> getAllRoles(){
        List<RoleDTO> roleDTOList = roleMapper.toDTOList(roleRepository.findAll());
        return roleDTOList;
    }

    public UserDTO getUserByID(Long id){
        UserDTO userDTO = userMapper.toDTO(userRepository.findById(id).get());
        List<Long> longList = new ArrayList<>();
        userRepository.findById(id).get().getRoles().forEach(role -> longList.add(role.getId()));
        userDTO.setRolesId(longList);
        return userDTO;
    }


}