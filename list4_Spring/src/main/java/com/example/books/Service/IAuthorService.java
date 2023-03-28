package com.example.books.Service;

import com.example.books.Entities.Author;

import java.util.Collection;

public interface IAuthorService {
    Collection<Author> getAllAuthors();
    Author getAuthor(int id);
    Author addAuthor(Author author);
    Author updateAuthor(Author author);
    void deleteAuthor(int id);
}
