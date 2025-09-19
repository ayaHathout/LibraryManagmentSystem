package com.example.libraryManagementSystem.controllers;

import com.example.libraryManagementSystem.dtos.UserActivityLogResponseDTO;
import com.example.libraryManagementSystem.enums.Action;
import com.example.libraryManagementSystem.enums.EntityType;
import com.example.libraryManagementSystem.services.interfaces.UserActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    @GetMapping(params = "userId")
    public List<UserActivityLogResponseDTO> getLogsByUserId(@RequestParam Long userId) {
        return userActivityLogService.getLogsByUserId(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(params = "action")
    public ResponseEntity<?> getLogsByAction(@RequestParam String action) {
        try {
            Action actionParam = Action.valueOf(action.toUpperCase());
            List<UserActivityLogResponseDTO> ret = userActivityLogService.getLogsByAction(actionParam);
            return ResponseEntity.status(HttpStatus.OK).body(ret);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Invalid action: " + action,
                    "valid values", Arrays.toString(Action.values())
            ));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(params = "user-id")
    public List<UserActivityLogResponseDTO> getLogsByAction(@RequestParam("user-id") Long userId) {
        return userActivityLogService.getLogsByUserId(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(params = "entity-type")
    public ResponseEntity<?> getLogsByEntityType(@RequestParam("entity-type") String entityType) {
        try {
            EntityType entityTypeParam = EntityType.valueOf(entityType.toLowerCase());
            List<UserActivityLogResponseDTO> ret = userActivityLogService.getLogsByEntityType(entityTypeParam);
            return ResponseEntity.status(HttpStatus.OK).body(ret);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Invalid entity type: " + entityType,
                    "valid values", Arrays.toString(EntityType.values())
            ));
        }
    }

    // To search with multiple criteria
    @GetMapping(value = "/search")
    public ResponseEntity<?> searchLogs(@RequestParam(required = false, name = "user-id") Long userId, @RequestParam(required = false, name = "action") String action, @RequestParam(required = false, name = "entity-type") String entityType) {
        try {
            Action actionParam = action != null ? Action.valueOf(action.toUpperCase()) : null;
            EntityType entityTypeParam = entityType != null ? EntityType.valueOf(entityType.toLowerCase()) : null;
            List<UserActivityLogResponseDTO> ret = userActivityLogService.searchLogs(userId, actionParam, entityTypeParam);
            return ResponseEntity.status(HttpStatus.OK).body(ret);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of(
                    "error", "Invalid entity type or action",
                    "valid values for Entity type are", Arrays.toString(EntityType.values()),
                    "valid values for Action are", Arrays.toString(Action.values())
            ));
        }
    }
}
