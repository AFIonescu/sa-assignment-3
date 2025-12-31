package com.library.bookmanagement.service;

import com.library.bookmanagement.model.Book;
import com.library.bookmanagement.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service class for Book entity
 * Handles business logic and CRUD operations
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    /**
     * Add a new book to the library
     * @param book the book to add
     * @return the saved book
     */
    @Transactional
    public Book addBook(Book book) {
        log.info("Adding new book: {}", book.getTitle());
        return bookRepository.save(book);
    }

    /**
     * Get all books in the library
     * @return list of all books
     */
    public List<Book> getAllBooks() {
        log.info("Retrieving all books");
        return bookRepository.findAll();
    }

    /**
     * Get a book by its ID
     * @param id the book ID
     * @return Optional containing the book if found
     */
    public Optional<Book> getBookById(Long id) {
        log.info("Retrieving book with ID: {}", id);
        return bookRepository.findById(id);
    }

    /**
     * Update an existing book
     * @param id the book ID
     * @param bookDetails the updated book details
     * @return the updated book
     */
    @Transactional
    public Book updateBook(Long id, Book bookDetails) {
        log.info("Updating book with ID: {}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setCategory(bookDetails.getCategory());
        book.setIsbn(bookDetails.getIsbn());
        book.setPrice(bookDetails.getPrice());
        book.setFeatured(bookDetails.getFeatured());
        book.setBestseller(bookDetails.getBestseller());

        return bookRepository.save(book);
    }

    /**
     * Delete a book by its ID
     * @param id the book ID
     */
    @Transactional
    public void deleteBook(Long id) {
        log.info("Deleting book with ID: {}", id);
        bookRepository.deleteById(id);
    }

    /**
     * Find books by category
     * @param category the category to search for
     * @return list of books in the specified category
     */
    public List<Book> findByCategory(String category) {
        log.info("Finding books by category: {}", category);
        return bookRepository.findByCategory(category);
    }

    /**
     * Find books by author
     * @param author the author to search for
     * @return list of books by the specified author
     */
    public List<Book> findByAuthor(String author) {
        log.info("Finding books by author: {}", author);
        return bookRepository.findByAuthor(author);
    }

    /**
     * Find featured books
     * @return list of featured books
     */
    public List<Book> findFeaturedBooks() {
        log.info("Finding featured books");
        return bookRepository.findByFeatured(true);
    }

    /**
     * Find bestseller books
     * @return list of bestseller books
     */
    public List<Book> findBestsellers() {
        log.info("Finding bestseller books");
        return bookRepository.findByBestseller(true);
    }
}
