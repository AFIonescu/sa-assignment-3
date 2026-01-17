package org.example.structural.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.example.structural.dto.BookDto;
import org.example.structural.entity.Book;
import org.example.structural.service.BookDecorator;
import org.example.structural.service.LibraryFacade;
import org.example.structural.utils.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
public class LibraryController {

    private final LibraryFacade libraryFacade;

    @Autowired
    public LibraryController(LibraryFacade libraryFacade) {
        this.libraryFacade = libraryFacade;
    }

    @Operation(summary = "Retrieve all books", description = "Returns a list of all books in the library as BookDto objects")
    @GetMapping
    public List<BookDto> getAllBooks() {
        return libraryFacade.getAllBooks()
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get a book by ID", description = "Provide an ID to lookup a specific book in the library")
    @GetMapping("/{id}")
    public BookDto getBookById(@Parameter(description = "ID of the book to retrieve") @PathVariable Long id) {
        Book book = libraryFacade.getBookById(id);
        return book != null ? BookMapper.toDTO(book) : new BookDto();
    }

    @Operation(summary = "Add a new book", description = "Adds a new book to the library and returns the saved BookDto object")
    @PostMapping
    public BookDto addBook(@Parameter(description = "BookDto object to be added") @RequestBody BookDto bookDto) {
        Book book = BookMapper.toEntity(bookDto);
        Book savedBook = libraryFacade.addBook(book);
        return BookMapper.toDTO(savedBook);
    }

    @Operation(summary = "Update an existing book", description = "Updates an existing book by ID with new information from the BookDto object")
    @PutMapping("/{id}")
    public BookDto updateBook(
            @Parameter(description = "ID of the book to update") @PathVariable Long id,
            @Parameter(description = "Updated BookDto object") @RequestBody BookDto updatedBookDto) {
        Book updatedBook = BookMapper.toEntity(updatedBookDto);
        Book result = libraryFacade.updateBook(id, updatedBook);
        return result != null ? BookMapper.toDTO(result) : new BookDto();
    }

    @Operation(summary = "Delete a book by ID", description = "Deletes the book with the specified ID from the library")
    @DeleteMapping("/{id}")
    public void deleteBook(@Parameter(description = "ID of the book to delete") @PathVariable Long id) {
        libraryFacade.deleteBook(id);
    }

    @Operation(summary = "Get books by category", description = "Returns all books in a specific category")
    @GetMapping("/category/{category}")
    public List<BookDto> getBooksByCategory(@Parameter(description = "Category to filter books by") @PathVariable String category) {
        return libraryFacade.findBooksByCategory(category)
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get featured books", description = "Returns all books with Featured decorator applied")
    @GetMapping("/featured")
    public List<String> getFeaturedBooks() {
        return libraryFacade.getFeaturedBooks()
                .stream()
                .map(BookDecorator::getDescription)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Get bestseller books", description = "Returns all books with Bestseller decorator applied")
    @GetMapping("/bestsellers")
    public List<String> getBestsellerBooks() {
        return libraryFacade.getBestsellerBooks()
                .stream()
                .map(BookDecorator::getDescription)
                .collect(Collectors.toList());
    }
}
