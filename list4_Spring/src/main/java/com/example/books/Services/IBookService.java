package com.example.books.Services;

import com.example.books.Entities.Book;
import com.example.books.Exceptions.InvalidObjectException;
import com.example.books.Exceptions.ObjectNotFoundException;

import java.util.Collection;
public interface IBookService {
    Collection<Book> getAllBooks();
    Book getBook(int id) throws ObjectNotFoundException;

    Book addBook(Book book) throws InvalidObjectException;

    Book updateBook(Book book) throws ObjectNotFoundException, InvalidObjectException;

    void deleteBook(int id) throws ObjectNotFoundException;
}