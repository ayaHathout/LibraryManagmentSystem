package com.example.libraryManagementSystem.dtos;

public record BorrowerUpdateDTO(
        String firstName,
        String lastName,
        String email,
        String phone,
        String address,
        Boolean isActive
) {
}
