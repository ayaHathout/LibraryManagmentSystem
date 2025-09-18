package com.example.libraryManagementSystem.repositories;

import com.example.libraryManagementSystem.dtos.PublisherResponseDTO;
import com.example.libraryManagementSystem.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    @Query("SELECT DISTINCT p FROM Publisher p LEFT JOIN FETCH p.books") // Fetch join to avoid the n + 1 problem
    List<Publisher> findAllWithBooks();
}
