package com.example.libraryManagementSystem.services.interfaces;

import com.example.libraryManagementSystem.dtos.UserDTO;
import com.example.libraryManagementSystem.dtos.UserResponseDTO;
import com.example.libraryManagementSystem.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponseDTO> getAllUsers();
    Optional<UserResponseDTO> getUserById(Long id);
    UserResponseDTO createUser(UserDTO userDTO);
    Optional<UserResponseDTO> updateUser(Long id, UserDTO userDTO);
    Optional<UserResponseDTO> deleteUser(Long id);
    Optional<UserResponseDTO> getByUserName(String username);
}
