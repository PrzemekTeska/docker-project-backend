package pl.teska.bookapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.teska.bookapp.model.Author;
import pl.teska.bookapp.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin
public class AuthorApi {

    private AuthorService authorService;

    @Autowired
    public AuthorApi(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAllAuthors() {
        return new ResponseEntity<>(authorService.getAllAuthors(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getAllAuthors(@PathVariable long id) {
        return new ResponseEntity<>(authorService.getAuthorById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        return new ResponseEntity<>(authorService.addAuthor(author), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteAuthorById(@PathVariable long id) {
        authorService.deleteAuthorById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author, @PathVariable long id) {
        return new ResponseEntity<>(authorService.updateAuthor(author, id), HttpStatus.OK);
    }
}
