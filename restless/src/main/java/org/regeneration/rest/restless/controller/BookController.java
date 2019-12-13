package org.regeneration.rest.restless.controller;

import org.regeneration.rest.restless.exception.BookNotFoundException;
import org.regeneration.rest.restless.model.Book;
import org.regeneration.rest.restless.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(@Autowired BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getBooks(@RequestParam(value = "title", required = false) String title) {
        return bookService.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping("/books")
    public Book newBook(@RequestBody @Valid Book book) {
        return bookService.newBook(book);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/books/{id}")
    public Book deleteBook(@PathVariable Long id) {
        try {
            return bookService.deleteBook(id);
        } catch (BookNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

}
