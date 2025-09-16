package com.example.libraryManagementSystem.services.interfaces;

import com.example.libraryManagementSystem.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
    Optional<User> getByUserName(String username);
}
