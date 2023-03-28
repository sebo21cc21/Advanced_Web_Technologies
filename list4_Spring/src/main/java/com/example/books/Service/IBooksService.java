package com.example.books.Service;

import com.example.books.Constructors.Book;

import java.util.Collection;
public interface IBooksService {
    public abstract Collection<Book> getBooks();
    public abstract Book getBook(int id);
}