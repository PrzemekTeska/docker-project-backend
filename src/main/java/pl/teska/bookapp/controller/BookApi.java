package pl.teska.bookapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.teska.bookapp.model.Author;
import pl.teska.bookapp.model.Book;
import pl.teska.bookapp.service.AuthorService;
import pl.teska.bookapp.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookApi {

    private BookService bookService;
    private AuthorService authorService;

    @Autowired
    public BookApi(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
        Book testBook = new Book();
        Author testAuthor = new Author();
        testAuthor.setFirstName("Henryk");
        testAuthor.setLastName("Sienkiewicz");

        testBook.setTitle("W pustyni i w puszczy");
        testBook.setIsbn("123456789");
        testBook.setAuthor(authorService.addAuthor(testAuthor));
        bookService.addBook(testBook);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteBook(@PathVariable long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Boolean> updateBook(@RequestBody Book book) {
        boolean bookCheck = bookService.updateBook(book);
        if (bookCheck) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

}
