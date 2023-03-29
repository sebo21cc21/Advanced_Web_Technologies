package com.pwr.weblablibrary.service;

import com.pwr.weblablibrary.entity.Book;
import com.pwr.weblablibrary.exception.InvalidObjectException;
import com.pwr.weblablibrary.exception.ObjectNotFoundException;

import java.util.Collection;
public interface IBookService {
    Collection<Book> getAllBooks();
    Book getBook(int id) throws ObjectNotFoundException;

    Book addBook(Book book) throws InvalidObjectException;

    Book updateBook(Book book) throws ObjectNotFoundException, InvalidObjectException;

    void deleteBook(int id) throws ObjectNotFoundException;
}
