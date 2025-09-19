package com.example.libraryManagementSystem.services.impls;

import com.example.libraryManagementSystem.dtos.UserActivityLogDTO;
import com.example.libraryManagementSystem.dtos.UserActivityLogResponseDTO;
import com.example.libraryManagementSystem.dtos.UserResponseDTO;
import com.example.libraryManagementSystem.entities.User;
import com.example.libraryManagementSystem.entities.UserActivityLog;
import com.example.libraryManagementSystem.mappers.UserActivityLogMapper;
import com.example.libraryManagementSystem.mappers.UserMapper;
import com.example.libraryManagementSystem.repositories.UserActivityLogRepository;
import com.example.libraryManagementSystem.services.interfaces.UserActivityLogService;
import com.example.libraryManagementSystem.services.interfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserActivityLogServiceImpl implements UserActivityLogService {
    @Autowired
    private UserActivityLogRepository userActivityLogRepository;

    @Autowired
    private UserActivityLogMapper userActivityLogMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserActivityLogResponseDTO> getAllUserActivityLogs() {
        return userActivityLogRepository.findAll()
                .stream()
                .map(userActivityLogMapper::toResponseDTO)
                .toList();
    }

    @Override
    public Optional<UserActivityLogResponseDTO> getUserActivityLogById(Long id) {
        return userActivityLogRepository.findById(id)
                .map(userActivityLogMapper::toResponseDTO);
    }

    @Override
    public UserActivityLogResponseDTO createUserActivityLog(UserActivityLogDTO userActivityLogDTO) {
        UserResponseDTO userResponseDTO = userService.getUserById(userActivityLogDTO.userId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userActivityLogDTO.userId()));

        User user = userMapper.toEntity(userResponseDTO);

        UserActivityLog userActivityLog = userActivityLogMapper.toEntity(userActivityLogDTO);

        userActivityLog.setUser(user);
        userActivityLog.setTimestamp(LocalDateTime.now());

        UserActivityLog savedLog = userActivityLogRepository.save(userActivityLog);
        return userActivityLogMapper.toResponseDTO(savedLog);
    }
}
