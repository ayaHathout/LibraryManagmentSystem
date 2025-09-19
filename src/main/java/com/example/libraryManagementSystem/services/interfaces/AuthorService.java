package com.example.libraryManagementSystem.services.interfaces;

import com.example.libraryManagementSystem.dtos.AuthorDTO;
import com.example.libraryManagementSystem.dtos.AuthorResponseDTO;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<AuthorResponseDTO> getAllAuthors();
    Optional<AuthorResponseDTO> getAuthorById(Long id);
    AuthorResponseDTO createAuthor(AuthorDTO authorDTO);
    Optional<AuthorResponseDTO> updateAuthor(Long id, AuthorDTO authorDTO);
    Optional<AuthorResponseDTO> deleteAuthor(Long id);
}
