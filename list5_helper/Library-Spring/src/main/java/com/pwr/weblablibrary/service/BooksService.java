package com.pwr.weblablibrary.service;

import com.pwr.weblablibrary.entity.Author;
import com.pwr.weblablibrary.entity.Book;
import com.pwr.weblablibrary.exception.EntityNotFoundException;
import com.pwr.weblablibrary.exception.InvalidEntityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BooksService implements IBooksService {

    private final AuthorService authorService;

    private static List<Book> booksRepo = new ArrayList<>();

    static {
        booksRepo.add(new Book(1, "Potop",  new Author(1, "Henryk", "Sienkiewicz"), 936, false));
        booksRepo.add(new Book(2, "Wesele", new Author(2, "Stanisław", "Reymont"), 150, false));
        booksRepo.add(new Book(3, "Dziady", new Author(3, "Adam", "Mickiewicz"), 292, false));
    }

    @Override
    public Collection<Book> getBooks() {
        return booksRepo;
    }

    @Override
    public Book getBook(int id) throws EntityNotFoundException {
        return booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findFirst().orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Book addBook(Book book) throws InvalidEntityException {
        book.setId(booksRepo.get(booksRepo.size() - 1).getId() + 1);
        if (validateBook(book)) {
            booksRepo.add(book);
            return book;
        }
        throw new InvalidEntityException();
    }

    @Override
    public Book updateBook(Book book) throws EntityNotFoundException, InvalidEntityException {
        Book updatedBook = booksRepo.stream()
                .filter(b -> b.getId() == book.getId())
                .findFirst().orElseThrow(EntityNotFoundException::new);
        if (validateBook(book)) {
            updatedBook.setTitle(book.getTitle());
            updatedBook.setAuthor(book.getAuthor());
            updatedBook.setPages(book.getPages());
            return updatedBook;
        }
        throw new InvalidEntityException();

    }

    @Override
    public void deleteBook(int id) throws EntityNotFoundException {
        Optional<Book> book = booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny();

        if (book.isEmpty()) {
            throw new EntityNotFoundException();
        }
        booksRepo.removeIf(b -> b.getId() == id);
    }

    public boolean validateBook(Book book) {
        return book.getAuthor() != null && authorService.getAuthors().contains(book.getAuthor())
                && book.getTitle() != null && book.getPages() != 0 && !book.getTitle().equals("");
    }
}
