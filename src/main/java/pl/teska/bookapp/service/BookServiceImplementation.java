package pl.teska.bookapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.teska.bookapp.model.Book;
import pl.teska.bookapp.repositories.BookRepo;

import java.util.List;

@Service
public class BookServiceImplementation implements BookService {

    private BookRepo bookRepo;

    @Autowired
    public BookServiceImplementation(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public Book addBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public void deleteBookById(long id) {
        bookRepo.deleteById(id);
    }

    @Override
    public boolean updateBook(Book book) {
        Book newBook = bookRepo.save(book);
        return bookRepo.findById(book.getId()).get().equals(newBook);
    }
}
