package com.library.bookmanagement.decorator;

import com.library.bookmanagement.model.Book;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

/**
 * Concrete Component implementation for Decorator Pattern
 * Represents a simple book without any decorations
 */
@RequiredArgsConstructor
public class SimpleBook implements BookComponent {

    private final Book book;

    @Override
    public String getDescription() {
        return String.format("%s by %s", book.getTitle(), book.getAuthor());
    }

    @Override
    public BigDecimal getPrice() {
        return book.getPrice();
    }

    /**
     * Get the underlying book entity
     * @return the book entity
     */
    public Book getBook() {
        return book;
    }
}
