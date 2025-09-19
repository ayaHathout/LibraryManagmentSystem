package com.example.libraryManagementSystem.repositories;

import com.example.libraryManagementSystem.entities.UserActivityLog;
import com.example.libraryManagementSystem.enums.Action;
import com.example.libraryManagementSystem.enums.EntityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserActivityLogRepository extends JpaRepository<UserActivityLog, Long>, JpaSpecificationExecutor<UserActivityLog> {
    List<UserActivityLog> findByUserIdOrderByTimestampDesc(Long userId);
    List<UserActivityLog> findByActionOrderByTimestampDesc(Action action);
    List<UserActivityLog> findByEntityOrderByTimestampDesc(EntityType entityType);
}
