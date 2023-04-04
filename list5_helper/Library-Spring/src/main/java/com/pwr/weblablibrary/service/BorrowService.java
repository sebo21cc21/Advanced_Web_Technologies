package com.pwr.weblablibrary.service;

import com.pwr.weblablibrary.entity.Book;
import com.pwr.weblablibrary.entity.Borrowing;
import com.pwr.weblablibrary.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowService implements IBorrowService {

    private final BooksService booksService;
    private static final List<Borrowing> borrowings = new ArrayList<>();

    @Override
    public List<Borrowing> getBorrowings() {
        return borrowings;
    }

    @Override
    public Borrowing borrowBook(int id) throws EntityNotFoundException {

        Optional<Book> libraryBookOpt = booksService.getBooks().stream()
                .filter(b -> b.getId() == id)
                .findFirst();

        if (libraryBookOpt.isEmpty()) {
            throw new EntityNotFoundException("There is no such book");
        }

        Book libraryBook = libraryBookOpt.get();
        if (libraryBook.isBorrowed()) {
            throw new EntityNotFoundException("Book is already borrowed");
        }

        libraryBook.setBorrowed(true);
        Borrowing borrowing = new Borrowing(LocalDateTime.now(), null, libraryBook);
        borrowings.add(borrowing);

        return borrowing;
    }

    @Override
    public Borrowing returnBook(int id) throws EntityNotFoundException {
        Optional<Book> libraryBookOpt = booksService.getBooks().stream()
                .filter(b -> b.getId() == id)
                .findFirst();

        if (libraryBookOpt.isEmpty()) {
            throw new EntityNotFoundException("There is no such book");
        }

        Book libraryBook = libraryBookOpt.get();
        if (!libraryBook.isBorrowed()) {
            throw new EntityNotFoundException("Book is not borrowed");
        }

        Optional<Borrowing> borrowing = borrowings.stream()
                .filter(b -> b.getBook().equals(libraryBook) && b.getReturnDate() == null)
                .findFirst();

        if (borrowing.isPresent()) {
            borrowing.get().setReturnDate(LocalDateTime.now());
            libraryBook.setBorrowed(false);
            return borrowing.get();
        }

        throw new InternalError();
    }
}
