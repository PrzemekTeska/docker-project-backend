package pl.teska.bookapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.teska.bookapp.model.Author;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {

    Author getAuthorByAuthorId(long id);
}
