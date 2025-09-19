package com.example.libraryManagementSystem.dtos;

import com.example.libraryManagementSystem.enums.Language;

import java.util.Set;

public record BookResponseDTO(
        Long id,
        String title,
        String isbn,
        String edition,
        Integer publicationYear,
        Language language,
        String summary,
        String coverImage,
        Long totalCopies,
        Long availableCopies,
        Long publisherId,
        String publisherName,
        Set<Long> authorIds,
        Set<String> authorNames,
        Set<Long> categoryIds,
        Set<String> categoryNames
) {
}
