package com.example.libraryManagementSystem.services.impls;

import com.example.libraryManagementSystem.dtos.BorrowerCreateDTO;
import com.example.libraryManagementSystem.dtos.BorrowerUpdateDTO;
import com.example.libraryManagementSystem.entities.Borrower;
import com.example.libraryManagementSystem.mappers.BorrowerMapper;
import com.example.libraryManagementSystem.repositories.BorrowerRepository;
import com.example.libraryManagementSystem.services.interfaces.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowerServiceImpl implements BorrowerService {
    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private BorrowerMapper borrowerMapper;

    @Override
    public List<Borrower> getAllBorrowers() {
        return borrowerRepository.findAll();
    }

    @Override
    public Optional<Borrower> getBorrowerById(Long id) {
        return borrowerRepository.findById(id);
    }

    @Override
    public Borrower createBorrower(BorrowerCreateDTO borrowerCreateDTO) {
        Borrower borrower = borrowerMapper.toEntity(borrowerCreateDTO);
        return borrowerRepository.save(borrower);
    }

    @Override
    public Optional<Borrower> updateBorrower(Long id, BorrowerUpdateDTO borrowerUpdateDTO) {
        return borrowerRepository.findById(id)
                .map(borrower -> {
                    if (borrowerUpdateDTO.email() != null) {
                        borrower.setEmail(borrowerUpdateDTO.email());
                    }
                    if (borrowerUpdateDTO.firstName() != null) {
                        borrower.setFirstName(borrowerUpdateDTO.firstName());
                    }
                    if (borrowerUpdateDTO.lastName() != null) {
                        borrower.setLastName(borrowerUpdateDTO.lastName());
                    }
                    if (borrowerUpdateDTO.phone() != null) {
                        borrower.setPhone(borrowerUpdateDTO.phone());
                    }
                    if (borrowerUpdateDTO.address() != null) {
                        borrower.setAddress(borrowerUpdateDTO.address());
                    }
                    if (borrowerUpdateDTO.isActive() != null) {
                        borrower.setIsActive(borrowerUpdateDTO.isActive());
                    }
                    return borrowerRepository.save(borrower);
                });
    }

    @Override
    public Optional<Borrower> deleteBorrower(Long id) {
        Optional<Borrower> borrower = borrowerRepository.findById(id);
        if (borrower.isPresent()) {
            borrowerRepository.deleteById(id);
        }
        return borrower;
    }
}