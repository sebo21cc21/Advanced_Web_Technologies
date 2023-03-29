package com.example.books.Services;

import com.example.books.Entities.Borrowing;
import com.example.books.Exceptions.BorrowedBookException;
import com.example.books.Exceptions.ObjectNotFoundException;
import com.example.books.Exceptions.ReturnedBookException;

import java.util.Collection;

public interface IBorrowingService {

    Collection<Borrowing> getAllBorrowings();

    Borrowing borrowBook(int id) throws ObjectNotFoundException, BorrowedBookException;

    Borrowing returnBook(int id) throws ObjectNotFoundException, ReturnedBookException;
}
