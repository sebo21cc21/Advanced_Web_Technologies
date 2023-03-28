package com.example.books.Services;

import com.example.books.Entities.Author;
import com.example.books.Entities.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final AuthorService authorService;
    private static List<Book> booksRepo = new ArrayList<>();
    static {
        booksRepo.add(new Book(1, new Author(1, "Henryk", "Sienkiewicz"),
                "W pustyni i w puszczy", 936));
        booksRepo.add(new Book(2, new Author(2, "Adam", "Mickiewicz"),
                "Pan Tadeusz", 334));
        booksRepo.add(new Book(3,new Author(3, "Juliusz", "Słowacki"),
                "Balldayna", 170));
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
    public Book getBook(int id) {
        return booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public Book addBook(Book book) {
        if (isValidBook(book)) {
            book.setId(booksRepo.get(booksRepo.size() - 1).getId() + 1);
            booksRepo.add(book);
            return book;
        }
        return null;
    }

    @Override
    public Book updateBook(Book book) {
        Book bookToUpdate = booksRepo.stream()
                .filter(b -> b.getId() == book.getId())
                .findFirst()
                .orElse(null);
        if (bookToUpdate != null)
        {
            bookToUpdate.setAuthor(book.getAuthor());
            bookToUpdate.setTitle(book.getTitle());
            bookToUpdate.setPages(book.getPages());
        }
        return bookToUpdate;
    }

    @Override
    public void deleteBook(int id) {
        Book bookToDelete = booksRepo.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);

        if (bookToDelete != null) {
            booksRepo.removeIf(b -> b.getId() == id);
        }
    }


}