package com.example.libraryManagementSystem.dtos;

import com.example.libraryManagementSystem.enums.Language;

import java.util.List;

public record BookCreateDTO(
        String title,
        String isbn,
        String edition,
        Integer publicationYear,
        Language language,
        String summary,
        String coverImage,
        Long totalCopies,
        Long publisherId,
        List<Long> authorIds,
        List<Long> categoryIds
) {
    public BookCreateDTO {
        if (totalCopies == null)  totalCopies = 1L;
        if (language == null)  language = Language.ARABIC;
    }
}
