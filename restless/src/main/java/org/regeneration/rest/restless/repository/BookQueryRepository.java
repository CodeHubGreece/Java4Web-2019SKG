package org.regeneration.rest.restless.repository;

import org.regeneration.rest.restless.model.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookQueryRepository {
    List<Book> findBooksWithSubjectAndPublishedDate(LocalDate from, LocalDate to, String title);
}
