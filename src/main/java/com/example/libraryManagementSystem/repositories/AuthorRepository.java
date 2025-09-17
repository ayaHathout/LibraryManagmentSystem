package com.example.libraryManagementSystem.repositories;

import com.example.libraryManagementSystem.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
