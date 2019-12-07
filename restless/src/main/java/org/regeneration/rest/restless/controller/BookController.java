package org.regeneration.rest.restless.controller;

import org.regeneration.rest.restless.model.Book;
import org.regeneration.rest.restless.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class BookController {

    private BookService bookService;

    public BookController(@Autowired BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getBooks(@RequestParam(value = "title", required = true) String title) {
        Book book = new Book(1l, "1234", title);
        return Arrays.asList(book);
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping("/books")
    public Book newBook(@RequestBody Book book) {
        return bookService.newBook(book);
    }







}
