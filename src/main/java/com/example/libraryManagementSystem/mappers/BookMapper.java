package com.example.libraryManagementSystem.mappers;

import com.example.libraryManagementSystem.dtos.BookCreateDTO;
import com.example.libraryManagementSystem.dtos.BookUpdateDTO;
import com.example.libraryManagementSystem.entities.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookUpdateDTO toDTO(Book book);

    Book toEntity(BookCreateDTO bookDTO);
}
