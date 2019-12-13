package org.regeneration.rest.restless;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.regeneration.rest.restless.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookRestControllerIT {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void authenticatedAsUserGettingAllBooks() throws Exception {
        ResponseEntity<String> result = template.withBasicAuth("user", "user")
                .getForEntity("/books", String.class);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        final String booksJson = "[" +
                "{\"id\":3,\"isbn\":\"0143125508\",\"title\":\"The Grapes of Wrath\"}," +
                "{\"id\":4,\"isbn\":\"0872200760\",\"title\":\"Symposium\"}," +
                "{\"id\":5,\"isbn\":\"0486284735\",\"title\":\"Pride and Prejudice\"}," +
                "{\"id\":6,\"isbn\":\"9780141182636\",\"title\":\"The Great Gatsby\"}," +
                "{\"id\":7,\"isbn\":\"12345678910\",\"title\":\"Book title\"}" +
                "]";
        assertEquals(booksJson, result.getBody());
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

        ResponseEntity<String> response = template.withBasicAuth("user", "user")
                .postForEntity("/books", request, String.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("{\"id\":7,\"isbn\":\"12345678910\",\"title\":\"Book title\"}", response.getBody());
    }

    @Test
    public void deleteBookWithUserGettingForbidden() {
        template.withBasicAuth("user", "user")
                .delete("/books/" + 7);
    }
}
