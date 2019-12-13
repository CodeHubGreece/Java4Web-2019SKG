package org.regeneration.rest.restless.repository;

import org.regeneration.rest.restless.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
