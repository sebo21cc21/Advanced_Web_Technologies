package com.example.books.Services;

import com.example.books.Entities.Author;
import com.example.books.Exceptions.InvalidObjectException;
import com.example.books.Exceptions.ObjectNotFoundException;
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
    public Author getAuthor(int id) throws ObjectNotFoundException{
        return authorsRepo.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(ObjectNotFoundException::new);
    }

    @Override
    public Author addAuthor(Author author) throws InvalidObjectException {
        if (isValidAuthor(author)) {
            author.setId(authorsRepo.get(authorsRepo.size() - 1).getId() + 1);
            authorsRepo.add(author);
            return author;
        }
        throw new InvalidObjectException();
    }

    @Override
    public Author updateAuthor(Author author) throws ObjectNotFoundException, InvalidObjectException {
        Author authorToUpdate = authorsRepo.stream()
                .filter(b -> b.getId() == author.getId())
                .findFirst()
                .orElseThrow(ObjectNotFoundException::new);
        if (isValidAuthor(author))
        {
            authorToUpdate.setName(author.getName());
            authorToUpdate.setSurname(author.getSurname());
            return authorToUpdate;
        }
        throw new InvalidObjectException();
    }

    @Override
    public void deleteAuthor(int id) throws ObjectNotFoundException {
        authorsRepo.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(ObjectNotFoundException::new);

        authorsRepo.removeIf(b -> b.getId() == id);
    }
}
