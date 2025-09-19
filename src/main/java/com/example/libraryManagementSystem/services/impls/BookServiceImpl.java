package com.example.libraryManagementSystem.services.impls;

import com.example.libraryManagementSystem.dtos.BookCreateDTO;
import com.example.libraryManagementSystem.dtos.BookUpdateDTO;
import com.example.libraryManagementSystem.entities.*;
import com.example.libraryManagementSystem.mappers.BookMapper;
import com.example.libraryManagementSystem.repositories.AuthorRepository;
import com.example.libraryManagementSystem.repositories.BookRepository;
import com.example.libraryManagementSystem.repositories.CategoryRepository;
import com.example.libraryManagementSystem.repositories.PublisherRepository;
import com.example.libraryManagementSystem.services.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book createBook(BookCreateDTO bookCreateDTO) {
        Book book = bookMapper.toEntity(bookCreateDTO);

        // To make availableCopies = totalCopies
        book.setAvailableCopies(book.getTotalCopies());

        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> updateBook(Long id, BookUpdateDTO updatedBookDTO) {
        return bookRepository.findById(id)
                .map(book -> {
                    if (updatedBookDTO.title() != null) {
                        book.setTitle(updatedBookDTO.title());
                    }
                    if (updatedBookDTO.isbn() != null) {
                        book.setIsbn(updatedBookDTO.isbn());
                    }
                    if (updatedBookDTO.language() != null) {
                        book.setLanguage(updatedBookDTO.language());
                    }
                    if (updatedBookDTO.edition() != null) {
                        book.setEdition(updatedBookDTO.edition());
                    }
                    if (updatedBookDTO.publicationYear() != null) {
                        book.setPublicationYear(updatedBookDTO.publicationYear());
                    }
                    if (updatedBookDTO.summary() != null) {
                        book.setSummary(updatedBookDTO.summary());
                    }
                    if (updatedBookDTO.coverImage() != null) {
                        book.setCoverImage(updatedBookDTO.coverImage());
                    }

                    // Handle the total copies
                    if (updatedBookDTO.totalCopies() != null) {
                        Long currentTotal = book.getTotalCopies();
                        Long newTotal = updatedBookDTO.totalCopies();
                        Long currentAvailable = book.getAvailableCopies();

                        book.setTotalCopies(newTotal);

                        if (newTotal > currentTotal) {
                            book.setAvailableCopies(currentAvailable + (newTotal - currentTotal));
                        }
                        else if (newTotal < currentTotal) {
                            Long borrowedCount = currentTotal - currentAvailable;
                            Long newAvailable = Math.max(0, newTotal - borrowedCount);
                            book.setAvailableCopies(newAvailable);
                        }
                    }

                    if (updatedBookDTO.availableCopies() != null) {
                        if (updatedBookDTO.availableCopies() > book.getTotalCopies()) {
                            throw new RuntimeException("Available copies can't be greater than the total copies");
                        }
                        book.setAvailableCopies(updatedBookDTO.availableCopies());
                    }

                    if (updatedBookDTO.publisherId() != null) {
                        Optional<Publisher> publisherOpt = publisherRepository.findById(updatedBookDTO.publisherId());
                        publisherOpt.ifPresent(book::setPublisher);
                    }

                    if (updatedBookDTO.authorIds() != null && !updatedBookDTO.authorIds().isEmpty()) {
                        List<Author> authors = authorRepository.findAllById(updatedBookDTO.authorIds());
                        book.setAuthors(authors);
                    }

                    if (updatedBookDTO.categoryIds() != null && !updatedBookDTO.categoryIds().isEmpty()) {
                        List<Category> categories = categoryRepository.findAllById(updatedBookDTO.categoryIds());
                        book.setCategories(categories);
                    }

                    return bookRepository.save(book);
                });
    }

    @Override
    public Optional<Book> deleteBook(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            bookRepository.deleteById(id);
        }
        return book;
    }
}
