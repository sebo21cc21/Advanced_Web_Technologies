package com.example.books.Service;

import com.example.books.Entities.Author;
import com.example.books.Entities.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthorService implements IAuthorService {

    private static List<Author> authorsRepo = new ArrayList<>();

    static {
        authorsRepo.add(new Author(1, "Henryk", "Sienkiewicz"));
        authorsRepo.add(new Author(2, "Adam", "Mickiewicz"));
        authorsRepo.add(new Author(3, "Juliusz", "Słowacki"));
    }

    private boolean isValidAuthor(Author author) {
        return author.getName() != null && author.getSurname() != null && !author.getName().equals("")
                && !author.getSurname().equals("");
    }

    @Override
    public Collection<Author> getAllAuthors() {
        return authorsRepo;
    }

    @Override
    public Author getAuthor(int id) {
        return authorsRepo.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Author addAuthor(Author author) {
        if (isValidAuthor(author)) {
            author.setId(authorsRepo.get(authorsRepo.size() - 1).getId() + 1);
            authorsRepo.add(author);
            return author;
        }
        return null;
    }

    @Override
    public Author updateAuthor(Author author) {
        Author authorToUpdate = authorsRepo.stream()
                .filter(b -> b.getId() == author.getId())
                .findFirst()
                .orElse(null);
        if (authorToUpdate != null)
        {
            authorToUpdate.setName(author.getName());
            authorToUpdate.setSurname(author.getSurname());
        }
        return authorToUpdate;
    }

    @Override
    public void deleteAuthor(int id) {
        Author authorToDelete = authorsRepo.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElse(null);

        if (authorToDelete != null) {
            authorsRepo.removeIf(b -> b.getId() == id);
        }
    }
}
