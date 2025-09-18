package com.example.libraryManagementSystem.services.interfaces;

import com.example.libraryManagementSystem.dtos.UserActivityLogDTO;
import com.example.libraryManagementSystem.dtos.UserActivityLogResponseDTO;

import java.util.List;
import java.util.Optional;

public interface UserActivityLogService {
    List<UserActivityLogResponseDTO> getAllUserActivityLogs();
    Optional<UserActivityLogResponseDTO> getUserActivityLogById(Long id);
    UserActivityLogResponseDTO createUserActivityLog(UserActivityLogDTO userActivityLogDTO);
}
