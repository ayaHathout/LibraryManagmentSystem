package com.example.libraryManagementSystem.mappers;

import com.example.libraryManagementSystem.dtos.BookCreateDTO;
import com.example.libraryManagementSystem.dtos.BookUpdateDTO;
import com.example.libraryManagementSystem.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookUpdateDTO toDTO(Book book);

    @Mapping(target = "publisher", ignore = true)
    Book toEntity(BookCreateDTO bookDTO);
}
