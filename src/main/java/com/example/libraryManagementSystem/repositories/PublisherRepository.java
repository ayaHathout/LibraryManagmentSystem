package com.example.libraryManagementSystem.repositories;

import com.example.libraryManagementSystem.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
