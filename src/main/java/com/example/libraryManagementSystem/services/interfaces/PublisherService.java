package com.example.libraryManagementSystem.services.interfaces;

import com.example.libraryManagementSystem.dtos.PublisherDTO;
import com.example.libraryManagementSystem.dtos.PublisherResponseDTO;

import java.util.List;
import java.util.Optional;

public interface PublisherService {
    List<PublisherResponseDTO> getAllPublishers();
    Optional<PublisherResponseDTO> getPublisherById(Long id);
    PublisherResponseDTO createPublisher(PublisherDTO publisherDTO);
    Optional<PublisherResponseDTO> updatePublisher(Long id, PublisherDTO publisherDTO);
    Optional<PublisherResponseDTO> deletePublisher(Long id);
}
