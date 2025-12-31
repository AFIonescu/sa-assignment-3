package com.library.bookmanagement.decorator;

import java.math.BigDecimal;

/**
 * Component interface for the Decorator Pattern
 * Defines the contract for book components
 */
public interface BookComponent {

    /**
     * Get the book description
     * @return the book description
     */
    String getDescription();

    /**
     * Get the book price
     * @return the book price
     */
    BigDecimal getPrice();
}
