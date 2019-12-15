package org.regeneration.rest.restless.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regeneration.rest.restless.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Before
    public void init() {
        List<Book> books = Arrays.asList(
                new Book("The Grapes of Wrath", "0143125508", LocalDate.parse("1939-04-14")),
                new Book("Symposium", "0872200760", LocalDate.parse("1989-05-01")),
                new Book("Pride and Prejudice", "0486284735", LocalDate.parse("1995-04-01")),
                new Book("The Great Gatsby", "9780141182636", LocalDate.parse("2000-02-01"))
        );
        bookRepository.saveAll(books);
    }

    @Test
    public void testMatchBookTitle() {
        List<Book> matchedBooks = bookRepository.findMatchingTitle("The Great Gutsby");

        assertEquals(1, matchedBooks.size());
        assertEquals("The Great Gatsby", matchedBooks.get(0).getTitle());
    }

}