package com.example.libraryManagementSystem.dtos;

import com.example.libraryManagementSystem.enums.Action;
import com.example.libraryManagementSystem.enums.EntityType;

public record UserActivityLogDTO(
        Action action,
        EntityType entity,
        String details,
        Long userId
) {
}
