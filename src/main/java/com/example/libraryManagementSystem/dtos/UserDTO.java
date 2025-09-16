package com.example.libraryManagementSystem.dtos;

import com.example.libraryManagementSystem.enums.Role;

public record UserDTO(
        String userName,
        String email,
        String password,
        String phone,
        String firstName,
        String lastName,
        Boolean isActive,
        Role role
) {}
