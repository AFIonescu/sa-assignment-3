package com.library.bookmanagement.decorator;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Concrete Decorator for Bestseller Books
 * Adds "Bestseller" badge and increases price by 5%
 */
@Slf4j
public class BestsellerBookDecorator extends BookDecorator {

    private static final BigDecimal PRICE_MULTIPLIER = new BigDecimal("1.05");

    public BestsellerBookDecorator(BookComponent decoratedBook) {
        super(decoratedBook);
        log.debug("Decorating book with Bestseller badge");
    }

    @Override
    public String getDescription() {
        return "[BESTSELLER] " + decoratedBook.getDescription();
    }

    @Override
    public BigDecimal getPrice() {
        BigDecimal originalPrice = decoratedBook.getPrice();
        BigDecimal newPrice = originalPrice.multiply(PRICE_MULTIPLIER)
                .setScale(2, RoundingMode.HALF_UP);
        log.debug("Bestseller book price: {} -> {}", originalPrice, newPrice);
        return newPrice;
    }
}
