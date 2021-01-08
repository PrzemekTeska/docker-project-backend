package pl.teska.bookapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.teska.bookapp.model.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
}
