package com.pwr.weblablibrary.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    private int id;
    private Author author;
    private String title;
    private int pages;
    private boolean isBorrowed;
}
