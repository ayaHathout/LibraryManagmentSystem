package com.example.libraryManagementSystem.controllers;

import com.example.libraryManagementSystem.dtos.BorrowRecordDTO;
import com.example.libraryManagementSystem.services.interfaces.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/borrower-records")
public class BorrowerRecordController {
    @Autowired
    private BorrowRecordService borrowRecordService;

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @PostMapping
    public ResponseEntity<?> createBorrowRecord(@RequestBody BorrowRecordDTO borrowRecordDTO) {
        try {
            BorrowRecordDTO retBorrowRecord = borrowRecordService.createBorrowRecord(borrowRecordDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(retBorrowRecord);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBorrowRecord(@PathVariable Long id, @RequestBody BorrowRecordDTO updatedBorrowRecord) {
        try {
            Optional<BorrowRecordDTO> borrowRecord = borrowRecordService.updateBorrowRecord(id, updatedBorrowRecord);
            if (borrowRecord.isPresent()) {
                return ResponseEntity.ok(borrowRecord.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BorrowRecord Not Found With id " + id);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBorrowRecord(@PathVariable Long id) {
        Optional<BorrowRecordDTO> borrowRecord = borrowRecordService.deleteBorrowRecord(id);
        if (borrowRecord.isPresent()) {
            return ResponseEntity.ok(borrowRecord.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BorrowRecord Not Found With id " + id);
    }

    @GetMapping
    public List<BorrowRecordDTO> getAllBorrowRecords() {
        return borrowRecordService.getAllBorrowRecords();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBorrowRecord(@PathVariable Long id) {
        Optional<BorrowRecordDTO> borrowRecord = borrowRecordService.getBorrowRecordById(id);
        if (borrowRecord.isPresent()) {
            return ResponseEntity.ok(borrowRecord.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BorrowRecord Not Found With id " + id);
    }
}
