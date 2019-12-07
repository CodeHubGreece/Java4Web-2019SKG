package org.regeneration.rest.restless.service;

import org.regeneration.rest.restless.model.Book;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    public Book getById(Long id) {
        return new Book(id, "1234", "Book title " + id);
    }

    public Book newBook(Book book) {
        book.setId(99l);
        return book;
    }
}
