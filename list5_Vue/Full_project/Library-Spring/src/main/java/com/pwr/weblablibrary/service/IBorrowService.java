package com.pwr.weblablibrary.service;

import com.pwr.weblablibrary.entity.Borrowing;
import com.pwr.weblablibrary.exception.EntityNotFoundException;

import java.util.List;

public interface IBorrowService {

    List<Borrowing> getBorrowings();

    Borrowing borrowBook(int id) throws EntityNotFoundException;

    Borrowing returnBook(int id) throws EntityNotFoundException;
}
