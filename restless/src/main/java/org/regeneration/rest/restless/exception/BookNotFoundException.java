package org.regeneration.rest.restless.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException {

    private final Long id;

    public BookNotFoundException(Long id) {
        super("Could not find book with id " + id);
        this.id=id;
    }

    public Long getId() {
        return id;
    }

}
