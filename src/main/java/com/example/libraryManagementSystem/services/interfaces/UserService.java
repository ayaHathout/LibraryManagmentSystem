package com.example.libraryManagementSystem.services.interfaces;

import com.example.libraryManagementSystem.dtos.UserDTO;
import com.example.libraryManagementSystem.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User createUser(UserDTO user);
    Optional<User> updateUser(Long id, UserDTO user);
    Optional<User> deleteUser(Long id);
    Optional<User> getByUserName(String username);
}
