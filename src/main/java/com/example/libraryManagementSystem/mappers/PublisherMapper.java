package com.example.libraryManagementSystem.mappers;

import com.example.libraryManagementSystem.dtos.PublisherDTO;
import com.example.libraryManagementSystem.dtos.PublisherResponseDTO;
import com.example.libraryManagementSystem.entities.Book;
import com.example.libraryManagementSystem.entities.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    // For create and update
    PublisherDTO toDTO(Publisher publisher);

    Publisher toEntity(PublisherDTO publisherDTO);

    // For get
    @Mapping(target = "bookIds", source = "books")
    PublisherResponseDTO toResponseDTO(Publisher publisher);

    // Helper method to map books → bookIds
    default List<Long> mapBooksToIds(List<Book> books) {
        if (books == null)  return null;
        return books.stream()
                .map(Book::getId)
                .toList();
    }
}
