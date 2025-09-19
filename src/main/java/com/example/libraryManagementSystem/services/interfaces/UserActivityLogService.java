package com.example.libraryManagementSystem.services.interfaces;

import com.example.libraryManagementSystem.dtos.UserActivityLogDTO;
import com.example.libraryManagementSystem.dtos.UserActivityLogResponseDTO;
import com.example.libraryManagementSystem.enums.Action;
import com.example.libraryManagementSystem.enums.EntityType;

import java.util.List;

public interface UserActivityLogService {
    List<UserActivityLogResponseDTO> getAllUserActivityLogs();
    List<UserActivityLogResponseDTO>  getLogsByUserId(Long userId);
    List<UserActivityLogResponseDTO>  getLogsByAction(Action action);
    List<UserActivityLogResponseDTO> getLogsByEntityType(EntityType entityType);
    List<UserActivityLogResponseDTO> searchLogs(Long userId, Action action, EntityType entityType);
    UserActivityLogResponseDTO createUserActivityLog(UserActivityLogDTO userActivityLogDTO);

}
