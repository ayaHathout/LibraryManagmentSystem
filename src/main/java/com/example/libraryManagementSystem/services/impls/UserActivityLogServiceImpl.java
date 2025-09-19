package com.example.libraryManagementSystem.services.impls;

import com.example.libraryManagementSystem.dtos.UserActivityLogDTO;
import com.example.libraryManagementSystem.dtos.UserActivityLogResponseDTO;
import com.example.libraryManagementSystem.dtos.UserResponseDTO;
import com.example.libraryManagementSystem.entities.User;
import com.example.libraryManagementSystem.entities.UserActivityLog;
import com.example.libraryManagementSystem.enums.Action;
import com.example.libraryManagementSystem.enums.EntityType;
import com.example.libraryManagementSystem.mappers.UserActivityLogMapper;
import com.example.libraryManagementSystem.mappers.UserMapper;
import com.example.libraryManagementSystem.repositories.UserActivityLogRepository;
import com.example.libraryManagementSystem.services.interfaces.UserActivityLogService;
import com.example.libraryManagementSystem.services.interfaces.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
        return userActivityLogRepository.findAll(Sort.by(Sort.Direction.DESC, "timestamp"))
                .stream()
                .map(userActivityLogMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<UserActivityLogResponseDTO>  getLogsByUserId(Long userId) {
        return userActivityLogRepository.findByUserIdOrderByTimestampDesc(userId)
                .stream()
                .map(userActivityLogMapper::toResponseDTO).toList();
    }

    @Override
    public List<UserActivityLogResponseDTO> getLogsByAction(Action action) {
        return userActivityLogRepository.findByActionOrderByTimestampDesc(action)
                .stream()
                .map(userActivityLogMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<UserActivityLogResponseDTO> getLogsByEntityType(EntityType entityType) {
        return userActivityLogRepository.findByEntityOrderByTimestampDesc(entityType)
                .stream()
                .map(userActivityLogMapper::toResponseDTO)
                .toList();
    }

    @Override
    public List<UserActivityLogResponseDTO> searchLogs(Long userId, Action action, EntityType entityType) {
        Specification<UserActivityLog> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (userId != null) {
                predicates.add(cb.equal(root.get("user").get("id"), userId));
            }

            if (action != null) {
                predicates.add(cb.equal(root.get("action"), action));
            }

            if (entityType != null) {
                predicates.add(cb.equal(root.get("entity"), entityType));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return userActivityLogRepository.findAll(spec, Sort.by(Sort.Direction.DESC, "timestamp"))
                .stream()
                .map(userActivityLogMapper::toResponseDTO)
                .toList();
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
