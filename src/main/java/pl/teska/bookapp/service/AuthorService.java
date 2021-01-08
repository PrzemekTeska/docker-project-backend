package pl.teska.bookapp.service;

import pl.teska.bookapp.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAllAuthors();

    Author addAuthor(Author author);

    void deleteAuthorById(long id);

    boolean updateAuthor(Author author);

    Author getAuthorById(long id);
}
