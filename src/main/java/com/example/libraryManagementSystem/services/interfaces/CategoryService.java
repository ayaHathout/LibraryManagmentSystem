package com.example.libraryManagementSystem.services.interfaces;

import com.example.libraryManagementSystem.dtos.CategoryDTO;
import com.example.libraryManagementSystem.dtos.CategoryResponseDTO;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<CategoryResponseDTO> getAllCategorys();
    Optional<CategoryResponseDTO> getCategoryById(Long id);
    CategoryResponseDTO createCategory(CategoryDTO categoryDTO);
    Optional<CategoryResponseDTO> updateCategory(Long id, CategoryDTO categoryDTO);
    Optional<CategoryResponseDTO> deleteCategory(Long id);
}
