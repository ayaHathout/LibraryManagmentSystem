package com.example.libraryManagementSystem.controllers;

import com.example.libraryManagementSystem.dtos.UserActivityLogResponseDTO;
import com.example.libraryManagementSystem.services.interfaces.UserActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user-activity-logs")
public class UserActivityLogController {
    @Autowired
    private UserActivityLogService userActivityLogService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserActivityLogResponseDTO> getAllUserActivityLogs() {
        return userActivityLogService.getAllUserActivityLogs();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<?> getUserActivityLog(@PathVariable Long id) {
        Optional<UserActivityLogResponseDTO> userActivityLog = userActivityLogService.getUserActivityLogById(id);
        if (userActivityLog.isPresent()) {
            return ResponseEntity.ok(userActivityLog.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("UserActivityLog Not Found With id " + id);
    }
}
