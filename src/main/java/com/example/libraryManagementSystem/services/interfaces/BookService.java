package com.example.libraryManagementSystem.services.interfaces;

import com.example.libraryManagementSystem.dtos.BookCreateDTO;
import com.example.libraryManagementSystem.dtos.BookUpdateDTO;
import com.example.libraryManagementSystem.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> getAllBooks();
    Optional<Book> getBookById(Long id);
    Book createBook(BookCreateDTO bookDTO);
    Optional<Book> updateBook(Long id, BookUpdateDTO bookDTO);
    Optional<Book> deleteBook(Long id);
}
