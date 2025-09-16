package com.example.libraryManagementSystem.repositories;

import com.example.libraryManagementSystem.entities.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowerRepository extends JpaRepository<Borrower, Long> {
}
