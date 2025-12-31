package com.library.bookmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO for book response
 * Contains decorated information
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {

    private Long id;
    private String title;
    private String author;
    private String category;
    private String isbn;
    private BigDecimal originalPrice;
    private BigDecimal displayPrice;
    private String description;
    private Boolean featured;
    private Boolean bestseller;
}
