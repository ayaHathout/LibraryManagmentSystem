package com.example.libraryManagementSystem.controllers;

import com.example.libraryManagementSystem.dtos.BorrowerCreateDTO;
import com.example.libraryManagementSystem.dtos.BorrowerUpdateDTO;
import com.example.libraryManagementSystem.entities.Borrower;
import com.example.libraryManagementSystem.services.interfaces.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/borrowers")
public class BorrowerController {
    @Autowired
    private BorrowerService borrowerService;

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @PostMapping
    public Borrower createBorrower(@RequestBody BorrowerCreateDTO borrowerCreateDTO) {
        return borrowerService.createBorrower(borrowerCreateDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBorrower(@PathVariable Long id, @RequestBody BorrowerUpdateDTO borrowerUpdateDTO) {
        Optional<Borrower> borrower = borrowerService.updateBorrower(id, borrowerUpdateDTO);
        if (borrower.isPresent()) {
            return ResponseEntity.ok(borrower.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Borrower Not Found With id " + id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBorrower(@PathVariable Long id) {
        Optional<Borrower> borrower = borrowerService.deleteBorrower(id);
        if (borrower.isPresent()) {
            return ResponseEntity.ok(borrower.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Borrower Not Found With id " + id);
    }

    @GetMapping
    public List<Borrower> getAllBorrowers() {
        return borrowerService.getAllBorrowers();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBorrower(@PathVariable Long id) {
        Optional<Borrower> borrower = borrowerService.getBorrowerById(id);
        if (borrower.isPresent()) {
            return ResponseEntity.ok(borrower.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Borrower Not Found With id " + id);
    }
}
