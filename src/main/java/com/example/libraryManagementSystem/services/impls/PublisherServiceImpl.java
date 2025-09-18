package com.example.libraryManagementSystem.services.impls;

import com.example.libraryManagementSystem.dtos.PublisherDTO;
import com.example.libraryManagementSystem.dtos.PublisherResponseDTO;
import com.example.libraryManagementSystem.entities.Book;
import com.example.libraryManagementSystem.entities.Publisher;
import com.example.libraryManagementSystem.entities.Borrower;
import com.example.libraryManagementSystem.enums.Status;
import com.example.libraryManagementSystem.mappers.PublisherMapper;
import com.example.libraryManagementSystem.repositories.BookRepository;
import com.example.libraryManagementSystem.repositories.PublisherRepository;
import com.example.libraryManagementSystem.repositories.BorrowerRepository;
import com.example.libraryManagementSystem.services.interfaces.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private PublisherMapper publisherMapper;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<PublisherResponseDTO> getAllPublishers() {
        return publisherRepository.findAllWithBooks()
                .stream()
                .map(publisherMapper::toResponseDTO)
                .toList();
    }

    @Override
    public Optional<PublisherResponseDTO> getPublisherById(Long id) {
        return publisherRepository.findById(id)
                .map(publisherMapper::toResponseDTO);
    }

    @Override
    public PublisherResponseDTO createPublisher(PublisherDTO publisherDTO) {
        Publisher publisher = publisherMapper.toEntity(publisherDTO);
        return publisherMapper.toResponseDTO(publisherRepository.save(publisher));
    }

    @Override
    public Optional<PublisherResponseDTO> updatePublisher(Long id, PublisherDTO publisherDTO) {
        return publisherRepository.findById(id)
                .map(publisher -> {
                    if (publisherDTO.email() != null) {
                        publisher.setEmail(publisherDTO.email());
                    }
                    if (publisherDTO.address() != null) {
                        publisher.setAddress(publisherDTO.address());
                    }
                    if (publisherDTO.phone() != null) {
                        publisher.setPhone(publisherDTO.phone());
                    }
                    if (publisherDTO.name() != null) {
                        publisher.setName(publisherDTO.name());
                    }
                    return publisherRepository.save(publisher);
                })
                .map(publisherMapper::toResponseDTO);
    }

    @Override
    public Optional<PublisherResponseDTO> deletePublisher(Long id) {
        return publisherRepository.findById(id)
                .map(publisher -> {
                    PublisherResponseDTO deletedPublisher = publisherMapper.toResponseDTO(publisher);
                    publisherRepository.deleteById(id);
                    return deletedPublisher;
                });
    }
}
