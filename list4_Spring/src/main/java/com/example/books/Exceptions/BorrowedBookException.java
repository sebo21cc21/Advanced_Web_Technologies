package com.example.books.Exceptions;

public class BorrowedBookException extends Exception {
    public BorrowedBookException() {
        super();
    }
    public BorrowedBookException(String message) {
        super(message);
    }
}
