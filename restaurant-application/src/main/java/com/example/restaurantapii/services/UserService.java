package com.example.restaurantapii.services;

import com.example.restaurantapii.converters.DTOConverter;
import com.example.restaurantapii.converters.EntityConvertor;
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

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public UserDTO addUser(UserDTO userDTO){
        User user = DTOConverter.convertToUserDTO(userDTO);
        List<Role> roles = roleRepository.findAllById(userDTO.getRolesId());
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setRoles(roles);
        userRepository.save(user);
        return userDTO;
    }

    public UserDTO updateUser(UserDTO userDTO){
        User user = DTOConverter.convertToUserDTO(userDTO);
        List<Role> roles = roleRepository.findAllById(userDTO.getRolesId());
        user.setRoles(roles);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        userRepository.saveAndFlush(user);
        return userDTO;
    }

    public List<UserDTO> allUsers(){
        List<UserDTO> userDTOList = new ArrayList<>();
        userRepository.findAll().forEach(user -> userDTOList.add(EntityConvertor.convertToUser(user)));
        return userDTOList;
    }

    public String deleteUser(Long id){
        //userRepository.deleteUserRole(id);
        Optional<User> optionalUser = userRepository.findById(id);
        optionalUser.get().setRoles(null);
        userRepository.deleteById(id);
        return "Deleted users.";
    }

    public List<RoleDTO> getAllRoles(){
        List<RoleDTO> roleDTOList = new ArrayList<>();
        roleRepository.findAll().forEach(role -> roleDTOList.add(EntityConvertor.convertToRole(role)));
        return roleDTOList;
    }


}