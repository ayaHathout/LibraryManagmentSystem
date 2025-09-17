package com.example.libraryManagementSystem.controllers;

import com.example.libraryManagementSystem.dtos.BorrowRecordDTO;
import com.example.libraryManagementSystem.services.interfaces.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/borrower-records")
public class BorrowerRecordController {
    @Autowired
    private BorrowRecordService borrowRecordService;

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @PostMapping
    public BorrowRecordDTO createBorrowRecord(@RequestBody BorrowRecordDTO borrowRecordDTO) {
        return borrowRecordService.createBorrowRecord(borrowRecordDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBorrowRecord(@PathVariable Long id, @RequestBody BorrowRecordDTO updatedBorrowRecord) {
        Optional<BorrowRecordDTO> borrowRecord = borrowRecordService.updateBorrowRecord(id, updatedBorrowRecord);
        if (borrowRecord.isPresent()) {
            return ResponseEntity.ok(borrowRecord.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("BorrowRecord Not Found With id " + id);
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
