package com.example.libraryManagementSystem.controllers;

import com.example.libraryManagementSystem.dtos.BookCreateDTO;
import com.example.libraryManagementSystem.dtos.BookUpdateDTO;
import com.example.libraryManagementSystem.entities.Book;
import com.example.libraryManagementSystem.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Book createBook(@RequestBody BookCreateDTO bookCreateDTO) {
        return bookService.createBook(bookCreateDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody BookUpdateDTO bookUpdateDTO) {
        Optional<Book> book = bookService.updateBook(id, bookUpdateDTO);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book Not Found With id " + id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        Optional<Book> book = bookService.deleteBook(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book Not Found With id " + id);
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getBook(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book Not Found With id " + id);
    }
}
