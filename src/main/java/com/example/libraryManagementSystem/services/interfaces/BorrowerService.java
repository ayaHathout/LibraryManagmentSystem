package com.example.libraryManagementSystem.services.interfaces;

import com.example.libraryManagementSystem.dtos.BorrowerCreateDTO;
import com.example.libraryManagementSystem.dtos.BorrowerUpdateDTO;
import com.example.libraryManagementSystem.entities.Borrower;
import com.example.libraryManagementSystem.entities.Borrower;

import java.util.List;
import java.util.Optional;

public interface BorrowerService {
    List<Borrower> getAllBorrowers();
    Optional<Borrower> getBorrowerById(Long id);
    Borrower createBorrower(BorrowerCreateDTO borrowerCreateDTO);
    Optional<Borrower> updateBorrower(Long id, BorrowerUpdateDTO borrowerUpdateDTO);
    Optional<Borrower> deleteBorrower(Long id);
}
