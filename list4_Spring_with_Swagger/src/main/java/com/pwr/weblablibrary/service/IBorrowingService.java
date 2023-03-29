package com.pwr.weblablibrary.service;

import com.pwr.weblablibrary.entity.Borrowing;
import com.pwr.weblablibrary.exception.BorrowedBookException;
import com.pwr.weblablibrary.exception.ObjectNotFoundException;
import com.pwr.weblablibrary.exception.ReturnedBookException;

import java.util.Collection;

public interface IBorrowingService {

    Collection<Borrowing> getAllBorrowings();

    Borrowing borrowBook(int id) throws ObjectNotFoundException, BorrowedBookException;

    Borrowing returnBook(int id) throws ObjectNotFoundException, ReturnedBookException;
}
