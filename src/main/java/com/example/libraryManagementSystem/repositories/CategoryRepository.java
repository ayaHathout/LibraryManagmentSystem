package com.example.libraryManagementSystem.repositories;

import com.example.libraryManagementSystem.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
