package com.pwr.weblablibrary.exception;

public class BorrowedBookException extends Exception {
    public BorrowedBookException() {
        super();
    }
    public BorrowedBookException(String message) {
        super(message);
    }
}
