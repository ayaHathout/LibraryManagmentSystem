package com.example.libraryManagementSystem.repositories;

import com.example.libraryManagementSystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
