package com.example.libraryManagementSystem.services.impls;

import com.example.libraryManagementSystem.dtos.BorrowRecordDTO;
import com.example.libraryManagementSystem.entities.Book;
import com.example.libraryManagementSystem.entities.BorrowRecord;
import com.example.libraryManagementSystem.entities.Borrower;
import com.example.libraryManagementSystem.mappers.BorrowRecordMapper;
import com.example.libraryManagementSystem.repositories.BookRepository;
import com.example.libraryManagementSystem.repositories.BorrowRecordRepository;
import com.example.libraryManagementSystem.repositories.BorrowerRepository;
import com.example.libraryManagementSystem.services.interfaces.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {
    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private BorrowRecordMapper borrowRecordMapper;

    @Autowired
    private BorrowerRepository borrowerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<BorrowRecordDTO> getAllBorrowRecords() {
        return borrowRecordRepository.findAll()
                .stream()
                .map(borrowRecordMapper::toDTO)
                .toList();
    }

    @Override
    public Optional<BorrowRecordDTO> getBorrowRecordById(Long id) {
        return borrowRecordRepository.findById(id)
                .map(borrowRecordMapper::toDTO);
    }

    @Override
    public BorrowRecordDTO createBorrowRecord(BorrowRecordDTO borrowRecordDTO) {
        BorrowRecord borrowRecord = borrowRecordMapper.toEntity(borrowRecordDTO);

        // Get the book
        Book book = bookRepository.findById(borrowRecordDTO.bookId())
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + borrowRecordDTO.bookId()));

        if (book.getAvailableCopies() == 0) {
            throw new RuntimeException("No available copies for book: " + book.getTitle());
        }
        book.setAvailableCopies(book.getAvailableCopies() - 1);

        // Get the borrower
        Borrower borrower = borrowerRepository.findById(borrowRecordDTO.borrowerId())
                .orElseThrow(() -> new RuntimeException("Borrower not found with id: " + borrowRecordDTO.borrowerId()));

        borrowRecord.setBook(book);
        borrowRecord.setBorrower(borrower);

        return borrowRecordMapper.toDTO(borrowRecordRepository.save(borrowRecord));
    }

    @Override
    public Optional<BorrowRecordDTO> updateBorrowRecord(Long id, BorrowRecordDTO updateBorrowRecordDTO) {
        return borrowRecordRepository.findById(id)
                .map(borrowRecord -> {
                    if (updateBorrowRecordDTO.borrowDate() != null) {
                        borrowRecord.setBorrowDate(updateBorrowRecordDTO.borrowDate());
                    }
                    if (updateBorrowRecordDTO.dueDate() != null) {
                        borrowRecord.setDueDate(updateBorrowRecordDTO.dueDate());
                    }
                    if (updateBorrowRecordDTO.returnDate() != null) {
                        borrowRecord.setReturnDate(updateBorrowRecordDTO.returnDate());
                    }
                    if (updateBorrowRecordDTO.status() != null) {
                        borrowRecord.setStatus(updateBorrowRecordDTO.status());
                    }
                    if (updateBorrowRecordDTO.fine() != null) {
                        borrowRecord.setFine(updateBorrowRecordDTO.fine());
                    }
                    if (updateBorrowRecordDTO.borrowerId() != null) {
                        borrowerRepository.findById(updateBorrowRecordDTO.borrowerId())
                                .ifPresent(borrowRecord::setBorrower);
                    }
                    if (updateBorrowRecordDTO.bookId() != null) {
                        Optional<Book> book = bookRepository.findById(updateBorrowRecordDTO.bookId());
                        if (book.isPresent()) {
                            if (book.get().getAvailableCopies() == 0) {
                                throw new RuntimeException("No available copies for book: " + book.get().getTitle());
                            }
                            book.get().setAvailableCopies(book.get().getAvailableCopies() - 1);
                            borrowRecord.setBook(book.get());
                        }
                        else {
                            throw new RuntimeException("Book not found with id: " + updateBorrowRecordDTO.bookId());
                        }
                    }
                    return borrowRecordRepository.save(borrowRecord);
                })
                .map(borrowRecordMapper::toDTO);
    }

    @Override
    public Optional<BorrowRecordDTO> deleteBorrowRecord(Long id) {
        Optional<BorrowRecord> borrowRecord = borrowRecordRepository.findById(id);
        if (borrowRecord.isPresent()) {
            borrowRecordRepository.deleteById(id);
        }
        return borrowRecord.map(borrowRecordMapper::toDTO);
    }
}
