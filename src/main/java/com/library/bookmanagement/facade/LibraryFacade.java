package com.library.bookmanagement.facade;

import com.library.bookmanagement.decorator.*;
import com.library.bookmanagement.dto.BookResponse;
import com.library.bookmanagement.dto.CreateBookRequest;
import com.library.bookmanagement.model.Book;
import com.library.bookmanagement.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade Pattern implementation for Library Management
 * Simplifies complex subsystem interactions and coordinates BookService with Decorator Pattern
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class LibraryFacade {

    private final BookService bookService;

    /**
     * Add a new book to the library
     * @param request the book creation request
     * @return the created book response
     */
    public BookResponse addNewBook(CreateBookRequest request) {
        log.info("Facade: Adding new book - {}", request.getTitle());

        Book book = Book.builder()
                .title(request.getTitle())
                .author(request.getAuthor())
                .category(request.getCategory())
                .isbn(request.getIsbn())
                .price(request.getPrice())
                .featured(request.getFeatured())
                .bestseller(request.getBestseller())
                .build();

        Book savedBook = bookService.addBook(book);
        return convertToResponse(savedBook);
    }

    /**
     * Find books by category
     * @param category the category to search for
     * @return list of book responses
     */
    public List<BookResponse> findBooksByCategory(String category) {
        log.info("Facade: Finding books by category - {}", category);
        List<Book> books = bookService.findByCategory(category);
        return books.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get all featured books
     * @return list of featured book responses
     */
    public List<BookResponse> getFeaturedBooks() {
        log.info("Facade: Getting all featured books");
        List<Book> books = bookService.findFeaturedBooks();
        return books.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get all bestseller books
     * @return list of bestseller book responses
     */
    public List<BookResponse> getBestsellers() {
        log.info("Facade: Getting all bestseller books");
        List<Book> books = bookService.findBestsellers();
        return books.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Search books by various criteria
     * @param author optional author name
     * @param category optional category
     * @return list of book responses
     */
    public List<BookResponse> searchBooks(String author, String category) {
        log.info("Facade: Searching books - author: {}, category: {}", author, category);

        List<Book> books;
        if (author != null && !author.isEmpty()) {
            books = bookService.findByAuthor(author);
        } else if (category != null && !category.isEmpty()) {
            books = bookService.findByCategory(category);
        } else {
            books = bookService.getAllBooks();
        }

        return books.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get all books in the library
     * @return list of all book responses
     */
    public List<BookResponse> getAllBooks() {
        log.info("Facade: Getting all books");
        List<Book> books = bookService.getAllBooks();
        return books.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get a book by ID
     * @param id the book ID
     * @return the book response
     */
    public BookResponse getBookById(Long id) {
        log.info("Facade: Getting book by ID - {}", id);
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
        return convertToResponse(book);
    }

    /**
     * Delete a book by ID
     * @param id the book ID
     */
    public void deleteBook(Long id) {
        log.info("Facade: Deleting book with ID - {}", id);
        bookService.deleteBook(id);
    }

    /**
     * Convert a Book entity to BookResponse using Decorator Pattern
     * @param book the book entity
     * @return the book response with decorated information
     */
    private BookResponse convertToResponse(Book book) {
        // Create base book component
        BookComponent bookComponent = new SimpleBook(book);

        // Apply decorators based on book properties
        if (book.getFeatured()) {
            bookComponent = new FeaturedBookDecorator(bookComponent);
        }
        if (book.getBestseller()) {
            bookComponent = new BestsellerBookDecorator(bookComponent);
        }

        // Build response with decorated values
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .category(book.getCategory())
                .isbn(book.getIsbn())
                .originalPrice(book.getPrice())
                .displayPrice(bookComponent.getPrice())
                .description(bookComponent.getDescription())
                .featured(book.getFeatured())
                .bestseller(book.getBestseller())
                .build();
    }
}
