package com.example.libraryManagementSystem.dtos;

import com.example.libraryManagementSystem.enums.Role;

public record UserResponseDTO(
        Long id,
        String userName,
        String email,
        String password,
        String phone,
        String firstName,
        String lastName,
        Role role
) {}
