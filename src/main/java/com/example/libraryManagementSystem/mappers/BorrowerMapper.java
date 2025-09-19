package com.example.libraryManagementSystem.mappers;

import com.example.libraryManagementSystem.dtos.BorrowerDTO;
import com.example.libraryManagementSystem.dtos.BorrowerResponseDTO;
import com.example.libraryManagementSystem.entities.Borrower;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BorrowerMapper {
    BorrowerDTO toDTO(Borrower borrower);

    Borrower toEntity(BorrowerDTO borrowerDTO);

    BorrowerResponseDTO toResponseDTO(Borrower borrower);
}
