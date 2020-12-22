package com.example.restaurantapii.Mapper;

import com.example.restaurantapii.dto.UserDTO;
import com.example.restaurantapii.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO userDTO);

    UserDTO toDTO(User user);

    List<User> toEntityList(List<UserDTO> userDTOS);

    List<UserDTO> toDTOList(List<User> userList);
}
