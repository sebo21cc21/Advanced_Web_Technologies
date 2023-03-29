package com.example.books.Services;

import com.example.books.Entities.Book;
import com.example.books.Entities.Borrowing;
import com.example.books.Exceptions.BorrowedBookException;
import com.example.books.Exceptions.ObjectNotFoundException;
import com.example.books.Exceptions.ReturnedBookException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowingService implements IBorrowingService {

    private final BookService bookService;
    private static final List<Borrowing> borrowingRepo = new ArrayList<>();


    @Override
    public Collection<Borrowing> getAllBorrowings() {
        return borrowingRepo;
    }

    @Override
    public Borrowing borrowBook(int id) throws ObjectNotFoundException, BorrowedBookException {
        Book bookToBorrow = bookService.getAllBooks().stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);

        if (bookToBorrow == null) {
            throw new ObjectNotFoundException("This book does not exist!");
        }
        else if (bookToBorrow.isBorrowed()) {
            throw new BorrowedBookException("This book is already borrowed!");
        }

        bookToBorrow.setBorrowed(true);
        Borrowing newBorrowing = new Borrowing(borrowingRepo.get(borrowingRepo.size() - 1).getId() + 1, bookToBorrow,
                LocalDateTime.now(), null);
        borrowingRepo.add(newBorrowing);
        return newBorrowing;
    }

    @Override
    public Borrowing returnBook(int id) throws ObjectNotFoundException, ReturnedBookException {
        Book bookToReturn = bookService.getAllBooks().stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);

        if (bookToReturn == null) {
            throw new ObjectNotFoundException("This book does not exist!");
        }
        else if (!bookToReturn.isBorrowed()) {
            throw new ReturnedBookException("This book is not borrowed yet!");
        }

        Borrowing returnedBorrowing = borrowingRepo.stream()
                .filter(b -> b.getBook().equals(bookToReturn) && b.getReturnDate() == null)
                .findFirst()
                .orElse(null);

        if (returnedBorrowing != null) {
            returnedBorrowing.setReturnDate(LocalDateTime.now());
            bookToReturn.setBorrowed(false);
            return returnedBorrowing;
        }
        throw new InternalError();
    }
}
