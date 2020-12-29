package com.example.restaurantapii.services;

import com.example.restaurantapii.Mapper.UserMapper;
import com.example.restaurantapii.dto.UserDTO;
import com.example.restaurantapii.entity.Role;
import com.example.restaurantapii.entity.User;
import com.example.restaurantapii.exceptions.BusinessRuleException;
import com.example.restaurantapii.exceptions.ContentNotFoundException;
import com.example.restaurantapii.exceptions.Errors;
import com.example.restaurantapii.repository.RoleRepository;
import com.example.restaurantapii.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional(propagation = Propagation.REQUIRED)
    public UserDTO addUser(UserDTO userDTO){
        if(userDTO.getUsername()==null){
            throw new BusinessRuleException(Errors.RECORD_SHOULD_GET_NAME);
        }

        User user = userMapper.toEntity(userDTO);
        List<Role> roles = getRolesAndControl(userDTO);

        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setRoles(roles);
        userRepository.save(user);
        return userDTO;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserDTO updateUser(UserDTO userDTO){
        if(userDTO.getId()==null){
            throw new BusinessRuleException(Errors.ID_NULL);
        }

        Optional<User> optionalUser = getUserById(userDTO.getId());
        User user = optionalUser.get();

        if(!user.getUsername().equals(userDTO.getUsername())){
            user.setUsername(userDTO.getUsername());
        }

        List<Role> roles = getRolesAndControl(userDTO);

        user.setRoles(roles);
        user.setPassword(encoder.encode(userDTO.getPassword()));
        userRepository.save(user);
        return userDTO;
    }

    public List<UserDTO> allUsers(){
        List<UserDTO> userDTOList = userMapper.toDTOList(userRepository.findAll());
        return userDTOList;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public String deleteUser(Long id){
        if(id==null){
            throw new BusinessRuleException(Errors.ID_NULL);
        }

        Optional<User> optionalUser = getUserById(id);
        optionalUser.get().setRoles(null);
        userRepository.deleteById(id);
        return "Deleted users.";
    }

    public UserDTO getUserByID(Long id){
        if(id==null){
            throw new BusinessRuleException(Errors.ID_NULL);
        }

        Optional<User> optionalUser = getUserById(id);
        UserDTO userDTO = userMapper.toDTO(optionalUser.get());
        List<Long> longList = new ArrayList<>();
        userRepository.findById(id).get().getRoles().forEach(role -> longList.add(role.getId()));

        if(longList.isEmpty()){
            throw new ContentNotFoundException(Errors.RECORD_NOT_FOUND);
        }

        userDTO.setRolesId(longList);
        return userDTO;
    }

    private Optional<User> getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new ContentNotFoundException(Errors.RECORD_NOT_FOUND);
        }
        return optionalUser;
    }

    private List<Role> getRolesAndControl(UserDTO userDTO) {
        List<Role> roles = roleRepository.findAllById(userDTO.getRolesId());

        if (roles.isEmpty()) {
            throw new ContentNotFoundException(Errors.RECORD_NOT_FOUND);
        }
        return roles;
    }


}