package com.example.libraryManagementSystem.entities;

import com.example.libraryManagementSystem.enums.Action;
import com.example.libraryManagementSystem.enums.EntityType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_activity_logs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "user")
public class UserActivityLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Action action;

    @Enumerated(EnumType.STRING)
    private EntityType entity;

    @Column(length = 500)
    private String details;

    private LocalDateTime timestamp = LocalDateTime.now();

    // --------------------------------------------------------
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
