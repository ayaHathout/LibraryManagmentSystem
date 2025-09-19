package com.example.libraryManagementSystem.services.interfaces;

import com.example.libraryManagementSystem.dtos.BookCreateDTO;
import com.example.libraryManagementSystem.dtos.BookResponseDTO;
import com.example.libraryManagementSystem.dtos.BookUpdateDTO;
import com.example.libraryManagementSystem.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookResponseDTO> getAllBooks();
    Optional<BookResponseDTO> getBookById(Long id);
    BookResponseDTO createBook(BookCreateDTO bookDTO);
    Optional<BookResponseDTO> updateBook(Long id, BookUpdateDTO bookDTO);
    Optional<BookResponseDTO> deleteBook(Long id);
}
