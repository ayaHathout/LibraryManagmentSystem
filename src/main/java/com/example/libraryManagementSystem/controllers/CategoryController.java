package com.example.libraryManagementSystem.controllers;

import com.example.libraryManagementSystem.dtos.CategoryDTO;
import com.example.libraryManagementSystem.dtos.CategoryResponseDTO;
import com.example.libraryManagementSystem.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) {
        try {
            CategoryResponseDTO retCategory = categoryService.createCategory(categoryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(retCategory);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO updatedCategory) {
        try {
            Optional<CategoryResponseDTO> category = categoryService.updateCategory(id, updatedCategory);
            if (category.isPresent()) {
                return ResponseEntity.ok(category.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category Not Found With id " + id);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        try {
            Optional<CategoryResponseDTO> category = categoryService.deleteCategory(id);
            if (category.isPresent()) {
                return ResponseEntity.ok(category.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category Not Found With id " + id);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", ex.getMessage()));
        }
    }

    @GetMapping
    public List<CategoryResponseDTO> getAllCategorys() {
        return categoryService.getAllCategorys();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id) {
        Optional<CategoryResponseDTO> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            return ResponseEntity.ok(category.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category Not Found With id " + id);
    }
}
