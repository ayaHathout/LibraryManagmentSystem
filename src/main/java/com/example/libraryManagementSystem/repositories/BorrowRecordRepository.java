package com.example.libraryManagementSystem.repositories;

import com.example.libraryManagementSystem.entities.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
}
