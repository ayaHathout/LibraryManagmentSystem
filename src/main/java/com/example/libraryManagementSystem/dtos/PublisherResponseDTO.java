package com.example.libraryManagementSystem.dtos;

import java.util.List;

public record PublisherResponseDTO(
        Long id,
        String name,
        String phone,
        String email,
        String address,
        List<Long> bookIds
) {
}
