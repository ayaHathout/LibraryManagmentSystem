package com.example.libraryManagementSystem.mappers;

import com.example.libraryManagementSystem.dtos.UserActivityLogDTO;
import com.example.libraryManagementSystem.dtos.UserActivityLogResponseDTO;
import com.example.libraryManagementSystem.entities.UserActivityLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserActivityLogMapper {
    // For create
    @Mapping(target = "userId", source = "user.id")
    UserActivityLogDTO toDTO(UserActivityLog userActivityLog);

    @Mapping(target = "user", ignore = true)
    UserActivityLog toEntity(UserActivityLogDTO userActivityLogDTO);

    // For get
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.userName")
    UserActivityLogResponseDTO toResponseDTO(UserActivityLog userActivityLog);
}
