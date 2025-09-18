package com.example.libraryManagementSystem.repositories;

import com.example.libraryManagementSystem.entities.UserActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActivityLogRepository extends JpaRepository<UserActivityLog, Long> {
}
