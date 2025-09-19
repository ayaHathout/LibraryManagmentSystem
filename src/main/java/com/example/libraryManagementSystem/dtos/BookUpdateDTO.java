package com.example.libraryManagementSystem.dtos;

import com.example.libraryManagementSystem.enums.Language;

import java.util.Set;

public record BookUpdateDTO(
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
        Set<Long> authorIds,
        Set<Long> categoryIds
) {}
