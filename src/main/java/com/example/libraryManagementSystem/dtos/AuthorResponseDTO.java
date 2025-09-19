package com.example.libraryManagementSystem.dtos;

import java.util.List;

public record AuthorResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String bio,
        List<String> bookTitles
) {
}
