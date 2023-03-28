package com.example.books.Service;

import com.example.books.Entities.Book;

import java.util.Collection;
public interface IBookService {
    Collection<Book> getAllBooks();
    Book getBook(int id);

    Book addBook(Book book);

    Book updateBook(Book book);

    void deleteBook(int id);
}