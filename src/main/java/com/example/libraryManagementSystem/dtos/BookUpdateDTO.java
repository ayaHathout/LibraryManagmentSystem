package com.example.libraryManagementSystem.dtos;

import com.example.libraryManagementSystem.enums.Language;

import java.util.List;

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
        List<Long> authorIds,
        List<Long> categoryIds
) {}
