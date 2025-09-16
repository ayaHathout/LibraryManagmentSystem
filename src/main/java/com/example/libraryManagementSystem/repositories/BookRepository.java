package com.example.libraryManagementSystem.repositories;

import com.example.libraryManagementSystem.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
