package com.library.bookmanagement.config;

import com.library.bookmanagement.model.Book;
import com.library.bookmanagement.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * Data Loader to populate sample data on application startup
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Override
    public void run(String... args) {
        log.info("Loading sample book data...");

        List<Book> sampleBooks = Arrays.asList(
                Book.builder()
                        .title("Clean Code")
                        .author("Robert C. Martin")
                        .category("Programming")
                        .isbn("978-0132350884")
                        .price(new BigDecimal("45.99"))
                        .featured(true)
                        .bestseller(true)
                        .build(),

                Book.builder()
                        .title("Design Patterns")
                        .author("Erich Gamma")
                        .category("Programming")
                        .isbn("978-0201633612")
                        .price(new BigDecimal("54.99"))
                        .featured(true)
                        .bestseller(false)
                        .build(),

                Book.builder()
                        .title("The Pragmatic Programmer")
                        .author("Andrew Hunt")
                        .category("Programming")
                        .isbn("978-0135957059")
                        .price(new BigDecimal("42.99"))
                        .featured(false)
                        .bestseller(true)
                        .build(),

                Book.builder()
                        .title("Effective Java")
                        .author("Joshua Bloch")
                        .category("Programming")
                        .isbn("978-0134685991")
                        .price(new BigDecimal("48.99"))
                        .featured(true)
                        .bestseller(true)
                        .build(),

                Book.builder()
                        .title("Head First Design Patterns")
                        .author("Eric Freeman")
                        .category("Programming")
                        .isbn("978-0596007126")
                        .price(new BigDecimal("39.99"))
                        .featured(false)
                        .bestseller(false)
                        .build(),

                Book.builder()
                        .title("Spring Boot in Action")
                        .author("Craig Walls")
                        .category("Programming")
                        .isbn("978-1617292545")
                        .price(new BigDecimal("44.99"))
                        .featured(true)
                        .bestseller(false)
                        .build(),

                Book.builder()
                        .title("1984")
                        .author("George Orwell")
                        .category("Fiction")
                        .isbn("978-0451524935")
                        .price(new BigDecimal("15.99"))
                        .featured(false)
                        .bestseller(true)
                        .build(),

                Book.builder()
                        .title("To Kill a Mockingbird")
                        .author("Harper Lee")
                        .category("Fiction")
                        .isbn("978-0061120084")
                        .price(new BigDecimal("18.99"))
                        .featured(true)
                        .bestseller(true)
                        .build(),

                Book.builder()
                        .title("The Great Gatsby")
                        .author("F. Scott Fitzgerald")
                        .category("Fiction")
                        .isbn("978-0743273565")
                        .price(new BigDecimal("14.99"))
                        .featured(false)
                        .bestseller(false)
                        .build(),

                Book.builder()
                        .title("Sapiens")
                        .author("Yuval Noah Harari")
                        .category("History")
                        .isbn("978-0062316097")
                        .price(new BigDecimal("24.99"))
                        .featured(true)
                        .bestseller(true)
                        .build()
        );

        bookRepository.saveAll(sampleBooks);
        log.info("Sample data loaded successfully. Total books: {}", sampleBooks.size());
    }
}
