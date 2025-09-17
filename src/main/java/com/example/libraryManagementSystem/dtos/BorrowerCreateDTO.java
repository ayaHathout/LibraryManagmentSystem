package com.example.libraryManagementSystem.dtos;

public record BorrowerCreateDTO(
        String firstName,
        String lastName,
        String email,
        String phone,
        String address,
        Boolean isActive

) {
    public BorrowerCreateDTO {
        if (isActive == null)  isActive = true;
    }
}
