package com.example.books.Service;

import com.example.books.Entities.Author;
import com.example.books.Entities.Book;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
public class BookService implements IBookService {
    private static List<Book> booksRepo = new ArrayList<>();
    static {
//        booksRepo.add(new Book(1, new Author(1, "Henryk", "Sienkiewicz"),
//                "W pustyni i w puszczy", 936));
//        booksRepo.add(new Book(1, new Author(2, "Adam", "Mickiewicz"),
//                "Pan Tadeusz", 936));
//        booksRepo.add(new Book(3,"Dziady", "Adam Mickiewicz", 292));
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
}