package org.regeneration.rest.restless;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regeneration.rest.restless.model.Book;
import org.regeneration.rest.restless.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookRestControllerIT {

    private TestRestTemplate template;
    private BookRepository bookRepository;

    @Autowired
    public void setTemplate(TestRestTemplate template) {
        this.template=template;
    }
    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository=bookRepository;
    }

    @Before
    public void beforeEachTest() {
        bookRepository.deleteAll();
        List<Book> books = Arrays.asList(
                new Book("The Grapes of Wrath", "0143125508"),
                new Book("Symposium", "0872200760"),
                new Book("Pride and Prejudice", "0486284735"),
                new Book("The Great Gatsby", "9780141182636")
        );
        bookRepository.saveAll(books);
    }

    @Test
    public void authenticatedAsUserGettingAllBooks() throws Exception {
        ResponseEntity<Book[]> result = template.withBasicAuth("user", "user")
                .getForEntity("/books", Book[].class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(4, result.getBody().length);
    }

    @Test
    public void authenticatedAsUserGettingBookById() throws Exception {
        ResponseEntity<String> result = template.withBasicAuth("user", "user")
                .getForEntity("/books/99", String.class);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("{\"error\":\"Could not find book with id 99\",\"resourceId\":99,\"status\":404}", result.getBody());
    }

    @Test
    public void notAuthenticatedUserGettingUnauthorized() throws Exception {
        ResponseEntity<String> result = template
                .getForEntity("/books", String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

    @Test
    public void newBookWithAuthedUserGettingCreated() throws Exception {
        final Book book = new Book("Book title", "12345678910");
        final HttpEntity<Book> request = new HttpEntity<>(book, new HttpHeaders());

        ResponseEntity<Book> response = template.withBasicAuth("user", "user")
                .postForEntity("/books", request, Book.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("12345678910", response.getBody().getIsbn());
        assertEquals("Book title", response.getBody().getTitle());
    }

    @Test
    public void newBookWithAuthedUserGettingInvalidIsbn() throws Exception {
        final Book book = new Book("Book title", "1234");
        final HttpEntity<Book> request = new HttpEntity<>(book, new HttpHeaders());

        ResponseEntity<String> response = template.withBasicAuth("user", "user")
                .postForEntity("/books", request, String.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("{\"error\":\"size must be between 10 and 13\",\"status\":400}", response.getBody());
    }

    @Test
    public void deleteBookWithUserGettingForbidden() {
        ResponseEntity<String> response = template.withBasicAuth("user", "user")
                .exchange("/books/6", HttpMethod.DELETE, new HttpEntity(new HttpHeaders()), String.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals(":{\"error\":\"Access denied\",\"status\":403}", response.getBody());
    }

    @Test
    public void deleteBookWithAdmin() {
        final Long bookId = bookRepository.findByIsbn("9780141182636").getId();
        ResponseEntity<Book> response = template.withBasicAuth("admin", "admin")
                .exchange("/books/" + bookId, HttpMethod.DELETE, new HttpEntity(new HttpHeaders()), Book.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("9780141182636", response.getBody().getIsbn());
        assertEquals("The Great Gatsby", response.getBody().getTitle());

        // trying delete again should return NO_CONTENT
        ResponseEntity<String> responseNotExisting = template.withBasicAuth("admin", "admin")
                .exchange("/books/"+bookId, HttpMethod.DELETE, new HttpEntity(new HttpHeaders()), String.class);

        assertEquals(HttpStatus.NO_CONTENT, responseNotExisting.getStatusCode());
        assertNull(responseNotExisting.getBody());
    }
}
