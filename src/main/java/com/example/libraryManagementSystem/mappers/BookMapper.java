package com.example.libraryManagementSystem.mappers;

import com.example.libraryManagementSystem.dtos.BookCreateDTO;
import com.example.libraryManagementSystem.dtos.BookResponseDTO;
import com.example.libraryManagementSystem.dtos.BookUpdateDTO;
import com.example.libraryManagementSystem.entities.Author;
import com.example.libraryManagementSystem.entities.Book;
import com.example.libraryManagementSystem.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookUpdateDTO toDTO(Book book);

    @Mapping(target = "publisher", ignore = true)
    Book toEntity(BookCreateDTO bookDTO);

    @Mapping(target = "publisherId", source = "publisher.id")
    @Mapping(target = "publisherName", source = "publisher.name")
    @Mapping(target = "authorIds", source = "authors", qualifiedByName = "mapAuthorIds")
    @Mapping(target = "authorNames", source = "authors", qualifiedByName = "mapAuthorNames")
    @Mapping(target = "categoryIds", source = "categories", qualifiedByName = "mapCategoryIds")
    @Mapping(target = "categoryNames", source = "categories", qualifiedByName = "mapCategoryNames")
    BookResponseDTO toResponseDTO(Book book);

    @Named("mapAuthorIds")
    default Set<Long> mapAuthorIds(Set<Author> authors) {
        if (authors == null) return Set.of();
        return authors.stream()
                .map(Author::getId)
                .collect(Collectors.toSet());
    }

    @Named("mapAuthorNames")
    default Set<String> mapAuthorNames(Set<Author> authors) {
        if (authors == null) return Set.of();
        return authors.stream()
                .map(author -> author.getFirstName() + " " + author.getLastName())
                .collect(Collectors.toSet());
    }

    @Named("mapCategoryIds")
    default Set<Long> mapCategoryIds(Set<Category> categories) {
        if (categories == null) return Set.of();
        return categories.stream()
                .map(Category::getId)
                .collect(Collectors.toSet());
    }

    @Named("mapCategoryNames")
    default Set<String> mapCategoryNames(Set<Category> categories) {
        if (categories == null) return Set.of();
        return categories.stream()
                .map(Category::getName)
                .collect(Collectors.toSet());
    }
}
