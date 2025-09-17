package com.example.libraryManagementSystem.mappers;

import com.example.libraryManagementSystem.dtos.BorrowerCreateDTO;
import com.example.libraryManagementSystem.dtos.BorrowerUpdateDTO;
import com.example.libraryManagementSystem.entities.Borrower;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BorrowerMapper {
    BorrowerUpdateDTO toDTO(Borrower borrower);

    Borrower toEntity(BorrowerCreateDTO borrowerCreateDTO);
}
