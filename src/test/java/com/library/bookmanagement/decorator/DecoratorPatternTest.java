package com.library.bookmanagement.decorator;

import com.library.bookmanagement.model.Book;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Decorator Pattern implementation
 */
class DecoratorPatternTest {

    @Test
    void testSimpleBook() {
        Book book = Book.builder()
                .title("Test Book")
                .author("Test Author")
                .price(new BigDecimal("100.00"))
                .build();

        BookComponent simpleBook = new SimpleBook(book);

        assertEquals("Test Book by Test Author", simpleBook.getDescription());
        assertEquals(new BigDecimal("100.00"), simpleBook.getPrice());
    }

    @Test
    void testFeaturedBookDecorator() {
        Book book = Book.builder()
                .title("Test Book")
                .author("Test Author")
                .price(new BigDecimal("100.00"))
                .build();

        BookComponent decoratedBook = new FeaturedBookDecorator(new SimpleBook(book));

        assertEquals("[FEATURED] Test Book by Test Author", decoratedBook.getDescription());
        assertEquals(new BigDecimal("110.00"), decoratedBook.getPrice());
    }

    @Test
    void testBestsellerBookDecorator() {
        Book book = Book.builder()
                .title("Test Book")
                .author("Test Author")
                .price(new BigDecimal("100.00"))
                .build();

        BookComponent decoratedBook = new BestsellerBookDecorator(new SimpleBook(book));

        assertEquals("[BESTSELLER] Test Book by Test Author", decoratedBook.getDescription());
        assertEquals(new BigDecimal("105.00"), decoratedBook.getPrice());
    }

    @Test
    void testCombinedDecorators() {
        Book book = Book.builder()
                .title("Test Book")
                .author("Test Author")
                .price(new BigDecimal("100.00"))
                .build();

        // Apply both decorators: Featured first, then Bestseller
        BookComponent decoratedBook = new SimpleBook(book);
        decoratedBook = new FeaturedBookDecorator(decoratedBook);
        decoratedBook = new BestsellerBookDecorator(decoratedBook);

        assertEquals("[BESTSELLER] [FEATURED] Test Book by Test Author", decoratedBook.getDescription());
        // Price: 100 * 1.10 (Featured) * 1.05 (Bestseller) = 115.50
        assertEquals(new BigDecimal("115.50"), decoratedBook.getPrice());
    }

    @Test
    void testDecoratorOrder() {
        Book book = Book.builder()
                .title("Test Book")
                .author("Test Author")
                .price(new BigDecimal("50.00"))
                .build();

        // Order 1: Featured then Bestseller
        BookComponent order1 = new BestsellerBookDecorator(
                new FeaturedBookDecorator(new SimpleBook(book))
        );

        // Order 2: Bestseller then Featured
        BookComponent order2 = new FeaturedBookDecorator(
                new BestsellerBookDecorator(new SimpleBook(book))
        );

        // Both should give the same final price (multiplication is commutative)
        assertEquals(order1.getPrice(), order2.getPrice());
    }
}
