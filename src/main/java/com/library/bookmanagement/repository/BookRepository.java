package com.library.bookmanagement.repository;

import com.library.bookmanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Book entity
 * Provides database access methods
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Find books by category
     * @param category the category to search for
     * @return list of books in the specified category
     */
    List<Book> findByCategory(String category);

    /**
     * Find books by author
     * @param author the author to search for
     * @return list of books by the specified author
     */
    List<Book> findByAuthor(String author);

    /**
     * Find featured books
     * @param featured true to find featured books
     * @return list of featured books
     */
    List<Book> findByFeatured(Boolean featured);

    /**
     * Find bestseller books
     * @param bestseller true to find bestseller books
     * @return list of bestseller books
     */
    List<Book> findByBestseller(Boolean bestseller);
}
