package com.library.bookmanagement.decorator;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

/**
 * Abstract Decorator class for the Decorator Pattern
 * Base class for all concrete decorators
 */
@RequiredArgsConstructor
public abstract class BookDecorator implements BookComponent {

    protected final BookComponent decoratedBook;

    @Override
    public String getDescription() {
        return decoratedBook.getDescription();
    }

    @Override
    public BigDecimal getPrice() {
        return decoratedBook.getPrice();
    }
}
