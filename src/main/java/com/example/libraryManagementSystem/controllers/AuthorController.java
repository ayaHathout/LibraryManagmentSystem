package com.example.libraryManagementSystem.controllers;

import com.example.libraryManagementSystem.dtos.AuthorDTO;
import com.example.libraryManagementSystem.dtos.AuthorResponseDTO;
import com.example.libraryManagementSystem.services.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createAuthor(@RequestBody AuthorDTO authorDTO) {
        AuthorResponseDTO retAuthor = authorService.createAuthor(authorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(retAuthor);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO updatedAuthor) {
        Optional<AuthorResponseDTO> author = authorService.updateAuthor(id, updatedAuthor);
        if (author.isPresent()) {
            return ResponseEntity.ok(author.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author Not Found With id " + id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
        Optional<AuthorResponseDTO> author = authorService.deleteAuthor(id);
        if (author.isPresent()) {
            return ResponseEntity.ok(author.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author Not Found With id " + id);
    }

    @GetMapping
    public List<AuthorResponseDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getAuthor(@PathVariable Long id) {
        Optional<AuthorResponseDTO> author = authorService.getAuthorById(id);
        if (author.isPresent()) {
            return ResponseEntity.ok(author.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author Not Found With id " + id);
    }
}
