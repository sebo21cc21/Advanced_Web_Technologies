package com.example.books.Services;

import com.example.books.Entities.Author;
import com.example.books.Exceptions.InvalidObjectException;
import com.example.books.Exceptions.ObjectNotFoundException;

import java.util.Collection;

public interface IAuthorService {
    Collection<Author> getAllAuthors();
    Author getAuthor(int id) throws ObjectNotFoundException;
    Author addAuthor(Author author) throws InvalidObjectException;
    Author updateAuthor(Author author) throws ObjectNotFoundException, InvalidObjectException;
    void deleteAuthor(int id) throws ObjectNotFoundException;
}
