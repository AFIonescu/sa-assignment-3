package com.library.bookmanagement.controller;

import com.library.bookmanagement.dto.BookResponse;
import com.library.bookmanagement.dto.CreateBookRequest;
import com.library.bookmanagement.facade.LibraryFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for Book Management
 * Uses LibraryFacade to handle requests
 */
@Slf4j
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "Book Management", description = "APIs for managing books in the library")
public class BookController {

    private final LibraryFacade libraryFacade;

    /**
     * Create a new book
     * @param request the book creation request
     * @return the created book response
     */
    @PostMapping
    @Operation(summary = "Add a new book", description = "Creates a new book in the library system")
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody CreateBookRequest request) {
        log.info("REST: Creating new book - {}", request.getTitle());
        BookResponse response = libraryFacade.addNewBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Get all books
     * @return list of all books
     */
    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieves all books from the library")
    public ResponseEntity<List<BookResponse>> getAllBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category) {
        log.info("REST: Getting all books - author: {}, category: {}", author, category);

        List<BookResponse> response;
        if (author != null || category != null) {
            response = libraryFacade.searchBooks(author, category);
        } else {
            response = libraryFacade.getAllBooks();
        }

        return ResponseEntity.ok(response);
    }

    /**
     * Get a book by ID
     * @param id the book ID
     * @return the book response
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID", description = "Retrieves a specific book by its ID")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        log.info("REST: Getting book by ID - {}", id);
        BookResponse response = libraryFacade.getBookById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Get books by category
     * @param category the category name
     * @return list of books in the category
     */
    @GetMapping("/category/{category}")
    @Operation(summary = "Get books by category", description = "Retrieves all books in a specific category")
    public ResponseEntity<List<BookResponse>> getBooksByCategory(@PathVariable String category) {
        log.info("REST: Getting books by category - {}", category);
        List<BookResponse> response = libraryFacade.findBooksByCategory(category);
        return ResponseEntity.ok(response);
    }

    /**
     * Get all featured books
     * @return list of featured books
     */
    @GetMapping("/featured")
    @Operation(summary = "Get featured books", description = "Retrieves all featured books")
    public ResponseEntity<List<BookResponse>> getFeaturedBooks() {
        log.info("REST: Getting featured books");
        List<BookResponse> response = libraryFacade.getFeaturedBooks();
        return ResponseEntity.ok(response);
    }

    /**
     * Get all bestseller books
     * @return list of bestseller books
     */
    @GetMapping("/bestsellers")
    @Operation(summary = "Get bestseller books", description = "Retrieves all bestseller books")
    public ResponseEntity<List<BookResponse>> getBestsellers() {
        log.info("REST: Getting bestseller books");
        List<BookResponse> response = libraryFacade.getBestsellers();
        return ResponseEntity.ok(response);
    }

    /**
     * Delete a book by ID
     * @param id the book ID
     * @return no content response
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book", description = "Deletes a book from the library")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        log.info("REST: Deleting book with ID - {}", id);
        libraryFacade.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
