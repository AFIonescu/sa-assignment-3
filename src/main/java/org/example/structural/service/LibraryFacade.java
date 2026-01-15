package org.example.structural.service;


import org.example.structural.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LibraryFacade {

    @Autowired
    private BookService bookService;

    public void addBook(Book book) {
        bookService.addBook(book);
    }

    public List<BookDecorator> getFeaturedBooks() {
        return bookService.getAllBooks().stream()
                .map(book -> new FeaturedBookDecorator(new BasicBook(book)))
                .collect(Collectors.toList());
    }
}
