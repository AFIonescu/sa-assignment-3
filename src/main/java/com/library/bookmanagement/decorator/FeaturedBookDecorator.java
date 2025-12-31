package com.library.bookmanagement.decorator;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Concrete Decorator for Featured Books
 * Adds "Featured" badge and increases price by 10%
 */
@Slf4j
public class FeaturedBookDecorator extends BookDecorator {

    private static final BigDecimal PRICE_MULTIPLIER = new BigDecimal("1.10");

    public FeaturedBookDecorator(BookComponent decoratedBook) {
        super(decoratedBook);
        log.debug("Decorating book with Featured badge");
    }

    @Override
    public String getDescription() {
        return "[FEATURED] " + decoratedBook.getDescription();
    }

    @Override
    public BigDecimal getPrice() {
        BigDecimal originalPrice = decoratedBook.getPrice();
        BigDecimal newPrice = originalPrice.multiply(PRICE_MULTIPLIER)
                .setScale(2, RoundingMode.HALF_UP);
        log.debug("Featured book price: {} -> {}", originalPrice, newPrice);
        return newPrice;
    }
}
