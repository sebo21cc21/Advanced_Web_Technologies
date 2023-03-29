package com.pwr.weblablibrary.service;

import com.pwr.weblablibrary.entity.Author;
import com.pwr.weblablibrary.exception.InvalidObjectException;
import com.pwr.weblablibrary.exception.ObjectNotFoundException;

import java.util.Collection;

public interface IAuthorService {
    Collection<Author> getAllAuthors();
    Author getAuthor(int id) throws ObjectNotFoundException;
    Author addAuthor(Author author) throws InvalidObjectException;
    Author updateAuthor(Author author) throws ObjectNotFoundException, InvalidObjectException;
    void deleteAuthor(int id) throws ObjectNotFoundException;
}
