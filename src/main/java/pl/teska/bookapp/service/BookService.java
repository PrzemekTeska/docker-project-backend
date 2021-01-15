package pl.teska.bookapp.service;

import pl.teska.bookapp.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    Book addBook(Book book);

    void deleteBookById(long id);

    Book updateBook(Book book, long id);

    Book getBookById(long id);

}
