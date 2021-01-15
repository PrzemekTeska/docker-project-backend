package pl.teska.bookapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.teska.bookapp.model.Author;
import pl.teska.bookapp.repositories.AuthorRepo;

import java.util.List;

@Service
public class AuthorServiceImplementation implements AuthorService {

    private AuthorRepo authorRepo;

    @Autowired
    public AuthorServiceImplementation(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    @Override
    public Author addAuthor(Author author) {
        return authorRepo.save(author);
    }

    @Override
    public void deleteAuthorById(long id) {
        authorRepo.deleteById(id);
    }

    @Override
    public Author updateAuthor(Author author, long id) {
        Author authorToUpdate = getAuthorById(id);
        authorToUpdate.setFirstName(author.getFirstName());
        authorToUpdate.setLastName(author.getLastName());
        authorRepo.save(authorToUpdate);
        return authorToUpdate;
    }

    @Override
    public Author getAuthorById(long id) {
        return authorRepo.getAuthorByAuthorId(id);
    }
}
