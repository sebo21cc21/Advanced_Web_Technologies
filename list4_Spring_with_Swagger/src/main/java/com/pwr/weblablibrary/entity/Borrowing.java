package com.pwr.weblablibrary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Borrowing {
    private int id;
    private Book book;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
}
