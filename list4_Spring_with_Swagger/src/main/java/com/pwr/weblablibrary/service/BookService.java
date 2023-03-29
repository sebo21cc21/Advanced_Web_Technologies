package com.pwr.weblablibrary.service;

import com.pwr.weblablibrary.entity.Author;
import com.pwr.weblablibrary.entity.Book;
import com.pwr.weblablibrary.exception.InvalidObjectException;
import com.pwr.weblablibrary.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final AuthorService authorService;
    private static final List<Book> booksRepo = new ArrayList<>();

    static {
        booksRepo.add(new Book(1, new Author(1, "Henryk", "Sienkiewicz"),
                "W pustyni i w puszczy", 936, false));
        booksRepo.add(new Book(2, new Author(2, "Adam", "Mickiewicz"),
                "Pan Tadeusz", 334, false));
        booksRepo.add(new Book(3,new Author(3, "Juliusz", "Słowacki"),
                "Balldayna", 170, false));
    }

    private boolean isValidBook(Book book) {
        return book.getAuthor() != null && book.getTitle() != null && book.getPages() > 0
                && !book.getTitle().equals("") && authorService.getAllAuthors().contains(book.getAuthor());
    }
    @Override
    public Collection<Book> getAllBooks() {
        return booksRepo;
    }
    @Override
    public Book getBook(int id) throws ObjectNotFoundException {
        return booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElseThrow(ObjectNotFoundException::new);
    }

    @Override
    public Book addBook(Book book) throws InvalidObjectException {
        if (isValidBook(book) && authorService.isValidAuthor(book.getAuthor())) {
            book.setId(booksRepo.get(booksRepo.size() - 1).getId() + 1);
            booksRepo.add(book);
            return book;
        }
        throw new InvalidObjectException();
    }

    @Override
    public Book updateBook(Book book) throws ObjectNotFoundException, InvalidObjectException {
        Book bookToUpdate = booksRepo.stream()
                .filter(b -> b.getId() == book.getId())
                .findFirst()
                .orElseThrow(ObjectNotFoundException::new);
        if (isValidBook(book) && authorService.isValidAuthor(book.getAuthor()))
        {
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setTitle(book.getTitle());
            bookToUpdate.setPages(book.getPages());
            return bookToUpdate;
        }
        throw new InvalidObjectException();
    }

    @Override
    public void deleteBook(int id) throws ObjectNotFoundException {
        booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElseThrow(ObjectNotFoundException::new);

        booksRepo.removeIf(b -> b.getId() == id);
    }
}
