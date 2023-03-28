package com.example.books.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Author {
    private int id;
    private String name;
    private Author author;
    private String surname;
}
