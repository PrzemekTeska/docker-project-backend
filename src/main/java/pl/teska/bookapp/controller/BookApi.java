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
import java.util.Random;

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

        Author sienkiewicz = new Author();
        sienkiewicz.setFirstName("Henryk");
        sienkiewicz.setLastName("Sienkiewicz");
        authorService.addAuthor(sienkiewicz);
        Book pustynia = new Book();
        pustynia.setAuthor(sienkiewicz);
        pustynia.setTitle("W pustyni i w puszczy");
        pustynia.setIsbn("9788373271890");
        bookService.addBook(pustynia);

        Author dahl = new Author();
        dahl.setFirstName("Roald");
        dahl.setLastName("Dahl");
        authorService.addAuthor(dahl);

        Book fantastic = new Book();
        fantastic.setIsbn("9780140328721");
        fantastic.setAuthor(dahl);
        fantastic.setTitle("Fantastic Mr. Fox");
        bookService.addBook(fantastic);


        for(int i=3; i<28; i++) {
            testAuthor.setId(i);
            testAuthor.setFirstName("Test author first name " + i);
            testAuthor.setLastName("Test author last name " + i);
            authorService.addAuthor(testAuthor);

            testBook.setId(i);
            testBook.setTitle("Test title " + i);
            testBook.setIsbn(generateRandomIsbn());
            testBook.setAuthor(testAuthor);
            bookService.addBook(testBook);
        }
    }

    private String generateRandomIsbn() {
        int leftLimit = 48;
        int rightLimit= 58;
        int targetStringLength = 13;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
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

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable long id) {
        return new ResponseEntity<>(bookService.updateBook(book, id), HttpStatus.OK);
    }

}
