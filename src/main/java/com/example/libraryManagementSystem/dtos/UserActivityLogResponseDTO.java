package com.example.libraryManagementSystem.dtos;

import com.example.libraryManagementSystem.enums.Action;
import com.example.libraryManagementSystem.enums.EntityType;

import java.time.LocalDateTime;

public record UserActivityLogResponseDTO(
        Long id,
        Action action,
        EntityType entity,
        String details,
        LocalDateTime timestamp,
        Long userId,
        String username
) {
}
