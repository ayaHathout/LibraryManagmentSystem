package com.example.libraryManagementSystem.controllers;

import com.example.libraryManagementSystem.dtos.PublisherDTO;
import com.example.libraryManagementSystem.dtos.PublisherResponseDTO;
import com.example.libraryManagementSystem.services.interfaces.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/publishers")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createPublisher(@RequestBody PublisherDTO publisherDTO) {
        try {
            PublisherResponseDTO retPublisher = publisherService.createPublisher(publisherDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(retPublisher);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updatePublisher(@PathVariable Long id, @RequestBody PublisherDTO updatedPublisher) {
        try {
            Optional<PublisherResponseDTO> publisher = publisherService.updatePublisher(id, updatedPublisher);
            if (publisher.isPresent()) {
                return ResponseEntity.ok(publisher.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher Not Found With id " + id);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePublisher(@PathVariable Long id) {
        Optional<PublisherResponseDTO> publisher = publisherService.deletePublisher(id);
        if (publisher.isPresent()) {
            return ResponseEntity.ok(publisher.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher Not Found With id " + id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'STAFF')")
    @GetMapping
    public List<PublisherResponseDTO> getAllPublishers() {
        return publisherService.getAllPublishers();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'LIBRARIAN', 'STAFF')")
    @GetMapping("{id}")
    public ResponseEntity<?> getPublisher(@PathVariable Long id) {
        Optional<PublisherResponseDTO> publisher = publisherService.getPublisherById(id);
        if (publisher.isPresent()) {
            return ResponseEntity.ok(publisher.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publisher Not Found With id " + id);
    }
}
