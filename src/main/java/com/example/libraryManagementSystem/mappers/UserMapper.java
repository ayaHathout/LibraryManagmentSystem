package com.example.libraryManagementSystem.mappers;

import com.example.libraryManagementSystem.dtos.UserDTO;
import com.example.libraryManagementSystem.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")  //componentModel = "spring" ==> must be exist or it will cause an error
public interface UserMapper {
    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}
