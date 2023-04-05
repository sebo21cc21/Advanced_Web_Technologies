package com.pwr.weblablibrary.service;

import com.pwr.weblablibrary.entity.Author;
import com.pwr.weblablibrary.exception.EntityNotFoundException;
import com.pwr.weblablibrary.exception.InvalidEntityException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements IAuthorService {

    private static List<Author> authorsRepo = new ArrayList<>();

    static {
        authorsRepo.add(new Author(1, "Henryk", "Sienkiewicz"));
        authorsRepo.add(new Author(2, "Stanisław", "Reymont"));
        authorsRepo.add(new Author(3, "Adam", "Mickiewicz"));
    }


    @Override
    public Collection<Author> getAuthors() {
        return authorsRepo;
    }

    @Override
    public Author getAuthor(int id) throws EntityNotFoundException {
        return authorsRepo.stream()
                .filter(b -> b.getId() == id)
                .findFirst().orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Author addAuthor(Author author) throws InvalidEntityException {
        author.setId(authorsRepo.get(authorsRepo.size() - 1).getId() + 1);
        if (validateAuthor(author)) {
            authorsRepo.add(author);
            return author;
        }
        throw new InvalidEntityException();
    }

    @Override
    public Author updateAuthor(Author author) throws EntityNotFoundException {
        Author updatedAuthor = authorsRepo.stream()
                .filter(b -> b.getId() == author.getId())
                .findFirst().orElseThrow(EntityNotFoundException::new);
        updatedAuthor.setFirstName(author.getFirstName());
        updatedAuthor.setLastName(author.getLastName());
        return updatedAuthor;
    }

    @Override
    public void deleteAuthor(int id) throws EntityNotFoundException {
        Optional<Author> book = authorsRepo.stream()
                .filter(b -> b.getId() == id)
                .findAny();

        if (book.isEmpty()) {
            throw new EntityNotFoundException();
        }
        authorsRepo.removeIf(b -> b.getId() == id);
    }

    private boolean validateAuthor(Author author) {
        return author.getFirstName() != null && author.getLastName() != null
                && !author.getFirstName().equals("") && !author.getLastName().equals("");
    }

}
