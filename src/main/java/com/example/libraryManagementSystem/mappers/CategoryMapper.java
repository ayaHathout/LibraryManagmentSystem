package com.example.libraryManagementSystem.mappers;

import com.example.libraryManagementSystem.dtos.CategoryDTO;
import com.example.libraryManagementSystem.dtos.CategoryResponseDTO;
import com.example.libraryManagementSystem.entities.Category;
import com.example.libraryManagementSystem.entities.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    // For create
    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryDTO categoryDTO);

    // For get
    @Mapping(target = "parentId", source = "parent.id")
    @Mapping(target = "subCategoryIds", expression = "java(mapSubCategories(category.getSubCategories()))")
    @Mapping(target = "bookIds", expression = "java(mapBooks(category.getBooks()))")
    CategoryResponseDTO toResponseDTO(Category category);

    // Helper method to map Category → CategoryId
    default List<Long> mapSubCategories(List<Category> subCategories) {
        if (subCategories == null) return null;
        return subCategories.stream()
                .map(Category::getId)
                .collect(Collectors.toList());
    }

    // Helper method to map Book → BookId
    default List<Long> mapBooks(List<Book> books) {
        if (books == null) return null;
        return books.stream()
                .map(Book::getId)
                .collect(Collectors.toList());
    }
}
