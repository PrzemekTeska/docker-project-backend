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
    public Book updateBook(Book book, long id) {
        Book bookToUpdate = getBookById(id);
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setIsbn(book.getIsbn());
        bookRepo.save(bookToUpdate);
        return bookToUpdate;
    }

    @Override
    public Book getBookById(long id) {
        return bookRepo.getBookById(id);
    }


}
