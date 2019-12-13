package org.regeneration.rest.restless.service;

import org.regeneration.rest.restless.exception.BookNotFoundException;
import org.regeneration.rest.restless.model.Book;
import org.regeneration.rest.restless.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(@Autowired BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book getById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book newBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
       return bookRepository.findAll();
    }

    public Book deleteBook(Long id) {
        final Book deletedBook = getById(id);
        bookRepository.delete(deletedBook);
        return deletedBook;
    }
}
