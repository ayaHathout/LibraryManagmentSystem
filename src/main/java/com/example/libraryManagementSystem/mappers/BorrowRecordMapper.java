package com.example.libraryManagementSystem.mappers;

import com.example.libraryManagementSystem.dtos.BorrowRecordDTO;
import com.example.libraryManagementSystem.entities.BorrowRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BorrowRecordMapper {
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "borrower.id", target = "borrowerId")
    BorrowRecordDTO toDTO(BorrowRecord borrowRecord);

    @Mapping(source = "bookId", target = "book.id")
    @Mapping(source = "borrowerId", target = "borrower.id")
    BorrowRecord toEntity(BorrowRecordDTO borrowRecordDTO);
}
