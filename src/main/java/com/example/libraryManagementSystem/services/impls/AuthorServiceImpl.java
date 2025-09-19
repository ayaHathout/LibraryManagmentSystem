package com.example.libraryManagementSystem.services.impls;

import com.example.libraryManagementSystem.dtos.AuthorDTO;
import com.example.libraryManagementSystem.dtos.AuthorResponseDTO;
import com.example.libraryManagementSystem.mappers.AuthorMapper;
import com.example.libraryManagementSystem.repositories.AuthorRepository;
import com.example.libraryManagementSystem.services.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public List<AuthorResponseDTO> getAllAuthors() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toResponseDTO)
                .toList();
    }

    @Override
    public Optional<AuthorResponseDTO> getAuthorById(Long id) {
        return authorRepository.findById(id)
                .map(authorMapper::toResponseDTO);
    }

    @Override
    public AuthorResponseDTO createAuthor(AuthorDTO authorDTO) {
        return authorMapper.toResponseDTO(authorRepository.save(authorMapper.toEntity(authorDTO)));
    }

    @Override
    public Optional<AuthorResponseDTO> updateAuthor(Long id, AuthorDTO updateAuthorDTO) {
        return authorRepository.findById(id)
                .map(author -> {
                    if (updateAuthorDTO.firstName() != null) {
                        author.setFirstName(updateAuthorDTO.firstName());
                    }
                    if (updateAuthorDTO.lastName() != null) {
                        author.setLastName(updateAuthorDTO.lastName());
                    }
                    if (updateAuthorDTO.bio() != null) {
                        author.setBio(updateAuthorDTO.bio());
                    }
                    return authorRepository.save(author);
                })
                .map(authorMapper::toResponseDTO);
    }

    @Override
    public Optional<AuthorResponseDTO> deleteAuthor(Long id) {
        return authorRepository.findById(id)
                .map(author -> {
                    AuthorResponseDTO dto = authorMapper.toResponseDTO(author);
                    authorRepository.delete(author);
                    return dto;
                });
    }
}
